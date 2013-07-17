package instruction.service;

import instruction.SystemConstants.CREDIT_TYPE;
import instruction.model.CreditLog;
import instruction.model.CreditRule;
import instruction.model.Instruction;
import instruction.model.User;
import instruction.util.page.PageView;

public interface CreditService {
	public PageView<CreditRule> listRule(int pageNum, int pageSize);

	public PageView<CreditLog> listLog(int crid, int uid, int startTimeStamp, int endTimeStamp,
			int page, int count, String order, String sort);

	public PageView<CreditLog> listNotificationLog(int crid, int uid, int startTimeStamp,
			int endTimeStamp, int pageNum, int pageSize);

	public boolean isLogExists(int crid, Instruction ins, User user);

	public int editRule(CreditRule creditRule);

	public int sumLog(int uid, int startTimeStamp, int endTimeStamp, CREDIT_TYPE type);

	/**
	 * 目前积分值和上限以积分给予时间为准,而不是积分事件时间
	 * 
	 * @param crid
	 * @param ins
	 * @param user
	 * @param dateline
	 * @return
	 */
	public int addCredit(int crid, Instruction ins, User user, int dateline);

	/**
	 * 目前积分值和上限以积分给予时间为准,而不是积分事件时间
	 * 
	 * 2013-06-26 暂时取消移动家庭终端说明书翻倍
	 * 
	 */
	public int addCredit(int crid, Instruction ins, User user, int credit, String description,
			int dateline);

	public int editRule(int crid, int credit, String description, int dayThreshold,
			int monthThreshold);

	public CreditRule get(int crid);

	public int getNewCount(int uid);

	public int readAll(int uid);

	public int reCredit(String ids);

	/**
	 * 2013-06-26 新增 再次请求来福币
	 * 
	 * @return 请求成功数量
	 */
	public int reCreditAll();

}
