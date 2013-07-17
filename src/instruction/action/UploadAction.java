package instruction.action;

import instruction.SystemConstants.EXT_TYPE;
import instruction.model.UploadFile;
import instruction.rules.UploadFileRule;
import instruction.service.UploadFileService;
import instruction.util.page.PageView;

import java.io.File;

public class UploadAction extends SessionBaseAction implements UploadFileRule {

	private static final long serialVersionUID = 2352624521621547644L;
	private File file;
	private String fileFileName;
	private String fileContentType;

	private UploadFileService uploadFileService;
	private PageView<UploadFile> uploadFiles;
	private String statuses;
	private EXT_TYPE ext;
	private String search;
	private UploadFile uploadFile;

	public String uploadFileAuth() {
		uploadFile = uploadFileService.uploadFile(file, fileFileName, fileContentType,
				userSession.getUid(), EXT_TYPE.ALL);
		feedback = uploadFile.getId();
		return SUCCESS;
	}

	public String listAuth() {
		uploadFiles = uploadFileService.list(userSession.getUid(), statuses, search,
				tsRange.getStartTimeStamp(), tsRange.getEndTimeStamp(), page, rows, order, sort,
				ext);
		return SUCCESS;
	}

	public String delete() {

		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setUploadFileService(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	public void setUploadFiles(PageView<UploadFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public PageView<UploadFile> getUploadFiles() {
		return uploadFiles;
	}

	public UploadFileService getUploadFileService() {
		return uploadFileService;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setExt(EXT_TYPE ext) {
		this.ext = ext;
	}

	public EXT_TYPE getExt() {
		return ext;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}
