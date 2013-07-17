package instruction.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhpcmsContent implements java.io.Serializable {

	private static final long serialVersionUID = -8469550259585767648L;
	private Short contentid;
	private Short catid;
	private Short typeid;
	private Short areaid;
	private String title;
	private String style;
	private String thumb;
	private String keywords;
	private String description;
	private Short posids;
	private String url;
	private Short listorder;
	private Short status;
	private Short userid;
	private String username;
	private Integer inputtime;
	private Integer updatetime;
	private Short searchid;
	private Short islink;
	private String prefix;
	private Short shiftstatus;
	private PhpcmsCDownload download;

	public Short getContentid() {
		return this.contentid;
	}

	public void setContentid(Short contentid) {
		this.contentid = contentid;
	}

	public Short getCatid() {
		return this.catid;
	}

	public void setCatid(Short catid) {
		this.catid = catid;
	}

	public Short getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Short typeid) {
		this.typeid = typeid;
	}

	public Short getAreaid() {
		return this.areaid;
	}

	public void setAreaid(Short areaid) {
		this.areaid = areaid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getThumb() {
		if (thumb.indexOf("uploadfile") >= 0)
			thumb = thumb.substring(11);
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getPosids() {
		return this.posids;
	}

	public void setPosids(Short posids) {
		this.posids = posids;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Short getListorder() {
		return this.listorder;
	}

	public void setListorder(Short listorder) {
		this.listorder = listorder;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getUserid() {
		return this.userid;
	}

	public void setUserid(Short userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(Integer inputtime) {
		this.inputtime = inputtime;
	}

	public Integer getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}

	public Short getSearchid() {
		return this.searchid;
	}

	public void setSearchid(Short searchid) {
		this.searchid = searchid;
	}

	public Short getIslink() {
		return this.islink;
	}

	public void setIslink(Short islink) {
		this.islink = islink;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getModel() {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
		Matcher matcher = pattern.matcher(title);
		String model = "";
		while (matcher.find()) {
			model = matcher.group();
		}
		System.out.println(model);
		return model;
	}

	public String getBrand() {
		String brand = null;
		title = title.replaceAll("\\s+", "");
		Pattern pattern = Pattern.compile("[a-zA-Z0-9-/]+");
		Matcher matcher = pattern.matcher(title);
		int modelstart = 0;
		while (matcher.find()) {
			modelstart = matcher.start();
		}
		if (modelstart > 0)
			brand = title.substring(0, modelstart);
		return brand;
	}

	public int getCid() {
		int cid = 1;
		if (title.indexOf("平板") >= 0) {
			cid = 5;
		} else if (title.indexOf("LED") >= 0)
			cid = 6;
		else if (title.indexOf("格力") >= 0)
			cid = 17;
		else if (title.indexOf("笔记本") >= 0)
			cid = 57;
		return cid;
	}

	public int getUid() {
		int uid = 2;
		if (username.indexOf("yujie") >= 0) {
			uid = 2;
		} else if (username.indexOf("liuqingyun") >= 0)
			uid = 3;
		else if (username.indexOf("yangyun") >= 0)
			uid = 4;
		else if (username.indexOf("yishanshan") >= 0)
			uid = 5;
		return uid;
	}

	public void setDownload(PhpcmsCDownload download) {
		this.download = download;
	}

	public PhpcmsCDownload getDownload() {
		return download;
	}

	public void setShiftstatus(Short shiftstatus) {
		this.shiftstatus = shiftstatus;
	}

	public Short getShiftstatus() {
		return shiftstatus;
	}
}