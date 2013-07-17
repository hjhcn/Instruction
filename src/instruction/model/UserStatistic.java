package instruction.model;

public class UserStatistic {
	private User user;
	private int creditAdd;
	private int creditCut;
	private int insAdd;
	private int insPass;
	private int comAdd;
	private int comPass;

	public int getCreditAdd() {
		return creditAdd;
	}

	public void setCreditAdd(int creditAdd) {
		this.creditAdd = creditAdd;
	}

	public int getCreditCut() {
		return creditCut;
	}

	public void setCreditCut(int creditCut) {
		this.creditCut = creditCut;
	}

	public int getInsAdd() {
		return insAdd;
	}

	public void setInsAdd(int insAdd) {
		this.insAdd = insAdd;
	}

	public int getInsPass() {
		return insPass;
	}

	public void setInsPass(int insPass) {
		this.insPass = insPass;
	}

	public int getComAdd() {
		return comAdd;
	}

	public void setComAdd(int comAdd) {
		this.comAdd = comAdd;
	}

	public int getComPass() {
		return comPass;
	}

	public void setComPass(int comPass) {
		this.comPass = comPass;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
