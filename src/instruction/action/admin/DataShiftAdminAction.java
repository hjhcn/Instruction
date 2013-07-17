package instruction.action.admin;

import instruction.service.DataShiftService;
import instruction.service.FileService;

public class DataShiftAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = 6942307011274567292L;
	private DataShiftService dataShiftService;
	private FileService fileService;

	public String decompFile() {
		fileService.decompAndConverFile();
		return SUCCESS;
	}

	public String dataTransfer() {
		dataShiftService.dataTransfer();
		return SUCCESS;
	}

	public String docConverter() {
		dataShiftService.docConverter();
		return SUCCESS;
	}

	public String brandMerge() {
		dataShiftService.brandMerge();
		return SUCCESS;
	}

	public void setDataShiftService(DataShiftService dataShiftService) {
		this.dataShiftService = dataShiftService;
	}

	public DataShiftService getDataShiftService() {
		return dataShiftService;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public FileService getFileService() {
		return fileService;
	}
}
