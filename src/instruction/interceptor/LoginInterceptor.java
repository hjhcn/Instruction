package instruction.interceptor;

import instruction.SystemConstants;
import instruction.model.UserSession;
import instruction.rules.FeedbackRule;
import instruction.rules.UserSessionRule;
import instruction.service.UserService;
import instruction.util.UserCookieUtils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private UserService userService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserSession userSession = (UserSession) session
				.getAttribute(SystemConstants.USER_SESSION);
		if (null == userSession) {
			userSession = UserCookieUtils.getUserSessionByCookie(userService);
		}
		Object action = invocation.getAction();
		if (null != userSession) {
			if (action instanceof UserSessionRule) {
				UserSessionRule userSessionRule = (UserSessionRule) action;
				userSessionRule.setUserSession(userSession);
			}
			return invocation.invoke();
		} else {
			if (action instanceof FeedbackRule) {
				FeedbackRule feedbackRule = (FeedbackRule) action;
				feedbackRule
						.setFeedback(SystemConstants.FEEDBACK.USER_UNLOGIN_ERROR);
			}
			return Action.ERROR;
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
