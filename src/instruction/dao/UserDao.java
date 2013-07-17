package instruction.dao;

import instruction.model.User;

public interface UserDao extends BaseDao<User> {
	public void creditAdd(int uid, int credit);
}
