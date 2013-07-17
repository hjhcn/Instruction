package instruction.model;

import instruction.SystemConstants;
import instruction.util.FileUtils;
import instruction.util.Time;
import java.io.File;

import org.apache.struts2.json.annotations.JSON;

public class UploadFile implements java.io.Serializable {

	private static final long serialVersionUID = 8332332177086816801L;
	private Integer id;
	private String fileUrl;
	private Integer fileSize;
	private String description;
	private Integer dateline;
	private Short status;
	private String fileType;
	private String server;

	private User user;

	private File file;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JSON(serialize = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDateline(Integer dateline) {
		this.dateline = dateline;
	}

	public Integer getDateline() {
		return dateline;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * -2 文件已删除或存在问题<br>
	 * -1 正在转换(前提是文件是说明书，且正在等待或进行文件转换) <br>
	 * 0 上传至临时目录 <br>
	 * 1 已转存，但并不是说明书 <br>
	 * 2 说明书已转存，且转换SWF成功 <br>
	 * 3 说明书已转存，但转换SWF失败
	 */
	public Short getStatus() {
		return status;
	}

	public String getCreateTimeFormat() {
		return Time.formatTimeStamp(dateline, "yyyy-MM-dd HH:mm:ss");
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}

	/**
	 * @return 文件web地址，通过状态自动判断目录
	 */
	public String getWebUrl() {
		String forder = "/";
		switch (this.status) {
		case -1:
		case 0:
			forder += "uploadfileTemp/";
			break;

		default:
			forder += this.getServer() + "/";
			break;
		}
		return forder + this.getFileUrl();
	}

	public String getServer() {
		if (null == server || server.isEmpty())
			return SystemConstants.UPLOAD_FOLDER_SERVER;
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * 
	 * @return 文件最终绝对存储目录，并不一定是真实目录
	 */
	@JSON(serialize = false)
	public String getTempFolder() {
		return SystemConstants.UPLOAD_FOLDER_TEMP + this.getFileUrl();
	}

	@JSON(serialize = false)
	public String getFileFolder() {
		return this.getUploadFolder() + this.getFileUrl();
	}

	/**
	 * @return 若为说明书文件，返回转换后的swf文件个数
	 */
	@JSON(serialize = false)
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

	@JSON(serialize = false)
	public String getUploadFolder() {
		String uploadFolder = SystemConstants.UPLOAD_FOLDER;
		if (this.getServer().equals(SystemConstants.OLD_UPLOAD_FOLDER_SERVER)) {
			uploadFolder = SystemConstants.OLD_UPLOAD_FOLDER;
		}
		return uploadFolder;
	}

	@JSON(serialize = false)
	public File getFile() {
		if (null == file) {
			file = new File(this.getUploadFolder() + this.getFileUrl());
			if (file.exists()) {
				file = new File(this.getUploadFolder() + FileUtils.getPre(this.getFileUrl())
						+ ".swf");
			}
		}
		return file;
	}

}