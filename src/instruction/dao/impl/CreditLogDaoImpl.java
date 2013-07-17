package instruction.dao.impl;

import instruction.dao.CreditLogDao;
import instruction.model.CreditLog;
import instruction.model.CreditRule;
import instruction.model.User;
import instruction.util.Time;

import org.hibernate.Query;

public class CreditLogDaoImpl extends BaseDaoImpl<CreditLog> implements CreditLogDao {
	public int todaySumByUserAndRule(CreditRule creditRule, User user, int dateline) {
		Query q = getSession().createQuery(
				"select sum(credit) from CreditLog cl where user.uid=" + user.getUid()
						+ " and creditRule.id=" + creditRule.getId() + " and dateline>="
						+ Time.getTodayTimeStamp(dateline));
		int count = 0;
		if (q.uniqueResult() != null)
			count = Integer.parseInt(q.uniqueResult() + "");
		return count;
	}

	public int monthSumByUserAndRule(CreditRule creditRule, User user, int dateline) {
		Query q = getSession().createQuery(
				"select sum(credit) from CreditLog cl where user.uid=" + user.getUid()
						+ " and creditRule.id=" + creditRule.getId() + " and dateline>="
						+ Time.getMonthTimeStamp(dateline));
		int count = 0;
		if (q.uniqueResult() != null)
			count = Integer.parseInt(q.uniqueResult() + "");
		return count;
	}

}
