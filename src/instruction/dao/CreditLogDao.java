package instruction.dao;

import instruction.model.CreditLog;
import instruction.model.CreditRule;
import instruction.model.User;

public interface CreditLogDao extends BaseDao<CreditLog> {
	public int todaySumByUserAndRule(CreditRule creditRule, User user,
			int dateline);

	public int monthSumByUserAndRule(CreditRule creditRule, User user,
			int dateline);
}
