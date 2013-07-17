package instruction.dao;

import instruction.model.AdminStatistic;
import instruction.model.Instruction;
import instruction.util.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface InsDao extends BaseDao<Instruction> {
	public QueryResult<Instruction> findScrollDataCascade(int pageNum,
			int pageSize, List<String> whereClause,
			LinkedHashMap<String, String> orderbyClause);

	public List<Instruction> findTopNew(int count);

	public List<AdminStatistic> statistic(List<String> whereClause,
			LinkedHashMap<String, String> orderbyClause,
			List<String> groupbyClause);
}
