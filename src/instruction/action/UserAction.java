package instruction.action;

import instruction.SystemConstants;
import instruction.model.LoginUser;
import instruction.model.User;
import instruction.model.UserSession;
import instruction.service.CreditService;
import instruction.service.UserService;
import instruction.util.UserCookieUtils;
import instruction.util.Ip;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.fivestars.interfaces.bbs.client.UCClient;

public class UserAction extends SessionBaseAction {

	private static final long serialVersionUID = -6071942657437493908L;
	private CreditService creditService;
	private UserService userService;
	private String loginname = "";
	private String password = "";
	private LoginUser loginUser;
	private String code;
	private int time;
	private int lfb;

	public String debug() {
		User user = userService.get(1302);
		user.generateCookie();
		Cookie cookie = UserCookieUtils.setUserCookie(user);
		response.addCookie(cookie);
		session.put(SystemConstants.USER_SESSION, new UserSession(user));
		return SUCCESS;
	}

	public String test() {
		UCClient uc = new UCClient();
		uc.uc_get_user("1302", 1);
		return SUCCESS;
	}

	public String login() {
		loginUser = userService.login(loginname, password, Ip.getIpAddr(request), isFromApp);
		if (loginUser.getFeedback() > 0 && !isFromApp) {
			Cookie cookie = UserCookieUtils.setUserCookie(loginUser.getUser());
			response.addCookie(cookie);
			session.put(SystemConstants.USER_SESSION, new UserSession(loginUser.getUser()));
		}
		feedback = loginUser.getFeedback();
		return SUCCESS;
	}

	public String logoutAuth() {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute(SystemConstants.USER_SESSION);
		Cookie cookie = UserCookieUtils.delCookie();
		if (cookie != null)
			response.addCookie(cookie);
		return SUCCESS;
	}

	public String queryLfbAuth() {
		if (userSession != null) {
			lfb = userService.queryLfb(userSession.getSmsphone());
			userSession.setLfb(lfb);
			session.put(SystemConstants.USER_SESSION, userSession);
		}
		return SUCCESS;
	}

	public String ucSync() {
		// System.out.println(code);
		UCClient uc = new UCClient();
		Map<String, String> codeMap = uc.decodeReturnMap(code);
		// System.out.println(codeMap);
		String action = codeMap.get("action");
		if (null != action) {
			if (action.equals("synlogin")) {
				User user = userService.login(codeMap, Ip.getIpAddr(request));
				Cookie cookie = UserCookieUtils.setUserCookie(user);
				response.addCookie(cookie);
				response.setHeader("P3P",
						"CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
				session.put(SystemConstants.USER_SESSION,
						new UserSession(user, userService.queryLfb(codeMap.get("tel"))));
			} else if (action.equals("synclogout")) {
				this.logoutAuth();
			}
		}
		return SUCCESS;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFeedback() {
		return feedback;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setLfb(int lfb) {
		this.lfb = lfb;
	}

	public int getLfb() {
		return lfb;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public CreditService getCreditService() {
		return creditService;
	}

}
