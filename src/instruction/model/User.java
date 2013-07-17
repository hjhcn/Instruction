package instruction.model;

import java.util.Set;
import java.util.UUID;

import org.apache.struts2.json.annotations.JSON;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = -4871637597338867318L;
	private int uid;
	private String username;
	private String smsphone;
	private String email;
	private Integer commentedCount;
	private Integer commentCount;
	private Integer downloadCount;
	private Integer downloadedCount;
	private Integer credit;
	private Integer dateline;
	private Integer regtime;
	private Integer logintime;
	private String regip;
	private String lastip;
	/**
	 * APP身份验证标记
	 */
	private String token;
	/**
	 * 浏览器cookie验证标记
	 */
	private String cookie;

	private Set<Instruction> instructions;
	private Set<Comment> comments;
	private Set<DownloadLog> downloadLogs;

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCommentedCount() {
		return this.commentedCount;
	}

	public void setCommentedCount(Integer commentedCount) {
		this.commentedCount = commentedCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Integer getDownloadedCount() {
		return this.downloadedCount;
	}

	public void setDownloadedCount(Integer downloadedCount) {
		this.downloadedCount = downloadedCount;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getDateline() {
		return this.dateline;
	}

	public void setDateline(Integer dateline) {
		this.dateline = dateline;
	}

	public Integer getRegtime() {
		return this.regtime;
	}

	public void setRegtime(Integer regtime) {
		this.regtime = regtime;
	}

	public Integer getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}

	public String getRegip() {
		return this.regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public void setInstructions(Set<Instruction> instructions) {
		this.instructions = instructions;
	}

	@JSON(serialize = false)
	public Set<Instruction> getInstructions() {
		return instructions;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@JSON(serialize = false)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip;
	}

	public String getLastip() {
		return lastip;
	}

	public void setSmsphone(String smsphone) {
		this.smsphone = smsphone;
	}

	public String getSmsphone() {
		return smsphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDownloadLogs(Set<DownloadLog> downloadLogs) {
		this.downloadLogs = downloadLogs;
	}

	@JSON(serialize = false)
	public Set<DownloadLog> getDownloadLogs() {
		return downloadLogs;
	}

	/**
	 * 生成token
	 * 
	 * @return token
	 */
	public String generateToken() {
		UUID uuid = UUID.randomUUID();
		token = uuid.toString();
		token = token.replaceAll("-", "");
		return token;
	}

	@JSON(serialize = false)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 生成cookie
	 * 
	 * @return cookie
	 */
	public String generateCookie() {
		UUID uuid = UUID.randomUUID();
		cookie = uuid.toString();
		cookie = cookie.replaceAll("-", "");
		return cookie;
	}

	@JSON(serialize = false)
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}