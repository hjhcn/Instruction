package instruction.service.impl;

import instruction.SystemConstants;
import instruction.SystemConstants.COMMENT_CREDIT_TYPE;
import instruction.SystemConstants.CREDIT_TYPE;
import instruction.SystemConstants.STATUS;
import instruction.dao.UserDao;
import instruction.model.DownloadLog;
import instruction.model.LoginUser;
import instruction.model.TimeStampRange;
import instruction.model.User;
import instruction.model.UserStatistic;
import instruction.service.CommentService;
import instruction.service.CreditService;
import instruction.service.InsService;
import instruction.service.UserService;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fivestars.interfaces.bbs.client.UCClient;
import com.fivestars.interfaces.bbs.util.XMLHelper;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private CreditService creditService;
	private InsService insService;
	private CommentService commentService;

	public void setInsService(InsService insService) {
		this.insService = insService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public User get(int uid) {
		return userDao.get(uid);
	}

	public User getUserByToken(String token) {
		User user = null;
		List<User> users = userDao.findByProperty("token", token);
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public User getUserByCookie(String token) {
		User user = null;
		List<User> users = userDao.findByProperty("cookie", token);
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	public PageView<User> findScrollData(int uid, String smsphone, String username, int page,
			int rows, String order, String sort) {
		PageView<User> pageView = new PageView<User>(page, rows);
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("uid=" + uid);
		if (null != smsphone && !smsphone.isEmpty())
			whereClause.add("smsphone like '%" + smsphone + "%'");
		if (null != username && !username.isEmpty())
			whereClause.add("username like '%" + username + "%'");
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put(sort, order);
		pageView.setQueryResult(userDao.findScrollData(page, rows, whereClause, orderbyClause));
		return pageView;
	}

	public synchronized LoginUser login(String loginname, String password, String ip,
			boolean isFromApp) {
		LoginUser loginUser = new LoginUser();
		loginUser.setFeedback(SystemConstants.FEEDBACK.SUCCESS);// 首先置为成功
		UCClient uc = new UCClient();
		try {
			String result = uc.uc_user_login(loginname, password, ip);
			if (isFromApp) {
				System.out.println(result);
			}
			LinkedList<String> rs = XMLHelper.uc_unserialize(result);
			if (rs.size() > 0) {
				int uid = Integer.parseInt(rs.get(0));
				String username = rs.get(1);
				String email = rs.get(3);
				String smsphone = rs.get(4);

				if (uid > 0) {
					User user = userDao.get(uid);
					boolean isNew = false;
					if (user == null) {
						user = new User();
						user.setUid(uid);
						user.setSmsphone(smsphone);
						user.setRegip(ip);
						user.setRegtime(Time.getTimeStamp());
						user.setCredit(0);
						isNew = true;
					}
					user.setUsername(username);
					user.setSmsphone(smsphone);
					user.setEmail(email);
					user.setDateline(Time.getTimeStamp());
					user.setLogintime(Time.getTimeStamp());
					user.setLastip(ip);
					if (isFromApp) {
						String token = user.generateToken();// 每次APP登陆生成token
						loginUser.setToken(token);
					} else
						user.generateCookie();// 浏览器登陆刷新cookie
					userDao.saveOrUpdate(user);
					if (isNew) {
						creditService.addCredit(1, null, user, Time.getTimeStamp());
					}
					loginUser.setLfb(this.queryLfb(loginname));
					loginUser.setUser(user);
					loginUser.setSync(uc.uc_user_synlogin(uid));
				} else {
					switch (uid) {
					case -2:
						loginUser.setFeedback(SystemConstants.FEEDBACK.USER_LOGINNAME_PSWD_ERROR);
						break;
					case -3:
					case -4:
					case -5:
					case -11:
					default:
						loginUser.setFeedback(SystemConstants.FEEDBACK.USER_API_ERROR);
						break;
					}
				}
			} else {
				loginUser.setFeedback(SystemConstants.FEEDBACK.USER_API_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginUser.setFeedback(SystemConstants.FEEDBACK.USER_API_ERROR);
		}
		return loginUser;
	}

	public int queryLfb(String smsphone) {
		int lfb = 0;
		try {
			UCClient uc = new UCClient();
			String result = uc.uc_credit_querylfb(smsphone);
			LinkedList<String> rs = XMLHelper.uc_unserialize(result);
			lfb = Integer.parseInt(rs.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfb;
	}

	public User login(Map<String, String> codeMap, String ip) {
		int uid = Integer.parseInt(codeMap.get("uid"));
		String smsphone = codeMap.get("tel");
		String email = codeMap.get("email");
		if (smsphone == null)
			smsphone = "";
		String username = codeMap.get("username");
		User user = userDao.get(uid);
		boolean isNew = false;
		if (user == null) {
			user = new User();
			user.setUid(uid);
			user.setSmsphone(smsphone);
			user.setRegip(ip);
			user.setRegtime(Time.getTimeStamp());
			user.setCredit(0);
			user.setEmail("");
			isNew = true;
		}
		if (!smsphone.equals(""))
			user.setSmsphone(smsphone);
		if (!email.equals(""))
			user.setEmail(email);
		user.setUsername(username);
		user.setDateline(Time.getTimeStamp());
		user.setLogintime(Time.getTimeStamp());
		user.setLastip(ip);
		user.generateCookie();// 浏览器登陆刷新cookie
		userDao.saveOrUpdate(user);
		if (isNew)
			creditService.addCredit(1, null, user, Time.getTimeStamp());
		return user;
	}

	public boolean isDownLoad(User user, int iid) {
		if (null != user) {
			Set<DownloadLog> downloadLogs = user.getDownloadLogs();
			for (DownloadLog downloadLog : downloadLogs) {
				if (downloadLog.getInstruction().getId() == iid) {
					return true;
				}
			}
		}
		return false;
	}

	public void creditAdd(User user, int credit) {
		userDao.creditAdd(user.getUid(), credit);
	}

	public UserStatistic statistic(int uid, TimeStampRange tsRange) {
		int startTimeStamp = tsRange.getStartTimeStamp();
		int endTimeStamp = tsRange.getEndTimeStamp();
		UserStatistic userStatistic = new UserStatistic();
		User user = userDao.get(uid);
		if (null == user)
			return null;
		userStatistic.setUser(user);
		userStatistic.setCreditAdd(creditService.sumLog(uid, startTimeStamp, endTimeStamp,
				CREDIT_TYPE.ADD));
		userStatistic.setCreditCut(creditService.sumLog(uid, startTimeStamp, endTimeStamp,
				CREDIT_TYPE.CUT));
		userStatistic.setInsAdd(insService.countByUid(uid, startTimeStamp, endTimeStamp,
				STATUS.SHOWALL));
		userStatistic.setInsPass(insService.countByUid(uid, startTimeStamp, endTimeStamp,
				STATUS.PASS));
		userStatistic.setComAdd(commentService.creditStatistic(uid, startTimeStamp, endTimeStamp,
				COMMENT_CREDIT_TYPE.SHOWALL));
		userStatistic.setComPass(commentService.creditStatistic(uid, startTimeStamp, endTimeStamp,
				COMMENT_CREDIT_TYPE.YES));
		return userStatistic;
	}
}
