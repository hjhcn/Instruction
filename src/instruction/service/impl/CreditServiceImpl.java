package instruction.service.impl;

import instruction.SystemConstants;
import instruction.SystemConstants.CREDIT_TYPE;
import instruction.dao.CreditLogDao;
import instruction.dao.CreditRuleDao;
import instruction.model.CreditLog;
import instruction.model.CreditRule;
import instruction.model.Instruction;
import instruction.model.User;
import instruction.service.CreditService;
import instruction.service.UserService;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.fivestars.interfaces.bbs.client.UCClient;
import com.fivestars.interfaces.bbs.util.XMLHelper;

public class CreditServiceImpl implements CreditService {
	private CreditRuleDao creditRuleDao;
	private CreditLogDao creditLogDao;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CreditRuleDao getCreditRuleDao() {
		return creditRuleDao;
	}

	public void setCreditRuleDao(CreditRuleDao creditRuleDao) {
		this.creditRuleDao = creditRuleDao;
	}

	public CreditLogDao getCreditLogDao() {
		return creditLogDao;
	}

	public void setCreditLogDao(CreditLogDao creditLogDao) {
		this.creditLogDao = creditLogDao;
	}

	public int addCredit(int crid, Instruction ins, User user, int dateline) {
		return this.addCredit(crid, ins, user, 0, "", dateline);
	}

	public int addCredit(int crid, Instruction ins, User user, int credit, String description,
			int dateline) {
		if (ins != null && crid == 4) {
			List<CreditLog> creditLogs = creditLogDao.findByProperty("instruction.id", ins.getId());
			if (creditLogs != null && creditLogs.size() > 0)
				return SystemConstants.FEEDBACK.CREDIT_INS_HAVEADD;
		}

		if (dateline <= 0) {
			dateline = Time.getTimeStamp();
		}
		CreditRule creditRule = creditRuleDao.get(crid);
		int creditValue = credit;
		if (creditValue == 0) {
			creditValue = creditRule.getCredit();
			if (creditValue == 0) {
				// 积分为0不再加入积分记录表
				return SystemConstants.FEEDBACK.CREDIT_0;
			}
		}
		if (creditRule != null) {
			int daySum = creditLogDao.todaySumByUserAndRule(creditRule, user, dateline);
			int dayThreshold = creditRule.getDayThreshold();
			int monthSum = creditLogDao.monthSumByUserAndRule(creditRule, user, dateline);
			int monthThreshold = creditRule.getMonthThreshold();

			if (creditRule.getMonthThreshold() == 0
					|| (monthThreshold > 0 && (creditRule.getCredit() + monthSum <= monthThreshold))
					|| (monthThreshold < 0 && (creditRule.getCredit() + monthSum >= monthThreshold))) {
				if (creditRule.getDayThreshold() == 0
						|| (dayThreshold > 0 && (creditRule.getCredit() + daySum <= dayThreshold))
						|| (dayThreshold < 0 && (creditRule.getCredit() + daySum >= dayThreshold))) {
					boolean isYidongShuomingshuUpload = false;

					/**
					 * 移动家庭终端说明书翻倍
					 * 
					 * @date 2013-06-26 暂时取消
					 */
					// if (crid == 4
					// && ins != null
					// && (ins.getCategory().getId() == 113 || ins.getCategory().getParentId() ==
					// 113)) {
					// isYidongShuomingshuUpload = true;
					// creditValue = 2 * creditValue;
					// }

					long currentTimeMillis = System.currentTimeMillis();
					int createTime = (int) (currentTimeMillis / 1000);
					CreditLog creditLog = new CreditLog();
					creditLog.setInstruction(ins);
					creditLog.setUser(user);
					creditLog.setCreditRule(creditRule);
					creditLog.setCreateTime(createTime);
					creditLog.setDateline(dateline);
					creditLog.setCredit(creditValue);
					if (description == null || "".equals(description))
						description = creditRule.getDescription();
					if (crid == 4) {
						description = ins.getTitle() + "通过审核获得" + creditValue + "来福币。";
						if (isYidongShuomingshuUpload) {
							description += "（移动家庭终端说明书翻倍）!";
						}

					} else if (crid == 3) {
						description = "对" + ins.getTitle() + "的评论通过审核获得" + creditValue + "来福币。";
					} else if (crid == 7) {
						description = ins.getTitle() + "下载并评论通过审核获得" + creditValue + "来福币。";
					}
					creditLog.setDescription(description);

					String orderNumber = UCClient.UC_APPID + "-"
							+ Time.formatTimeStamp(currentTimeMillis, "yyyyMMddHHmmssSSS") + "-"
							+ UCClient.UC_LFB_CODE + "-" + user.getUid();

					creditLog.setOrderNumber(orderNumber);
					UCClient uc = new UCClient();
					try {
						String result = uc.uc_credit_operatelfb(user.getSmsphone(), user.getUid(),
								creditValue, description, orderNumber);
						LinkedList<String> rs = XMLHelper.uc_unserialize(result);
						if (rs.size() > 0) {
							int lfb = Integer.parseInt(rs.get(1));
							if (lfb >= 0) {
								creditLog.setIsSync(new Short((short) 1));
								userService.creditAdd(user, creditValue);
							} else {
								System.out.println("来福币同步失败原因：" + rs.get(2));
								creditLog.setIsSync(new Short((short) 0));
							}
							creditLog.setSyncMessage(rs.get(2));
						}
					} catch (Exception e) {
						creditLog.setIsSync(new Short((short) 0));
						e.printStackTrace();
					}

					creditLogDao.saveOrUpdate(creditLog);

					return SystemConstants.FEEDBACK.SUCCESS;
				} else {
					return SystemConstants.FEEDBACK.CREDIT_EXEED_THRESHOLD;
				}
			} else {
				return SystemConstants.FEEDBACK.CREDIT_EXEED_THRESHOLD;
			}
		} else
			return SystemConstants.FEEDBACK.CREDIT_RULE_ID_NOEXSIT;
	}

	public int editRule(CreditRule creditRuleInput) {
		CreditRule creditRule = creditRuleDao.get(creditRuleInput.getId());
		if (creditRule != null) {
			if (creditRuleInput.getCredit() < 0 && creditRuleInput.getDayThreshold() <= 0
					|| creditRuleInput.getCredit() > 0 && creditRuleInput.getDayThreshold() >= 0) {
				creditRule.setCredit(creditRuleInput.getCredit());
				creditRule.setName(creditRuleInput.getName());
				creditRule.setDescription(creditRuleInput.getDescription());
				creditRule.setDayThreshold(creditRuleInput.getDayThreshold());
				creditRuleDao.saveOrUpdate(creditRule);
				return SystemConstants.FEEDBACK.SUCCESS;
			} else
				return SystemConstants.FEEDBACK.CREDIT_THRESHOLD_ILLEGAL;

		} else
			return SystemConstants.FEEDBACK.CREDIT_RULE_ID_NOEXSIT;
	}

	public PageView<CreditLog> listNotificationLog(int crid, int uid, int startTimeStamp,
			int endTimeStamp, int pageNum, int pageSize) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (crid > 0)
			whereClause.add("creditRule.id=" + crid);
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (startTimeStamp < endTimeStamp) {
			whereClause.add("dateline>=" + startTimeStamp);
			whereClause.add("dateline<=" + endTimeStamp);
		}
		whereClause.add("credit!=0");
		whereClause.add("isSync=1");
		whereClause.add("description!=''");
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put("id", "desc");
		PageView<CreditLog> creditLogs = new PageView<CreditLog>(pageNum, pageSize);
		creditLogs.setQueryResult(creditLogDao.findScrollData(pageNum, pageSize, whereClause,
				orderbyClause));
		return creditLogs;
	}

	public int sumLog(int uid, int startTimeStamp, int endTimeStamp, CREDIT_TYPE type) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (startTimeStamp < endTimeStamp) {
			whereClause.add("dateline>=" + startTimeStamp);
			whereClause.add("dateline<=" + endTimeStamp);
		}
		if (type != CREDIT_TYPE.ALL) {
			if (type == CREDIT_TYPE.ADD) {
				whereClause.add("credit>0");
			} else if (type == CREDIT_TYPE.CUT) {
				whereClause.add("credit<0");
			}
		}
		return creditLogDao.operate(whereClause, "sum", "credit");
	}

	public PageView<CreditLog> listLog(int crid, int uid, int startTimeStamp, int endTimeStamp,
			int page, int count, String order, String sort) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (crid > 0)
			whereClause.add("creditRule.id=" + crid);
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (startTimeStamp < endTimeStamp) {
			whereClause.add("dateline>=" + startTimeStamp);
			whereClause.add("dateline<=" + endTimeStamp);
		}
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put(sort, order);
		PageView<CreditLog> creditLogs = new PageView<CreditLog>(page, count);
		creditLogs.setQueryResult(creditLogDao.findScrollData(page, count, whereClause,
				orderbyClause));
		return creditLogs;
	}

	public PageView<CreditRule> listRule(int pageNum, int pageSize) {
		PageView<CreditRule> creditLogs = new PageView<CreditRule>(pageNum, pageSize);
		creditLogs.setQueryResult(creditRuleDao.findScrollData(pageNum, pageSize));
		return creditLogs;
	}

	public int editRule(int crid, int credit, String description, int dayThreshold,
			int monthThreshold) {
		CreditRule creditRule = creditRuleDao.get(crid);
		if (creditRule == null)
			return SystemConstants.FEEDBACK.CREDIT_RULE_ID_NOEXSIT;
		else {
			creditRule.setCredit(credit);
			creditRule.setDayThreshold(dayThreshold);
			creditRule.setMonthThreshold(monthThreshold);
			creditRule.setDescription(description);
			creditRuleDao.saveOrUpdate(creditRule);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public CreditRule get(int crid) {
		return creditRuleDao.get(crid);
	}

	public int getNewCount(int uid) {
		ArrayList<String> whereClause = new ArrayList<String>();
		whereClause.add("user.id=" + uid);
		whereClause.add("isSync=1");
		return creditLogDao.getCount(whereClause);
	}

	public int readAll(int uid) {
		List<CreditLog> creditLogs = creditLogDao.findByProperty("user.uid", uid);
		for (CreditLog creditLog : creditLogs) {
			creditLog.setIsNotified(new Short((short) 1));
			creditLogDao.saveOrUpdate(creditLog);
		}
		return 0;
	}

	public int reCreditAll() {
		int count = 0;
		List<CreditLog> creditLogs = creditLogDao.findByProperty("isSync", 1);
		for (CreditLog creditLog : creditLogs) {
			if (this.reCredit(creditLog)) {
				count++;
			}
		}
		return count;
	}

	public int reCredit(String ids) {
		int count = 0;
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			try {
				int id = Integer.parseInt(idStr);
				CreditLog creditLog = creditLogDao.get(id);
				if (this.reCredit(creditLog)) {
					count++;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public boolean reCredit(CreditLog creditLog) {
		boolean isSuccess = false;
		if (null != creditLog && creditLog.getIsSync() == 0 && creditLog.getCredit() != 0) {
			UCClient uc = new UCClient();
			try {
				User user = creditLog.getUser();
				String orderNumber = creditLog.getOrderNumber();
				if (orderNumber == null || orderNumber.isEmpty()) {
					orderNumber = UCClient.UC_APPID
							+ "-"
							+ Time.formatTimeStamp(creditLog.getCreateTime() * 1000,
									"yyyyMMddHHmmssSSS") + "-" + UCClient.UC_LFB_CODE + "-"
							+ user.getUid();
					creditLog.setOrderNumber(orderNumber);
				}

				String result = uc.uc_credit_operatelfb(user.getSmsphone(), user.getUid(),
						creditLog.getCredit(), creditLog.getDescription(), orderNumber);
				LinkedList<String> rs = XMLHelper.uc_unserialize(result);
				System.out.println(rs);
				if (rs.size() > 0) {
					int lfb = Integer.parseInt(rs.get(1));
					if (lfb >= 0) {
						creditLog.setIsSync(new Short((short) 1));
						userService.creditAdd(user, creditLog.getCredit());
						isSuccess = true;
					} else {
						System.out.println("来福币同步失败原因：" + lfb + ">" + rs.get(2));
					}
					creditLog.setSyncMessage(rs.get(2));
				}
				creditLogDao.saveOrUpdate(creditLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	public boolean isLogExists(int crid, Instruction ins, User user) {
		ArrayList<String> whereClause = new ArrayList<String>();
		whereClause.add("instruction.id=" + ins.getId());
		whereClause.add("user.uid=" + user.getUid());
		whereClause.add("creditRule.id=" + crid);

		int count = creditLogDao.getCount(whereClause);
		if (count > 0) {
			return true;
		}
		return false;
	}

}
