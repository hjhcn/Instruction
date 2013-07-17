package instruction.interceptor;

import instruction.SystemConstants;
import instruction.model.Admin;
import instruction.rules.AdminSessionRule;
import instruction.rules.FeedbackRule;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 77362167764460002L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin admin = (Admin) session
				.getAttribute(SystemConstants.ADMIN_SESSION);
		Object action = invocation.getAction();
		int feedback = SystemConstants.FEEDBACK.SUCCESS;
		if (null != admin) {
			action = invocation.getAction();
			if (action instanceof AdminSessionRule) {
				AdminSessionRule adminSessionRule = (AdminSessionRule) action;
				adminSessionRule.setAdminSession(admin);
				return invocation.invoke();
			}
		} else
			feedback = SystemConstants.FEEDBACK.ADMIN_UNLOGIN_ERROR;

		if (action instanceof FeedbackRule) {
			FeedbackRule feedbackRule = (FeedbackRule) action;
			feedbackRule.setFeedback(feedback);
		}
		return Action.ERROR;
	}

}
