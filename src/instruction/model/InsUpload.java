package instruction.model;

public class InsUpload {
	private int id = 0;
	private String model;
	private String title;
	private String description;
	private int cid = 0;
	private int bid = 0;
	private String g3Url;

	private int iconUfid;
	private int insUfid;
	private int mobileUfid;
	private int mobile3DUfid;

	public int getMobileUfid() {
		return mobileUfid;
	}

	public void setMobileUfid(int mobileUfid) {
		this.mobileUfid = mobileUfid;
	}

	public int getMobile3DUfid() {
		return mobile3DUfid;
	}

	public void setMobile3DUfid(int mobile3dUfid) {
		mobile3DUfid = mobile3dUfid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBid() {
		return bid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCid() {
		return cid;
	}

	public void setG3Url(String g3Url) {
		this.g3Url = g3Url;
	}

	public String getG3Url() {
		return g3Url;
	}

	public int getInsUfid() {
		return insUfid;
	}

	public void setInsUfid(int insUfid) {
		this.insUfid = insUfid;
	}

	public int getIconUfid() {
		return iconUfid;
	}

	public void setIconUfid(int iconUfid) {
		this.iconUfid = iconUfid;
	}
}
