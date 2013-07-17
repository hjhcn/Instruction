package instruction.dao.impl;

import java.util.List;

import instruction.dao.CategoryDao;
import instruction.model.Category;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements
		CategoryDao {

	@SuppressWarnings("unchecked")
	public List<Category> findLevel1() {
		return this.getHibernateTemplate().find(" from Category where parent=?",0);
	}
}
