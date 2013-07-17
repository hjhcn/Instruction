package instruction.service.impl;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.SystemConstants.STATUS;
import instruction.dao.InsDao;
import instruction.model.AdminStatistic;
import instruction.model.Brand;
import instruction.model.Category;
import instruction.model.DownloadLog;
import instruction.model.InsOrderBy;
import instruction.model.InsUpload;
import instruction.model.Instruction;
import instruction.model.TimeStampRange;
import instruction.model.UploadFile;
import instruction.model.UploadFileConvertObject;
import instruction.model.User;
import instruction.rules.DocConvertFinishRule;
import instruction.service.BrandService;
import instruction.service.CategoryService;
import instruction.service.CreditService;
import instruction.service.InsService;
import instruction.service.UploadFileService;
import instruction.service.UserService;
import instruction.util.FileUtils;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class InsServiceImpl implements InsService, DocConvertFinishRule {
	private InsDao insDao;
	private UserService userService;
	private BrandService brandService;
	private CategoryService categoryService;
	private CreditService creditService;
	private UploadFileService uploadFileService;

	public String dataExport(int startTime, int endTime) {

		try {
			String excelName = "已审核说明书(" + Time.formatTimeStamp(startTime, "yyyy-MM-dd") + "_"
					+ Time.formatTimeStamp(endTime, "yyyy-MM-dd") + ")";
			String fileName = excelName + ".xls";
			java.io.File file = new java.io.File(SystemConstants.DATA_TO_EXCEL_FOLDER
					+ new String(fileName.getBytes(), "ISO8859-1"));
			if (file.exists())
				return fileName;
			// ！！！！！！！！！！！这里存在文件不能更新的问题

			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("已审核说明书", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Label label = new Label(0, 0, "ID");
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			label = new Label(1, 0, "标题");
			sheet.addCell(label);

			ArrayList<String> whereClause = new ArrayList<String>();
			whereClause.add("updateTime>=" + startTime);
			whereClause.add("updateTime<=" + endTime);
			whereClause.add("status>=1");
			List<Instruction> inses = insDao.findAllData(whereClause, null, null);
			for (int i = 0; i < inses.size(); i++) {
				jxl.write.Number number = new jxl.write.Number(0, i + 1, inses.get(i).getId());
				sheet.addCell(number);
				label = new Label(1, i + 1, inses.get(i).getTitle());
				sheet.addCell(label);
			}

			// 写入数据并关闭文件
			book.write();
			book.close();
			return fileName;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<Instruction> findTopNew(int count) {
		return insDao.findTopNew(count);
	}

	public void verifyCountBath() {
		List<Instruction> inses = insDao.findAll();
		for (Instruction ins : inses) {
			Random random = new Random();
			int downRandom = random.nextInt(50);
			ins.setDownloadCount(ins.getDownloadCount() + downRandom);
			ins.setViewCount(ins.getViewCount() + downRandom + random.nextInt(300));
			insDao.saveOrUpdate(ins);
		}
	}

	public List<Instruction> findTopData(int cid, int bid, int hasIcon, int page, String groupby,
			String order, String sort, Short status) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (hasIcon > 0) {
			whereClause.add("iconUrl!=null");
			whereClause.add("iconUrl!=''");
		}
		if (cid > 0) {
			whereClause.add(CategoryFilter(cid));
		}
		if (bid > 0) {
			// if (cid > 0)
			whereClause.add("brand.id=" + bid);
		}
		if (status > 0)
			whereClause.add("status>=" + status);
		ArrayList<String> groupbyClause = new ArrayList<String>();
		if (groupby != null && !"".equals(groupby))
			groupbyClause.add(groupby);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		if (InsOrderBy.include(sort)) {
			orderbyClause.put(sort, order);
		}
		return insDao.findTopData(page, whereClause, groupbyClause, orderbyClause);
	}

	public PageView<Instruction> findScrollData(int uid, int page, int rows, String order,
			String sort, Short status) {
		return findScrollData(uid, 0, 0, null, null, page, rows, order, sort, status, false);
	}

	public PageView<Instruction> findScrollData(int uid, int cid, int bid, String search,
			TimeStampRange tsRange, int page, int rows, String order, String sort, Short status,
			boolean isMobile3d) {

		PageView<Instruction> inses = new PageView<Instruction>(page, rows);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		if (InsOrderBy.include(sort)) {
			orderbyClause.put(sort, order);
		}
		inses.setQueryResult(insDao.findScrollData(page, rows,
				this.bulidWhereClause(uid, cid, bid, search, tsRange, status, isMobile3d),
				orderbyClause));
		return inses;
	}

	public PageView<Instruction> findScrollDataCascade(int uid, int cid, int bid, String search,
			TimeStampRange tsRange, int page, int rows, String order, String sort, Short status,
			boolean isMobile3d) {
		PageView<Instruction> inses = new PageView<Instruction>(page, rows);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		if (InsOrderBy.include(sort)) {
			orderbyClause.put(sort, order);
		}
		inses.setQueryResult(insDao.findScrollDataCascade(page, rows,
				this.bulidWhereClause(uid, cid, bid, search, tsRange, status, isMobile3d),
				orderbyClause));
		return inses;
	}

	public ArrayList<String> bulidWhereClause(int uid, int cid, int bid, String search,
			TimeStampRange tsRange, Short status, boolean isMobile3d) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (cid > 0)
			whereClause.add(CategoryFilter(cid));
		if (bid > 0) {
			// if (cid > 0)
			whereClause.add("brand.id=" + bid);
		}
		if (search != null && !"".equals(search)) {
			whereClause.add("title like '%" + search + "%'");
		}
		if (null != tsRange && tsRange.isValid()) {
			whereClause.add("uploadTime>=" + tsRange.getStartTimeStamp());
			whereClause.add("uploadTime<=" + tsRange.getEndTimeStamp());
		}
		if (status > 0)
			whereClause.add("status>=" + status);
		if (isMobile3d)
			whereClause.add("mobileSWFUrl is not null or mobile3DSWFUrl is not null");
		return whereClause;
	}

	public synchronized Instruction get(int id, Short status) {
		Instruction ins = insDao.get(id);
		if (ins != null) {
			ins.setViewCount(ins.getViewCount() + 1);
			insDao.saveOrUpdate(ins);
			if (ins.getStatus() < status)
				ins = null;
		}
		return ins;
	}

	/**
	 * 还需要增加uploadfile归属，用户具体添加该说明书的权限
	 * 
	 * @param uid用户id
	 * @param insUpload上传的输入模型
	 * @return 返回操作結果（feedback）
	 */
	public synchronized int operate(int uid, InsUpload insUpload, Short status, OPERATE operate) {
		Instruction ins = new Instruction();
		User user = userService.get(uid);
		int time = Time.getTimeStamp();

		if (operate == OPERATE.ADMIN_UPLOAD || operate == OPERATE.USER_UPLOAD) {
			// 用于检查标题时使用insUpload.getId
			insUpload.setId(0);
		}

		// 检查标题合法性
		if (insUpload.getTitle() == null || insUpload.getTitle().isEmpty())
			return SystemConstants.FEEDBACK.INS_TITLE_ILLEGAL;
		if (!checkTitleWithOutIid(insUpload.getTitle(), insUpload.getId()))
			return SystemConstants.FEEDBACK.INS_TITLE_EXSIT;
		if (!this.checkModelWithOutIid(insUpload.getBid(), insUpload.getModel(), insUpload.getId()))
			return SystemConstants.FEEDBACK.INS_MODEL_ILLEGAL;
		// 检查品牌存在性
		Brand brand = brandService.get(insUpload.getBid());
		if (insUpload.getBid() != SystemConstants.UPLOAD_BRAND_OTHER && null == brand)
			return SystemConstants.FEEDBACK.INS_BRAND_ILLEGAL;
		// 检查分类存在性
		Category category = categoryService.get(insUpload.getCid());
		if (null == category)
			return SystemConstants.FEEDBACK.INS_CATEGORY_ILLEGAL;

		// 检查上传封面图片正确性
		UploadFile iconFile = uploadFileService.get(insUpload.getIconUfid());
		if (null == iconFile)
			return SystemConstants.FEEDBACK.INS_ICON_ILLEGAL;
		// 检查上传文件正确性
		UploadFile insFile = uploadFileService.get(insUpload.getInsUfid());
		if (null == insFile)
			return SystemConstants.FEEDBACK.INS_FILE_ILLEGAL;

		if (operate == OPERATE.ADMIN_UPLOAD || operate == OPERATE.USER_UPLOAD) {
			ins.setUser(user);
			ins.setUploadTime(time);
			ins.setDownloadCount(0);
			ins.setViewCount(0);
			if (operate == OPERATE.USER_UPLOAD) {
				ins.setIsUserUpload(new Short((short) 1));
			} else {
				// 初始化下载浏览数据
				Random random = new Random();
				int downRandom = random.nextInt(20);
				ins.setDownloadCount(downRandom);
				ins.setViewCount(downRandom + random.nextInt(100));
			}
		} else if ((operate == OPERATE.EDIT)) {
			ins = insDao.get(insUpload.getId());
			if (ins == null)
				return SystemConstants.FEEDBACK.INS_ID_NOEXSIT;
			String editUids = ins.getEditUids();
			if (editUids != null && !editUids.equals(""))
				editUids += ",";
			else
				editUids = "";
			editUids += uid;
			ins.setEditUids(editUids);
		}
		ins.setUpdateTime(time);
		ins.setStatus(status);

		ins.setCategory(category);
		ins.setBrand(brand);
		ins.setModel(insUpload.getModel());
		ins.setTitle(insUpload.getTitle());
		ins.setDescription(insUpload.getDescription());
		ins.setG3Url(insUpload.getG3Url());
		ins.setServer(SystemConstants.UPLOAD_FOLDER_SERVER);

		String iconUrl = iconFile.getServer() + "/" + iconFile.getFileUrl();
		ins.setIconUrl(iconUrl);// 冗余字段，用于列表查询
		// 新增统一文件上传 2013-05-13 start
		// 保存封面图片信息
		ins.setIconFile(iconFile);
		if (iconFile.getStatus() == 0) {
			// 转存图片
			uploadFileService.saveFile(iconFile);
			iconFile.setStatus(new Short((short) 1));
		}
		// 保存文件信息
		ins.setInsFile(insFile);
		String ext = FileUtils.getExt(insFile.getFileUrl());
		ins.setFileType(ext);
		switch (insFile.getStatus()) {
		case -2:
		case -1:
			// ins.setStatus(insFile.getStatus());
			// ins的status与uploadfile的status区分，前者只用记录审核与否
			break;
		case 0:
			// ins.setStatus(insFile.getStatus());
			// ins的status与uploadfile的status区分，前者只用记录审核与否
			insFile.setStatus(new Short((short) -1));
			// 转换并转存文件
			SystemConstants.docConverter.push(new UploadFileConvertObject(insFile, this));
			break;
		default:
			break;
		}
		// 新增统一文件上传 2013-05-13 end

		// swf文件添加
		UploadFile mobileFile = uploadFileService.get(insUpload.getMobileUfid());
		if (null != mobileFile) {
			ins.setMobileSWFUrl(mobileFile.getFileUrl());
			uploadFileService.saveFile(mobileFile);
		}
		UploadFile mobile3DFile = uploadFileService.get(insUpload.getMobile3DUfid());
		if (null != mobile3DFile) {
			ins.setMobile3DSWFUrl(mobile3DFile.getFileUrl());
			uploadFileService.saveFile(mobileFile);
		}

		// 修改brand和category，还差修改原来的-1
		if (brand != null) {
			brand.setCount(brand.getCount() + 1);
			brandService.saveOrUpdate(brand);
		}
		category.setCount(category.getCount() + 1);
		categoryService.saveOrUpdate(category);

		insDao.saveOrUpdate(ins);
		return SystemConstants.FEEDBACK.SUCCESS;
	}

	public void convertFinish(UploadFile insFile) {
		uploadFileService.saveOrUpdate(insFile);
	}

	/**
	 * 检查型号是否合法，这里只判断是否已存在
	 * 
	 * @param bid
	 *            输入品牌(不同品牌型号可重复)
	 * @param model
	 *            当前输入型号
	 * @param iid
	 *            当前输入说明书id(用于编辑时判断是否重复)
	 * @return 若合法，返回true，若非法返回false
	 */
	private boolean checkModelWithOutIid(int bid, String model, int iid) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (bid > 0) {
			whereClause.add("brand.id=" + bid);
			if (null != model && !"".equals(model)) {
				whereClause.add("model='" + model + "'");
			}
			List<Instruction> inses = insDao.findAllData(whereClause, null, null);
			if (inses.size() > 0) {
				if (iid <= 0)
					return false;
				else {
					for (Instruction ins : inses) {
						if (ins.getId() != iid) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * 检查标题是否合法，这里只判断是否已存在
	 * 
	 * @param title
	 *            输入标题
	 * @param iid
	 *            当前输入说明书id(用于编辑时判断是否重复)
	 * @return 若合法，返回true，若非法返回false
	 */
	private boolean checkTitleWithOutIid(String title, int iid) {
		List<Instruction> inses = insDao.findByProperty("title", title);
		if (inses.size() > 0) {
			if (iid <= 0)
				return false;
			else {
				for (Instruction ins : inses) {
					if (ins.getId() != iid) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public int delete(int id) {
		Instruction ins = insDao.get(id);
		if (ins == null)
			return SystemConstants.FEEDBACK.INS_ID_NOEXSIT;
		else {
			insDao.delete(ins);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public int operate(String ids, OPERATE operate) {
		int count = 0;
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			try {
				int id = Integer.parseInt(idStr);
				Instruction ins = insDao.get(id);
				if (ins != null) {
					if (operate == OPERATE.DELETE) {
						insDao.delete(ins);
						count++;
					} else if (operate == OPERATE.VERIFY) {
						if (ins.getStatus().shortValue() == STATUS.UNVERIFY) {
							ins.setStatus(STATUS.PASS);
							insDao.saveOrUpdate(ins);
							if (ins.getIsUserUpload() == 1) {
								creditService.addCredit(4, ins, ins.getUser(), ins.getUploadTime());
							}
							count++;
						}
					} else if (operate == OPERATE.UNVERIFY) {
						if (ins.getStatus().shortValue() == STATUS.PASS) {
							ins.setStatus(STATUS.UNVERIFY);
							insDao.saveOrUpdate(ins);
							count++;
						}
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public String CategoryFilter(int cid) {
		String categoryFilter = "category.id=" + cid;
		List<Category> categorys = categoryService.getTree(cid);
		for (Category category : categorys) {
			categoryFilter += " or category.id=" + category.getId();
		}
		return categoryFilter;
	}

	public synchronized UploadFile download(int iid, int uid) {
		UploadFile downloadFile = null;
		User user = userService.get(uid);
		Instruction ins = insDao.get(iid);
		if (null != user && null != ins) {
			ins.setDownloadCount(ins.getDownloadCount() + 1);
			if (ins != null) {
				downloadFile = ins.getInsFile();
				if (downloadFile != null) {
					creditService.addCredit(2, ins, user, Time.getTimeStamp());
				}
			}
			insDao.saveOrUpdate(ins);

			// 下载记录
			DownloadLog downloadLog = new DownloadLog();
			downloadLog.setInstruction(ins);
			downloadLog.setUser(user);
			downloadLog.setDateline(Time.getTimeStamp());
			user.getDownloadLogs().add(downloadLog);
			userService.saveOrUpdate(user);
		}

		return downloadFile;
	}

	public List<AdminStatistic> statistic(int startTime, int endTime, int uid) {
		ArrayList<String> whereClause = new ArrayList<String>();
		whereClause.add("uploadTime>=" + startTime);
		whereClause.add("uploadTime<=" + endTime);
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		ArrayList<String> groupClause = new ArrayList<String>();
		groupClause.add("user");
		if (uid > 0)
			groupClause.add("brand");
		List<AdminStatistic> statistics = insDao.statistic(whereClause, orderbyClause, groupClause);
		return statistics;
	}

	public int countByUid(int uid, int startTimeStamp, int endTimeStamp, short status) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (startTimeStamp < endTimeStamp) {
			whereClause.add("uploadTime>=" + startTimeStamp);
			whereClause.add("uploadTime<=" + endTimeStamp);
		}
		whereClause.add("uploadTime<=" + endTimeStamp);
		if (status != STATUS.SHOWALL) {
			whereClause.add("status=" + status);
		}
		return insDao.operate(whereClause, "count", "status");
	}

	public int judageModel(InsUpload insUpload) {
		if (!this.checkModelWithOutIid(insUpload.getBid(), insUpload.getModel(), insUpload.getId()))
			return SystemConstants.FEEDBACK.INS_MODEL_ILLEGAL;
		else
			return SystemConstants.FEEDBACK.SUCCESS;
	}

	public void reOrder(String orderStr) {
		String[] orderArray = orderStr.split(",");
		for (String idStr : orderArray) {
			String[] itemArray = idStr.split("|");
			if (itemArray.length == 2) {
				try {
					int id = Integer.parseInt(itemArray[0]);
					int order = Integer.parseInt(itemArray[1]);
					Instruction ins = insDao.get(id);
					if (ins != null) {
						ins.setOrder(order);
						insDao.saveOrUpdate(ins);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public void setInsDao(InsDao insDao) {
		this.insDao = insDao;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setUploadFileService(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	public int reCoverter(int id) {
		Instruction ins = insDao.get(id);
		if (ins == null)
			return SystemConstants.FEEDBACK.INS_ID_NOEXSIT;
		System.out.println("再次请求:" + ins.getInsFile().getFileUrl());
		SystemConstants.docConverter.push(new UploadFileConvertObject(ins.getInsFile(), this));
		return SystemConstants.FEEDBACK.SUCCESS;
	}

}
