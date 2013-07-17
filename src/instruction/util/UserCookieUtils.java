package instruction.util;

import instruction.SystemConstants;
import instruction.model.User;
import instruction.model.UserSession;
import instruction.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

public class UserCookieUtils {

	public static Cookie setUserCookie(User user) {
		Cookie cookie = new Cookie(SystemConstants.USER_COOKIE, user.getCookie() + "");
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
		return cookie;
	}

	public static UserSession getUserSessionByCookie(UserService userService) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (SystemConstants.USER_COOKIE.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						try {
							String ip = Ip.getIpAddr(request);
							User user = userService.getUserByCookie(value);
							if (user != null) {
								user.setLastip(ip);
								user.setLogintime(Time.getTimeStamp());
								user.generateCookie();
								UserSession userSession = new UserSession(user);
								userService.saveOrUpdate(user);
								HttpSession session = request.getSession();
								session.setAttribute(SystemConstants.USER_SESSION, userSession);
								ServletActionContext.getResponse().addCookie(
										UserCookieUtils.setUserCookie(user));
								return userSession;
							}
						} catch (Exception e) {
							// 转换异常
						}
					}
				}
			}
		}
		return null;
	}

	public static Cookie delCookie() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (SystemConstants.USER_COOKIE.equals(cookie.getName())) {
					cookie.setPath("/");
					cookie.setValue("");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
	}
}
