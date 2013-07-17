package instruction.action;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.SystemConstants.STATUS;
import instruction.model.BBSThread;
import instruction.model.Category;
import instruction.model.CreditRule;
import instruction.model.Daren;
import instruction.model.File;
import instruction.model.InsUpload;
import instruction.model.Instruction;
import instruction.model.UploadFile;
import instruction.service.BBSThreadService;
import instruction.service.CategoryService;
import instruction.service.CreditService;
import instruction.service.InsService;
import instruction.util.FileUtils;
import instruction.util.page.PageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class InsAction extends SessionBaseAction {

	private static final long serialVersionUID = 3176034408820731405L;
	private InsService insService;
	private CategoryService categoryService;
	private BBSThreadService bbsThreadService;
	private CreditService creditService;
	private int id;
	private Instruction ins;
	private PageView<Instruction> inses;
	private int cid = 0;
	private int bid = 0;
	private List<BBSThread> threads;
	private List<Category> cates;
	private String search;
	private InsUpload insUpload;
	private java.io.File file;
	private String fileFileName;
	private String fileFolderAndName;
	private List<Daren> darens = SystemConstants.darens;
	private InputStream fileDownload;
	private String filename;
	private String contentType;
	private int credit;// 上传给予积分
	private int mobile3d;// 是否查找3d

	public String judgeModelAuth() {
		feedback = insService.judageModel(insUpload);
		return SUCCESS;
	}

	public String ins() {
		ins = insService.get(id, SystemConstants.STATUS.PASS);
		if (ins != null) {
			for (File file : ins.getFiles()) {
				if (null == file.getDescription() || "".equals(file.getDescription())) {
					file.setDescription(ins.getTitle());
				}
			}
			cates = categoryService.findRelated(ins.getCategory());
		}
		threads = bbsThreadService.findTop(10);
		return SUCCESS;
	}

	public String list() {
		boolean isMobile3d = false;
		if (mobile3d == 1)
			isMobile3d = true;
		inses = insService.findScrollData(0, cid, bid, search, null, page, rows, order, sort,
				SystemConstants.STATUS.PASS, isMobile3d);
		cates = categoryService.getTree(0);
		threads = bbsThreadService.findTop(10);
		return SUCCESS;
	}

	public String listInsOfUserAuth() {
		inses = insService.findScrollData(userSession.getUid(), page, rows, order, sort,
				SystemConstants.STATUS.SHOWALL);
		threads = bbsThreadService.findTop(10);
		return SUCCESS;
	}

	public String uploadAuth() {
		feedback = insService.operate(userSession.getUid(), insUpload, STATUS.UNVERIFY,
				OPERATE.USER_UPLOAD);
		return SUCCESS;
	}

	public String downloadAuth() {
		UploadFile downloadFile = insService.download(id, userSession.getUid());
		if (downloadFile != null) {
			try {
				filename = new String((downloadFile.getDescription()).getBytes(), "ISO8859-1");
				try {
					fileDownload = new FileInputStream(downloadFile.getUploadFolder()
							+ downloadFile.getFileUrl());
					String ext = FileUtils.getExt(downloadFile.getFileUrl());
					filename = filename + "." + ext;
				} catch (FileNotFoundException e) {
					try {
						fileDownload = new FileInputStream(downloadFile.getUploadFolder()
								+ FileUtils.getPre(downloadFile.getFileUrl()) + ".swf");
						filename = filename + ".swf";
						contentType = "application/x-shockwave-flash";
					} catch (FileNotFoundException fileEx) {
						e.printStackTrace();
					}
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "file";
	}

	public String uploadPageAuth() {
		threads = bbsThreadService.findTop(10);
		cates = categoryService.getTree(0);
		CreditRule creditRule = creditService.get(4);
		this.setCredit(creditRule.getCredit());
		return SUCCESS;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getCid() {
		return cid;
	}

	public int getBid() {
		return bid;
	}

	public Instruction getIns() {
		return ins;
	}

	public void setInsService(InsService insService) {
		this.insService = insService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PageView<Instruction> getInses() {
		return inses;
	}

	public void setThreads(List<BBSThread> threads) {
		this.threads = threads;
	}

	public List<BBSThread> getThreads() {
		return threads;
	}

	public List<Category> getCates() {
		return cates;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String getSearch() {
		return search;
	}

	public InsUpload getInsUpload() {
		return insUpload;
	}

	public void setInsUpload(InsUpload insUpload) {
		this.insUpload = insUpload;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFolderAndName() {
		return fileFolderAndName;
	}

	public void setFileFolderAndName(String fileFolderAndName) {
		this.fileFolderAndName = fileFolderAndName;
	}

	public java.io.File getFile() {
		return file;
	}

	public void setFile(java.io.File file) {
		this.file = file;
	}

	public void setDarens(List<Daren> darens) {
		this.darens = darens;
	}

	public List<Daren> getDarens() {
		return darens;
	}

	public void setFileDownload(InputStream fileDownload) {
		this.fileDownload = fileDownload;
	}

	public InputStream getFileDownload() {
		return fileDownload;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setBbsThreadService(BBSThreadService bbsThreadService) {
		this.bbsThreadService = bbsThreadService;
	}

	public BBSThreadService getBbsThreadService() {
		return bbsThreadService;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public CreditService getCreditService() {
		return creditService;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getCredit() {
		return credit;
	}

	public int getMobile3d() {
		return mobile3d;
	}

	public void setMobile3d(int mobile3d) {
		this.mobile3d = mobile3d;
	}

}
