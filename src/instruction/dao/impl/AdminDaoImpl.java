package instruction.dao.impl;

import instruction.dao.AdminDao;
import instruction.model.Admin;

import java.util.List;

public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {
	@SuppressWarnings("unchecked")
	public Admin getByUsername(String username) {
		List<Admin> admins = this.getHibernateTemplate().find(
				"from Admin where username=?", username);
		if (admins.size() > 0)
			return admins.get(0);
		else
			return null;
	}

	public Admin getByUid(int uid) {
		List<Admin> admins = this.findByProperty("user.uid", uid);
		if (admins.size() > 0)
			return admins.get(0);
		else
			return null;
	}
}
