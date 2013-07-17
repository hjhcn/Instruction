package instruction.model;

import instruction.SystemConstants;

public class File implements java.io.Serializable {

	private static final long serialVersionUID = 8332332177086816801L;
	private int id;
	private String fileUrl;
	private Integer fileSize;
	private String description;
	private Instruction instruction;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public int getSwfCount() {
		try {
			java.io.File swfDir = new java.io.File(this.getUploadFolder()
					+ fileUrl.substring(0, fileUrl.lastIndexOf(".")) + "_SWF");
			if (swfDir.exists() && swfDir.isDirectory())
				return swfDir.list().length;
			java.io.File swfFile = new java.io.File(this.getUploadFolder()
					+ fileUrl.substring(0, fileUrl.lastIndexOf(".")) + ".swf");
			if (swfFile.exists())
				return -1;
			// -1表示老转换方式中的单个swf文件，用于swf展示时与新方式区分
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getUploadFolder() {
		String uploadFolder = SystemConstants.UPLOAD_FOLDER;
		if (instruction.getServer().equals(
				SystemConstants.OLD_UPLOAD_FOLDER_SERVER)) {
			uploadFolder = SystemConstants.OLD_UPLOAD_FOLDER;
		}
		return uploadFolder;
	}

	public String getRealPath() {
		return this.getUploadFolder() + this.getFileUrl();
	}
}