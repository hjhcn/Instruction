package instruction.action;

import java.util.List;

import instruction.model.BBSThread;
import instruction.model.CreditLog;
import instruction.service.BBSThreadService;
import instruction.service.CreditService;
import instruction.util.Time;
import instruction.util.page.PageView;

public class NotificationAction extends SessionBaseAction {

	private static final long serialVersionUID = 3176034408820731405L;
	private CreditService creditService;
	private int startTime = 0;
	private int endTime = Time.getTimeStamp();
	private int newCount = 0;
	private PageView<CreditLog> creditLogs;
	private BBSThreadService bbsThreadService;
	private List<BBSThread> threads;

	public String listAuth() {
		threads = bbsThreadService.findTop(10);
		setCreditLogs(creditService.listNotificationLog(0, userSession.getUid(), startTime,
				endTime, page, rows));
		return SUCCESS;
	}

	public String newCountAuth() {
		newCount = creditService.getNewCount(userSession.getUid());
		return SUCCESS;
	}

	public String readAllAuth() {
		newCount = creditService.readAll(userSession.getUid());
		return SUCCESS;
	}

	public CreditService getCreditService() {
		return creditService;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public void setCreditLogs(PageView<CreditLog> creditLogs) {
		this.creditLogs = creditLogs;
	}

	public PageView<CreditLog> getCreditLogs() {
		return creditLogs;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public BBSThreadService getBbsThreadService() {
		return bbsThreadService;
	}

	public void setBbsThreadService(BBSThreadService bbsThreadService) {
		this.bbsThreadService = bbsThreadService;
	}

	public List<BBSThread> getThreads() {
		return threads;
	}

	public void setThreads(List<BBSThread> threads) {
		this.threads = threads;
	}

	public int getNewCount() {
		return newCount;
	}

}
