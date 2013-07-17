package instruction.model;

public class PhpcmsCDownload implements java.io.Serializable {

	private static final long serialVersionUID = -4251642402034817057L;
	private Short contentid;
	private String template;
	private String content;
	private String downurls;
	private String filesize;
	private String version;
	private String classtype;
	private String language;
	private String copytype;
	private String stars;
	private Short groupidsView;
	private Integer readpoint;
	private String downurl;
	private PhpcmsContent pc;

	public Short getContentid() {
		return this.contentid;
	}

	public void setContentid(Short contentid) {
		this.contentid = contentid;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDownurls() {
		return this.downurls;
	}

	public void setDownurls(String downurls) {
		this.downurls = downurls;
	}

	public String getFilesize() {
		return this.filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getClasstype() {
		return this.classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCopytype() {
		return this.copytype;
	}

	public void setCopytype(String copytype) {
		this.copytype = copytype;
	}

	public String getStars() {
		return this.stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public Short getGroupidsView() {
		return this.groupidsView;
	}

	public void setGroupidsView(Short groupidsView) {
		this.groupidsView = groupidsView;
	}

	public Integer getReadpoint() {
		return this.readpoint;
	}

	public void setReadpoint(Integer readpoint) {
		this.readpoint = readpoint;
	}

	public String getDownurl() {
		return this.downurl;
	}

	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}

	public void setPc(PhpcmsContent pc) {
		this.pc = pc;
	}

	public PhpcmsContent getPc() {
		return pc;
	}

}