package instruction.model;

import instruction.util.Time;

import org.apache.struts2.json.annotations.JSON;

public class Admin implements java.io.Serializable {
	private static final long serialVersionUID = -927454814798101360L;
	private int uid;
	private String username;
	private String password;
	private int alloweditpassword;
	private int disabled;
	private int role;
	private int loginTime;
	private String loginIp;
	private int lastTime;
	private String lastIp;
	private User user;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAlloweditpassword() {
		return alloweditpassword;
	}

	public void setAlloweditpassword(int alloweditpassword) {
		this.alloweditpassword = alloweditpassword;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSON(serialize = false)
	public User getUser() {
		return user;
	}

	public int getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public String getFormatLoginTime() {
		return Time.formatTimeStamp(loginTime, "yyyy-MM-dd hh:mm:ss");
	}

	public String getFormatLastTime() {
		return Time.formatTimeStamp(lastTime, "yyyy-MM-dd hh:mm:ss");
	}
}
