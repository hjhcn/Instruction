package instruction.model;

import org.apache.struts2.json.annotations.JSON;

import instruction.util.Time;

public class CreditLog implements java.io.Serializable {
	private static final long serialVersionUID = -2575086359626766097L;
	private Integer id;
	private Integer credit;
	private Integer createTime;
	private Integer dateline;
	private String description;
	private Short isSync;
	private Short isNotified;
	private User user;
	private CreditRule creditRule;
	private Instruction instruction;
	private String syncMessage;
	private String orderNumber;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	/**
	 * 积分计算时间
	 */
	public Integer getDateline() {
		return this.dateline;
	}

	public void setDateline(Integer dateline) {
		this.dateline = dateline;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setCreditRule(CreditRule creditRule) {
		this.creditRule = creditRule;
	}

	@JSON(serialize = false)
	public CreditRule getCreditRule() {
		return creditRule;
	}

	public String getDatelineFormat() {
		return Time.formatTimeStamp(dateline, "yyyy-MM-dd HH:mm:ss");
	}

	public String getCreateTimeFormat() {
		return Time.formatTimeStamp(createTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	@JSON(serialize = false)
	public Instruction getInstruction() {
		return instruction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getIsSync() {
		return isSync;
	}

	public void setIsSync(Short isSync) {
		this.isSync = isSync;
	}

	public void setIsNotified(Short isNotified) {
		this.isNotified = isNotified;
	}

	public Short getIsNotified() {
		return isNotified;
	}

	/**
	 * 记录生成时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getSyncMessage() {
		return syncMessage;
	}

	public void setSyncMessage(String syncMessage) {
		this.syncMessage = syncMessage;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}