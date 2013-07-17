package instruction.action.admin;

import instruction.SystemConstants;
import instruction.model.Admin;
import instruction.model.LoginAdmin;
import instruction.service.AdminService;
import instruction.util.Ip;
import instruction.util.page.PageView;

import javax.servlet.http.HttpSession;

public class AdminAdminAction extends BaseAdminAction {
	private static final long serialVersionUID = 8595981534712690170L;

	private AdminService adminService;
	private int uid;
	private String username;
	private String password;
	private PageView<Admin> admins;
	private String search;
	private String newPassword;

	public String login() {
		LoginAdmin loginAdmin = adminService.login(username, password, Ip.getIpAddr(request));
		feedback = loginAdmin.getFeedback();
		if (feedback == SystemConstants.FEEDBACK.SUCCESS) {
			session.put(SystemConstants.ADMIN_SESSION, loginAdmin.getAdmin());
			return SUCCESS;
		} else
			return LOGIN;
	}

	public String logout() {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute(SystemConstants.ADMIN_SESSION);
		return SUCCESS;
	}

	public String password() {
		feedback = adminService.changePassword(admin.getUid(), password, newPassword);
		return SUCCESS;
	}

	public String list() {
		// 尚未优化
		admins = adminService.findScrollData(search, page, rows, order, sort);
		return SUCCESS;
	}

	public String delete() {
		feedback = adminService.delete(uid);
		return SUCCESS;
	}

	public String getAdminByAid() {
		admin = adminService.getAdminByUid(uid);
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Admin getAdmin() {
		return admin;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setAdmins(PageView<Admin> admins) {
		this.admins = admins;
	}

	public PageView<Admin> getAdmins() {
		return admins;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String setNewPassword() {
		return newPassword;
	}

}
