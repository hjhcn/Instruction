package instruction.dao.impl;

import instruction.dao.InsDao;
import instruction.model.AdminStatistic;
import instruction.model.Brand;
import instruction.model.Instruction;
import instruction.model.User;
import instruction.util.page.QueryResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;

public class InsDaoImpl extends BaseDaoImpl<Instruction> implements InsDao {

	@SuppressWarnings("unchecked")
	public QueryResult<Instruction> findScrollDataCascade(int pageNum,
			int pageSize, List<String> whereClause,
			LinkedHashMap<String, String> orderbyClause) {
		int start = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
		QueryResult<Instruction> qr = new QueryResult<Instruction>();
		Query q = getSession().createQuery(
				"select count(*) from  Instruction entity"
						+ buildWhereClause(whereClause)
						+ " and entity.brand.id=brand.id and entity.category.id=category.id and entity.user.uid=user.uid");
		int count = Integer.parseInt(q.uniqueResult() + "");
		if (start >= count)
			start = count - 1 - pageSize;
		// 有问题
		q = getSession()
				.createQuery(
						"select entity from Instruction entity,Brand brand,Category category,User user "
								+ buildWhereClause(whereClause)
								+ " and entity.brand.id=brand.id and entity.category.id=category.id and entity.user.uid=user.uid"
								+ buildOrderByClause(orderbyClause));
		q.setFirstResult(start).setMaxResults(pageSize);
		qr.setResultlist(q.list());
		qr.setTotalrecord(count);
		return qr;
	}

	@SuppressWarnings("unchecked")
	public List<Instruction> findTopNew(int count) {
		//MYSQL only，加入了limit语句
		Query q = getSession()
				.createSQLQuery(
						"select * from (select * from Instruction ins where status>0 order by uploadTime desc limit 1000) ins  group by bid order by uploadTime desc")
				.addEntity(Instruction.class);
		q.setFirstResult(0).setMaxResults(count);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<AdminStatistic> statistic(List<String> whereClause,
			LinkedHashMap<String, String> orderbyClause,
			List<String> groupbyClause) {
		Query q = getSession().createQuery(
				"select new List(count(*),entity.user,entity.brand) from Instruction entity"
						+ buildWhereClause(whereClause)
						+ buildGroupByClause(groupbyClause)
						+ buildOrderByClause(orderbyClause));
		List<AdminStatistic> statistislist = new ArrayList<AdminStatistic>();
		List<List<?>> lists = q.list();
		for (List<?> list : lists) {
			AdminStatistic adminStatistic = new AdminStatistic();
			adminStatistic.setBrandName(((Brand) list.get(2)).getName());
			adminStatistic.setUploadCount(((Long) list.get(0)).intValue());
			adminStatistic.setUsername(((User) list.get(1)).getUsername());
			adminStatistic.setSmsphone(((User) list.get(1)).getSmsphone());
			adminStatistic.setUid(((User) list.get(1)).getUid());
			statistislist.add(adminStatistic);
		}
		return statistislist;
	}
}
