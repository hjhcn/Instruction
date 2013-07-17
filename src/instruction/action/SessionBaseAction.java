package instruction.action;

import instruction.model.UserSession;
import instruction.rules.FeedbackRule;
import instruction.rules.UserSessionRule;
import instruction.rules.UserTokenRule;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

public class SessionBaseAction extends BaseAction implements SessionAware, ServletRequestAware,
		ServletResponseAware, UserSessionRule, FeedbackRule, UserTokenRule {
	private static final long serialVersionUID = 1L;
	public Map<String, Object> session;
	public HttpServletResponse response;
	public HttpServletRequest request;
	public UserSession userSession;
	public boolean isFromApp = false;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setIsFromApp(boolean isFromApp) {
		this.isFromApp = isFromApp;
	}

}
