package instruction.service;

import java.util.Map;

import instruction.model.LoginUser;
import instruction.model.TimeStampRange;
import instruction.model.User;
import instruction.model.UserStatistic;
import instruction.util.page.PageView;

public interface UserService {
	public User get(int uid);

	public User getUserByToken(String token);

	public User getUserByCookie(String value);

	public void saveOrUpdate(User user);

	public LoginUser login(String smsphone, String password, String ip, boolean isFromApp);

	public int queryLfb(String smsphone);

	public User login(Map<String, String> codeMap, String ip);

	/**
	 * @param user
	 *            用户
	 * @param iid
	 *            说明书id
	 * @return 用户是否已下载iid的说明书 目前仍然需要优化
	 */
	public boolean isDownLoad(User user, int iid);

	public void creditAdd(User user, int credit);

	public PageView<User> findScrollData(int uid, String smsphone, String username, int page,
			int rows, String order, String sort);

	public UserStatistic statistic(int uid, TimeStampRange tsRange);

}
