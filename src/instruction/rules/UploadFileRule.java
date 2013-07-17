package instruction.rules;

import instruction.service.UploadFileService;

import java.io.File;

public interface UploadFileRule {
	public void setFile(File file);

	public void setFileFileName(String fileFileName);

	public void setFileContentType(String fileContentType);

	public void setUploadFileService(UploadFileService uploadFileService);
}
