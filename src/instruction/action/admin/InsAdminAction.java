package instruction.action.admin;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.SystemConstants.STATUS;
import instruction.model.AdminStatistic;
import instruction.model.Brand;
import instruction.model.Category;
import instruction.model.InsUpload;
import instruction.model.Instruction;
import instruction.service.CategoryService;
import instruction.service.InsService;
import instruction.util.FileUtils;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import com.opensymphony.xwork2.Preparable;

public class InsAdminAction extends BaseAdminAction implements Preparable {

	private static final long serialVersionUID = 1L;
	private InsService insService;
	private CategoryService categoryService;
	private PageView<Instruction> inses;
	private int id;
	private Instruction ins;
	private int cid = 0;
	private int bid = 0;
	private Category category;
	private Brand brand;
	private String search;
	private List<Brand> brands;
	private List<Category> categorys;
	private InsUpload insUpload;
	private File file;
	private String fileFileName;
	private String fileFolderAndName;
	private Short status = STATUS.UNVERIFY;
	private String ids;
	private List<AdminStatistic> adminStatistics;
	private int startTime = 1262304000;// 2010-01-01
	private int endTime = Time.getTimeStamp();
	private int adminUid = 0;
	private List<String> fileList;
	private String tempFileName;
	private String orderStr;
	private InputStream fileExport;
	private int uid;
	private SystemConstants.OPERATE operate;


	public void prepare() {
		// 预定义参数
		sort = "uploadTime";
	}

	public String verifyCountBath() {
		insService.verifyCountBath();
		return SUCCESS;
	}

	public String dataExport() {
		fileFileName = insService.dataExport(startTime, endTime);
		try {
			fileFileName = new String(fileFileName.getBytes(), "ISO8859-1");
			try {
				fileExport = new FileInputStream(SystemConstants.DATA_TO_EXCEL_FOLDER
						+ fileFileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String exportPage() {
		return SUCCESS;
	}

	public String list() {
		// 待优化
		categorys = categoryService.getTree(0);
		inses = insService.findScrollData(uid, cid, bid, search, tsRange, page, rows, order, sort,
				STATUS.UNVERIFY, false);
		return SUCCESS;
	}

	public String edit() {
		feedback = insService.operate(admin.getUid(), insUpload, status, OPERATE.EDIT);
		return SUCCESS;
	}

	public String upload() {
		feedback = insService.operate(admin.getUid(), insUpload, status, OPERATE.ADMIN_UPLOAD);
		return SUCCESS;
	}

	public String operate() {
		feedback = insService.operate(ids, operate);
		return SUCCESS;
	}

	public String reCoverter() {
		feedback = insService.reCoverter(id);
		return SUCCESS;
	}

	public String reOrder() {
		insService.reOrder(orderStr);
		return SUCCESS;
	}

	public String judageModel() {
		feedback = insService.judageModel(insUpload);
		return SUCCESS;
	}

	public String uploadPage() {
		categorys = categoryService.getTree(0);
		return SUCCESS;
	}

	public String ins() {
		// 待优化
		status = STATUS.SHOWALL;
		ins = insService.get(id, status);
		return SUCCESS;
	}

	public String uploadFile() {
		String ext = FileUtils.getExt(fileFileName);
		String newFileName = UUID.randomUUID() + "." + ext;
		String folder = FileUtils.getFileFolderByNow();
		// 添加server目录，增加137服务器共享空间
		// 2012-11-22
		fileFolderAndName = folder + newFileName;
		folder = SystemConstants.UPLOAD_FOLDER_TEMP + folder;
		FileUtils.upload(folder, newFileName, file);
		return SUCCESS;
	}

	public String selectTempFile() {
		String ext = FileUtils.getExt(fileFileName);
		String newFileName = UUID.randomUUID() + "." + ext;
		String folder = FileUtils.getFileFolderByNow();
		fileFolderAndName = folder + newFileName;
		File newDir = new File(SystemConstants.UPLOAD_FOLDER_TEMP + folder);
		if (!newDir.exists()) {
			newDir.mkdirs();
		}
		FileUtils.movePath(SystemConstants.UPLOAD_FOLDER_TEMP + admin.getUsername() + "/"
				+ tempFileName, SystemConstants.UPLOAD_FOLDER_TEMP + fileFolderAndName);
		return SUCCESS;
	}

	public String uploadTemp() {
		fileList = FileUtils.getRooFileList(SystemConstants.UPLOAD_FOLDER_TEMP
				+ admin.getUsername());
		return SUCCESS;
	}

	public String statistic() {
		if (endTime == 0)
			endTime = Time.getTimeStamp();
		adminStatistics = insService.statistic(startTime, endTime, adminUid);
		return SUCCESS;
	}

	public void setInsService(InsService insService) {
		this.insService = insService;
	}

	public PageView<Instruction> getInses() {
		return inses;
	}

	public void setInses(PageView<Instruction> inses) {
		this.inses = inses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instruction getIns() {
		return ins;
	}

	public void setIns(Instruction ins) {
		this.ins = ins;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public InsService getInsService() {
		return insService;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public void setInsUpload(InsUpload insUpload) {
		this.insUpload = insUpload;
	}

	public InsUpload getInsUpload() {
		return insUpload;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFolderAndName(String fileFolderAndName) {
		this.fileFolderAndName = fileFolderAndName;
	}

	public String getFileFolderAndName() {
		return fileFolderAndName;
	}

	public InsUpload getModel() {
		return insUpload;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getStatus() {
		return status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<AdminStatistic> getAdminStatistics() {
		return adminStatistics;
	}

	public void setAdminStatistics(List<AdminStatistic> adminStatistics) {
		this.adminStatistics = adminStatistics;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public void setAdminUid(int adminUid) {
		this.adminUid = adminUid;
	}

	public int getAdminUid() {
		return adminUid;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public List<String> getFileList() {
		return fileList;
	}

	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}

	public String getTempFileName() {
		return tempFileName;
	}

	public void setFileExport(InputStream fileExport) {
		this.fileExport = fileExport;
	}

	public InputStream getFileExport() {
		return fileExport;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public SystemConstants.OPERATE getOperate() {
		return operate;
	}

	public void setOperate(SystemConstants.OPERATE operate) {
		this.operate = operate;
	}

}
