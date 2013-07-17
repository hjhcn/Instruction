package instruction.dao.impl;

import instruction.dao.UserDao;
import instruction.model.User;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public void creditAdd(final int uid, final int credit) {
		this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = s.createQuery("update User set credit=credit+"
						+ credit + " where uid=" + uid);
				query.executeUpdate();
				return null;
			}
		});
	}

}
