package instruction.interceptor;

import instruction.rules.UserTokenRule;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserApiInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (action instanceof UserTokenRule) {
			UserTokenRule userTokenRule = (UserTokenRule) action;
			userTokenRule.setIsFromApp(true);
		}
		return invocation.invoke();

	}

}
