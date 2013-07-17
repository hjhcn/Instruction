package instruction.dao.impl;

import instruction.dao.PhpcmsDao;
import instruction.model.PhpcmsContent;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PhpcmsDaoImpl extends HibernateDaoSupport implements PhpcmsDao {
	@SuppressWarnings("unchecked")
	public List<PhpcmsContent> getAllNewContent() {
		return this
				.getHibernateTemplate()
				.find(
						"from PhpcmsContent pc left outer join fetch pc.download where pc.shiftstatus=0");
	}

	@SuppressWarnings("unchecked")
	public List<PhpcmsContent> getAll() {
		return this
				.getHibernateTemplate()
				.find(
						"from PhpcmsContent pc left outer join fetch pc.download order by id desc");
	}

	public void save(PhpcmsContent pc) {
		this.getHibernateTemplate().saveOrUpdate(pc);
	}
}
