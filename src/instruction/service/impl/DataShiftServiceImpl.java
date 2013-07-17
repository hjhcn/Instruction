package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.BrandDao;
import instruction.dao.CategoryDao;
import instruction.dao.InsDao;
import instruction.dao.PhpcmsDao;
import instruction.dao.UploadFileDao;
import instruction.dao.UserDao;
import instruction.model.Brand;
import instruction.model.File;
import instruction.model.Instruction;
import instruction.model.PhpcmsCDownload;
import instruction.model.PhpcmsContent;
import instruction.model.UploadFile;
import instruction.model.UploadFileConvertObject;
import instruction.rules.DocConvertFinishRule;
import instruction.service.DataShiftService;
import instruction.util.FileUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class DataShiftServiceImpl implements DataShiftService, DocConvertFinishRule {
	private PhpcmsDao phpcmsContentDao;
	private InsDao insDao;
	private CategoryDao categoryDao;
	private BrandDao brandDao;
	private UserDao userDao;
	private UploadFileDao uploadFileDao;

	public void dataTransfer() {
		List<Instruction> inses = insDao.findAll();
		for (Instruction ins : inses) {
			UploadFile insFile = null;
			for (File file : ins.getFiles()) {
				insFile = new UploadFile();
				insFile.setFileUrl(file.getFileUrl());
				insFile.setFileSize(file.getFileSize() == null ? 0 : file.getFileSize());
				insFile.setDescription(file.getDescription());
				if (null == insFile.getDescription() || insFile.getDescription().isEmpty()) {
					insFile.setDescription(ins.getTitle());
				}
				insFile.setUser(ins.getUser());
				insFile.setFileType(FileUtils.getExt(file.getFileUrl()));
				insFile.setStatus((short) 2);
				insFile.setDateline(ins.getUploadTime());
				insFile.setServer(ins.getServer());
			}
			if (null != ins.getInsFile() && null != insFile)
				ins.setInsFile(insFile);

			UploadFile iconFile = null;
			if (null == ins.getIconUrl() || ins.getIconUrl().isEmpty()
					|| ins.getIconUrl().indexOf("default") > 0) {
				ins.setIconUrl(SystemConstants.UPLOAD_FOLDER_SERVER + "/default.png");
				iconFile = uploadFileDao.get(1);
			} else {
				iconFile = new UploadFile();
				iconFile.setFileUrl(ins.getIconUrl());

				java.io.File iconfile1 = new java.io.File(SystemConstants.UPLOAD_FOLDER + "/"
						+ ins.getIconUrl());
				java.io.File iconfile2 = new java.io.File(SystemConstants.OLD_UPLOAD_FOLDER + "/"
						+ ins.getIconUrl());
				if (iconfile1.exists()) {
					iconFile.setFileSize((int) (iconfile1.length() / 1024));
					iconFile.setStatus((short) 1);
				} else if (iconfile2.exists()) {
					iconFile.setFileSize((int) (iconfile2.length() / 1024));
					iconFile.setStatus((short) 1);
				} else {
					iconFile.setFileSize(0);
					iconFile.setStatus((short) -2);
				}
				iconFile.setDescription("封面" + ins.getTitle());
				iconFile.setDescription(ins.getTitle());
				iconFile.setUser(ins.getUser());
				iconFile.setFileType(FileUtils.getExt(ins.getIconUrl()));
				iconFile.setDateline(ins.getUploadTime());
				iconFile.setServer(ins.getServer());

				// 修改ins中封面冗余字段
				ins.setIconUrl("/" + ins.getServer() + "/" + ins.getIconUrl());
			}
			ins.setIconFile(iconFile);

			if (ins.getMobileSWFFile() == null && ins.getMobileSWFUrl() != null) {
				List<UploadFile> uploadFiles = uploadFileDao.findByProperty("fileUrl",
						ins.getMobileSWFUrl());
				if (uploadFiles.size() == 1) {
					ins.setMobileSWFFile(uploadFiles.get(0));
				}
			}
			if (ins.getMobile3DSWFFile() == null && ins.getMobile3DSWFUrl() != null) {
				List<UploadFile> uploadFiles = uploadFileDao.findByProperty("fileUrl",
						ins.getMobile3DSWFUrl());
				if (uploadFiles.size() == 1) {
					ins.setMobile3DSWFFile(uploadFiles.get(0));
				}
			}
			try {
				insDao.saveOrUpdate(ins);
			} catch (Exception e) {
			}
		}
	}

	public void docConverter() {
		ArrayList<String> whereClause = new ArrayList<String>();
		ArrayList<String> groupbyClause = new ArrayList<String>();
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put("id", "desc");
		List<Instruction> inses = insDao.findTopData(100000, whereClause, groupbyClause,
				orderbyClause);
		for (Instruction ins : inses) {
			SystemConstants.docConverter.push(new UploadFileConvertObject(ins.getInsFile(), this));
		}
	}

	public void brandMerge() {
		List<Brand> brands = brandDao.getAll();
		for (Brand brand : brands) {
			if (brand.getIconUrl() != null && !"".equals(brand.getIconUrl())) {
				int bid = Integer.parseInt(brand.getIconUrl());
				Brand mergeBrand = brandDao.get(bid);
				if (mergeBrand != null) {
					mergeBrand.setCount(mergeBrand.getCount() + brand.getCount());
					brandDao.save(mergeBrand);
					List<Instruction> inses = insDao.findByProperty("brand.id", bid);
					for (Instruction ins : inses) {
						ins.setBrand(mergeBrand);
						insDao.saveOrUpdate(ins);
					}
					brandDao.delete(brand);
				}
			}
		}
	}

	public int dataShift() {
		List<PhpcmsContent> pcs = phpcmsContentDao.getAll();
		for (PhpcmsContent pc : pcs) {
			PhpcmsCDownload download = pc.getDownload();
			if (download != null) {
				Instruction ins;
				if (pc.getShiftstatus() == 0)
					ins = new Instruction();
				else {
					List<Instruction> inses = insDao
							.findByProperty("uploadTime", pc.getInputtime());
					if (inses.size() > 0)
						ins = inses.get(0);
					else
						ins = new Instruction();
				}
				Short shiftstatus = 1;
				ins.setTitle(pc.getTitle());
				ins.setIconUrl(pc.getThumb());
				ins.setUploadTime(pc.getInputtime());
				ins.setUpdateTime(pc.getUpdatetime());
				ins.setCategory(categoryDao.get(pc.getCid()));
				pc.setShiftstatus(shiftstatus);
				phpcmsContentDao.save(pc);

				Set<File> files = new HashSet<File>();
				String[] fileSizeStrs = (download.getFilesize()).split(" ");
				int fileSize = 0;
				if (fileSizeStrs.length > 0) {
					try {
						fileSize = Integer.parseInt(fileSizeStrs[0]);
					} catch (NumberFormatException e) {

					}
				}
				String[] downurls = download.getDownurls().split("\n");
				for (String downurl : downurls) {
					String[] down = downurl.split("\\|");
					if (down.length >= 2) {
						File file = new File();
						file.setDescription(down[0]);
						file.setFileUrl(down[1].substring(11));
						file.setFileSize(fileSize);
						file.setInstruction(ins);
						files.add(file);
					}
				}
				ins.setDescription(download.getContent());
				ins.setInsFile(new UploadFile());
				ins.setUser(userDao.get(pc.getUid()));
				insDao.saveOrUpdate(ins);
			}
		}
		return SystemConstants.FEEDBACK.SUCCESS;
	}

	public int dataShiftOld() {
		List<PhpcmsContent> pcs = phpcmsContentDao.getAllNewContent();
		for (PhpcmsContent pc : pcs) {
			PhpcmsCDownload download = pc.getDownload();
			if (download != null) {
				Short shiftstatus = 1;
				Instruction ins = new Instruction();
				ins.setTitle(pc.getTitle());
				ins.setIconUrl(pc.getThumb());
				ins.setUploadTime(pc.getInputtime());
				ins.setUpdateTime(pc.getUpdatetime());
				ins.setCategory(categoryDao.get(pc.getCid()));
				if (pc.getBrand() == null)
					shiftstatus = 2;
				else {
					List<Brand> brands = brandDao.getByName(pc.getBrand());
					if (brands == null || brands.size() == 0) {
						Brand brand = new Brand();
						brand.setName(pc.getBrand());
						brand.setCount(1);
						brand.setIsHot(Short.valueOf((short) 0));
						brandDao.save(brand);
						ins.setBrand(brand);
					} else {
						Brand brand = brands.get(0);
						brand.setCount(brand.getCount() + 1);
						ins.setBrand(brands.get(0));
					}
				}
				pc.setShiftstatus(shiftstatus);
				phpcmsContentDao.save(pc);

				Set<File> files = new HashSet<File>();
				String[] fileSizeStrs = (download.getFilesize()).split(" ");
				int fileSize = 0;
				if (fileSizeStrs.length > 0) {
					try {
						fileSize = Integer.parseInt(fileSizeStrs[0]);
					} catch (NumberFormatException e) {

					}
				}
				String[] downurls = download.getDownurls().split("\n");
				for (String downurl : downurls) {
					String[] down = downurl.split("\\|");
					if (down.length >= 2) {
						File file = new File();
						file.setDescription(down[0]);
						file.setFileUrl(down[1]);
						file.setFileSize(fileSize);
						file.setInstruction(ins);
						files.add(file);
					}
				}
				ins.setDescription(download.getContent());
				ins.setInsFile(new UploadFile());
				insDao.saveOrUpdate(ins);
			}
		}
		this.brandMerge();
		return SystemConstants.FEEDBACK.SUCCESS;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setPhpcmsContentDao(PhpcmsDao phpcmsContentDao) {
		this.phpcmsContentDao = phpcmsContentDao;
	}

	public void setInsDao(InsDao insDao) {
		this.insDao = insDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUploadFileDao(UploadFileDao uploadFileDao) {
		this.uploadFileDao = uploadFileDao;
	}

	public void convertFinish(UploadFile insFile) {
		// TODO Auto-generated method stub

	}
}
