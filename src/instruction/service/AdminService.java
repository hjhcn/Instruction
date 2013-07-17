package instruction.service;

import instruction.model.Admin;
import instruction.model.LoginAdmin;
import instruction.util.page.PageView;

public interface AdminService {
	public LoginAdmin login(String username, String password, String ip);

	public Admin getAdminByUid(int aid);

	public PageView<Admin> findScrollData(String search, int page, int rows, String order,
			String sort);

	public int delete(int uid);

	public int changePassword(int uid, String OldPassword, String newPassword);
}
