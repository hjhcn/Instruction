package instruction.interceptor;

import instruction.SystemConstants;
import instruction.model.User;
import instruction.model.UserSession;
import instruction.rules.FeedbackRule;
import instruction.rules.UserSessionRule;
import instruction.rules.UserTokenRule;
import instruction.service.UserService;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserTokenInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private UserService userService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = (String) request.getParameter("token");
		User user = userService.getUserByToken(token);
		Object action = invocation.getAction();
		if (null != user) {
			if (action instanceof UserTokenRule) {
				UserTokenRule userTokenRule = (UserTokenRule) action;
				userTokenRule.setIsFromApp(true);
			}
			if (action instanceof UserSessionRule) {
				UserSession userSession = new UserSession(user);
				UserSessionRule userSessionRule = (UserSessionRule) action;
				userSessionRule.setUserSession(userSession);
			}
			return invocation.invoke();
		} else {
			if (action instanceof FeedbackRule) {
				FeedbackRule feedbackRule = (FeedbackRule) action;
				feedbackRule.setFeedback(SystemConstants.FEEDBACK.USER_TOKEN_ERROR);
			}
			return Action.ERROR;
		}

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
