package instruction.dao;

import instruction.util.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface BaseDao<T> {
	public void delete(int id);

	public T get(int id);

	public void delete(T entity);

	public void update(T entity);

	public void saveOrUpdate(T entity);

	public void execute(String hql);

	public int getCount(List<String> whereClause);

	public int operate(List<String> whereClause, String expression, String propertyName);

	public List<T> findAll();

	public List<T> findAllData(List<String> whereClause, List<String> groupbyClause,
			LinkedHashMap<String, String> orderbyClause);

	public List<T> findTopData(int pageSize, List<String> whereClause, List<String> groupbyClause,
			LinkedHashMap<String, String> orderbyClause);

	public List<T> findByProperty(String propertyName, Object value);

	public QueryResult<T> findScrollData(int pageNum, int pageSize);

	public QueryResult<T> findScrollData(int start, int maxResults, List<String> whereClause);

	public QueryResult<T> findScrollData(int pageNum, int pageSize,
			LinkedHashMap<String, String> orderbyClause);

	public QueryResult<T> findScrollData(int pageNum, int pageSize, List<String> whereClause,
			LinkedHashMap<String, String> orderbyClause);
}
