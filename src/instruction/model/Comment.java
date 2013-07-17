package instruction.model;

import instruction.util.Time;

import org.apache.struts2.json.annotations.JSON;

public class Comment implements java.io.Serializable {
	private static final long serialVersionUID = -6750442292196336424L;
	private int id;
	private String username;
	private Short grade;
	private String content;
	private Integer dateline;
	private String ip;
	/**
	 * 标记评论给予积分的状态 0：未操作 1：不给于积分 2：给予积分 3 : 给予积分，但未成功
	 */
	private Short creditStatus;
	private String creditDesc;
	private User user;
	private Instruction instruction;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Short getGrade() {
		return this.grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDateline() {
		return this.dateline;
	}

	public String getTimeFormat() {
		return Time.formatTimeStamp(dateline, "yyyy-MM-dd HH:mm:ss");
	}

	public void setDateline(Integer dateline) {
		this.dateline = dateline;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@JSON(serialize = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSON(serialize = false)
	public Instruction getInstruction() {
		return instruction;
	}

	public String getInsTitle() {
		return instruction != null ? instruction.getTitle() : "";
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public int getUid() {
		return user.getUid();
	}

	public void setCreditStatus(Short creditStatus) {
		this.creditStatus = creditStatus;
	}

	public Short getCreditStatus() {
		return creditStatus;
	}

	public String getCreditDesc() {
		return creditDesc;
	}

	public void setCreditDesc(String creditDesc) {
		this.creditDesc = creditDesc;
	}

}