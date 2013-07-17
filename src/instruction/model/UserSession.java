package instruction.model;

public class UserSession {
	private int uid;
	private String username;
	private String smsphone;
	private String email;
	private int credit;
	private String ip;
	private int lfb;

	public UserSession() {

	}

	/**
	 * 此构造方法需获取用户信息并修改lastIp后使用
	 */
	public UserSession(User user) {
		this.uid = user.getUid();
		this.username = user.getUsername();
		this.smsphone = user.getSmsphone();
		this.email = user.getEmail();
		this.credit = user.getCredit();
		this.ip = user.getLastip();
		this.lfb = 0;
	}

	public UserSession(User user, int lfb) {
		this.uid = user.getUid();
		this.username = user.getUsername();
		this.smsphone = user.getSmsphone();
		this.email = user.getEmail();
		this.credit = user.getCredit();
		this.ip = user.getLastip();
		this.lfb = lfb;
	}

	public int getLfb() {
		return lfb;
	}

	public void setLfb(int lfb) {
		this.lfb = lfb;
	}

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

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSmsphone() {
		return smsphone;
	}

	public void setSmsphone(String smsphone) {
		this.smsphone = smsphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
