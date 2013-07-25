package instruction.model;

import instruction.util.Time;

import org.apache.struts2.json.annotations.JSON;

public class Instruction implements java.io.Serializable {

	private static final long serialVersionUID = 3254988447778086189L;
	private int id;
	private Short status;
	private String model;
	private String title;
	private String description;
	private Integer credit;
	private Integer uploadTime;
	private Integer updateTime;
	private Integer viewCount;
	private Integer downloadCount;
	private Integer commentCount;
	private Integer commentGrade;
	private String editUids;
	private Short isUserUpload;
	private Integer order;
	private String g3Url;
	private String server;

	// 冗余字段
	private String iconUrl;
	private String mobileSWFUrl;
	private String mobile3DSWFUrl;
	private String fileType;

	private User user;
	private Brand brand;
	private Category category;
	private UploadFile iconFile;
	private UploadFile insFile;
	private UploadFile mobileSWFFile;
	private UploadFile mobile3DSWFFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return 说明书状态 <br/>
	 *         -2说明书文件不存在<br/>
	 *         -1说明书正在转换<br/>
	 *         0 未审核<br/>
	 *         1 审核通过<br/>
	 */
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public Integer getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Integer uploadTime) {
		this.uploadTime = uploadTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getCommentGrade() {
		return this.commentGrade;
	}

	public void setCommentGrade(Integer commentGrade) {
		this.commentGrade = commentGrade;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getCredit() {
		return credit;
	}

	public String getUpdateTimeFormat() {
		return Time.formatTimeStamp(updateTime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getUploadTimeFormat() {
		return Time.formatTimeStamp(uploadTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setEditUids(String editUids) {
		this.editUids = editUids;
	}

	public String getEditUids() {
		return editUids;
	}

	public void setIsUserUpload(Short isUserUpload) {
		this.isUserUpload = isUserUpload;
	}

	public Short getIsUserUpload() {
		return isUserUpload;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getOrder() {
		return order;
	}

	public void setG3Url(String g3Url) {
		this.g3Url = g3Url;
	}

	public String getG3Url() {
		return g3Url;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getServer() {
		return server;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}

	public String getMobileSWFUrl() {
		return mobileSWFUrl;
	}

	public void setMobileSWFUrl(String mobileSWFUrl) {
		this.mobileSWFUrl = mobileSWFUrl;
	}

	public String getMobile3DSWFUrl() {
		return mobile3DSWFUrl;
	}

	public void setMobile3DSWFUrl(String mobile3dSWFUrl) {
		this.mobile3DSWFUrl = mobile3dSWFUrl;
	}

	@JSON(serialize = false)
	public UploadFile getIconFile() {
		return iconFile;
	}

	public void setIconFile(UploadFile iconFile) {
		this.iconFile = iconFile;
	}

	@JSON(serialize = false)
	public UploadFile getInsFile() {
		return insFile;
	}

	public void setInsFile(UploadFile insFile) {
		this.insFile = insFile;
	}

	@JSON(serialize = false)
	public UploadFile getMobileSWFFile() {
		return mobileSWFFile;
	}

	public void setMobileSWFFile(UploadFile mobileSWFFile) {
		this.mobileSWFFile = mobileSWFFile;
	}

	@JSON(serialize = false)
	public UploadFile getMobile3DSWFFile() {
		return mobile3DSWFFile;
	}

	public void setMobile3DSWFFile(UploadFile mobile3dswfFile) {
		mobile3DSWFFile = mobile3dswfFile;
	}

}