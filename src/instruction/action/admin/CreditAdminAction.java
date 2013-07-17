package instruction.action.admin;

import instruction.SystemConstants;
import instruction.model.CreditLog;
import instruction.model.CreditRule;
import instruction.model.User;
import instruction.service.CreditService;
import instruction.service.UserService;
import instruction.util.Time;
import instruction.util.page.PageView;

public class CreditAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = 1979680168642525015L;
	private CreditService creditService;
	private UserService userService;
	private int startTimeStamp = SystemConstants.OPENING_TIMESTAMP;
	private int endTimeStamp = Time.getTimeStamp();
	private int uid = 0;
	private int crid = 0;
	private CreditRule creditRule;
	private PageView<CreditRule> creditRules;
	private PageView<CreditLog> creditLogs;
	private int credit;
	private String description;
	private int dayThreshold;
	private int monthThreshold;
	private String ids;

	public String manual() {
		User user = null;
		if (uid > 0 && (user = userService.get(uid)) != null) {
			feedback = creditService.addCredit(8, null, user, credit, description + "(来自管理员"
					+ admin.getUid() + "@" + admin.getUsername() + "操作)", Time.getTimeStamp());
		} else
			feedback = SystemConstants.FEEDBACK.UID_ERROR;
		return SUCCESS;
	}

	public String reCredit() {
		feedback = creditService.reCredit(ids);
		return SUCCESS;
	}

	public String creditRule() {
		creditRule = creditService.get(crid);
		return SUCCESS;
	}

	public String listRule() {
		creditRules = creditService.listRule(page, rows);
		return SUCCESS;
	}

	public String listLog() {
		creditLogs = creditService.listLog(crid, uid, startTimeStamp, endTimeStamp, page, rows,
				order, sort);
		return SUCCESS;
	}

	public String editRule() {
		feedback = creditService.editRule(crid, credit, description, dayThreshold, monthThreshold);
		return SUCCESS;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public CreditService getCreditService() {
		return creditService;
	}

	public void setCreditLogs(PageView<CreditLog> creditLogs) {
		this.creditLogs = creditLogs;
	}

	public PageView<CreditLog> getCreditLogs() {
		return creditLogs;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public CreditRule getCreditRule() {
		return creditRule;
	}

	public void setCreditRule(CreditRule creditRule) {
		this.creditRule = creditRule;
	}

	public int getStartTimeStamp() {
		return startTimeStamp;
	}

	public void setStartTimeStamp(int startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}

	public int getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(int endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public PageView<CreditRule> setCreditRules(PageView<CreditRule> creditRules) {
		this.creditRules = creditRules;
		return creditRules;
	}

	public PageView<CreditRule> getCreditRules() {
		return creditRules;
	}

	public int getCrid() {
		return crid;
	}

	public void setCrid(int crid) {
		this.crid = crid;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDayThreshold() {
		return dayThreshold;
	}

	public void setDayThreshold(int dayThreshold) {
		this.dayThreshold = dayThreshold;
	}

	public void setMonthThreshold(int monthThreshold) {
		this.monthThreshold = monthThreshold;
	}

	public int getMonthThreshold() {
		return monthThreshold;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}


}
