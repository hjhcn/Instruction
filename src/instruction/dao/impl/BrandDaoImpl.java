package instruction.dao.impl;

import instruction.dao.BrandDao;
import instruction.model.Brand;

import java.util.List;

public class BrandDaoImpl extends BaseDaoImpl<Brand> implements BrandDao {

	@SuppressWarnings("unchecked")
	public List<Brand> getByName(String name) {
		return this.getHibernateTemplate().find(
				"from Brand where name like '%" + name + "%'");
	}

	public void save(Brand brand) {
		this.getHibernateTemplate().saveOrUpdate(brand);
	}

	public void delete(Brand brand) {
		this.getHibernateTemplate().delete(brand);
	}

	@SuppressWarnings("unchecked")
	public List<Brand> getAll() {
		return this.getHibernateTemplate().find("from Brand");
	}

}
