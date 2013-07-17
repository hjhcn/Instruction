package instruction.action.admin;

import instruction.model.User;
import instruction.model.UserStatistic;
import instruction.service.UserService;
import instruction.util.page.PageView;

import com.opensymphony.xwork2.Preparable;

public class UserAdminAction extends BaseAdminAction implements Preparable {
	private static final long serialVersionUID = 1979680168642525015L;
	private UserService userService;
	private int uid = 0;
	private String username;
	private String smsphone;
	private User user;
	private long total;
	private PageView<User> userview;
	private UserStatistic userStatistic;

	public void prepare() {
		// 预定义参数
		sort = "uid";
	}

	public String list() {
		userview = userService.findScrollData(uid, smsphone, username, page, rows, order, sort);
		return SUCCESS;
	}

	public String statistic() {
		userStatistic = userService.statistic(uid, tsRange);
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getSmsphone() {
		return smsphone;
	}

	public void setSmsphone(String smsphone) {
		this.smsphone = smsphone;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public long getTotal() {
		return total;
	}

	public PageView<User> getUserview() {
		return userview;
	}

	public void setUserview(PageView<User> userview) {
		this.userview = userview;
	}

	public UserStatistic getUserStatistic() {
		return userStatistic;
	}

	public void setUserStatistic(UserStatistic userStatistic) {
		this.userStatistic = userStatistic;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
