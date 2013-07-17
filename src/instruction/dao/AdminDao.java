package instruction.dao;

import instruction.model.Admin;

public interface AdminDao extends BaseDao<Admin> {
	public Admin getByUsername(String username);

	public Admin getByUid(int uid);
}
