package instruction.model;

public class AdminStatistic {
	private int uid;
	private String username;
	private String smsphone;
	private String brandName;
	private int uploadCount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getUploadCount() {
		return uploadCount;
	}

	public void setUploadCount(int uploadCount) {
		this.uploadCount = uploadCount;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public void setSmsphone(String smsphone) {
		this.smsphone = smsphone;
	}

	public String getSmsphone() {
		return smsphone;
	}
}
