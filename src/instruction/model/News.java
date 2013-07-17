package instruction.model;

import instruction.util.Time;

public class News implements java.io.Serializable {

	private static final long serialVersionUID = -485843562318745096L;
	private int id;
	private String title;
	private String content;
	private String description;
	private Integer uploadTime;
	private Integer updateTime;
	private Integer viewCount;
	private Short status;
	private String coverUrl;

	private User user;
	private UploadFile coverFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Integer uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getStatus() {
		return status;
	}

	public String getUpdateTimeFormat() {
		return Time.formatTimeStamp(updateTime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getUploadTimeFormat() {
		return Time.formatTimeStamp(uploadTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionHtml() {
		if (null != description && description.length() > 200) {
			return description.substring(0, 200) + "...";
		}
		return description;
	}

	public void setCoverUrl(String cover) {
		this.coverUrl = cover;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public UploadFile getCoverFile() {
		return coverFile;
	}

	public void setCoverFile(UploadFile coverFile) {
		this.coverFile = coverFile;
	}

}
