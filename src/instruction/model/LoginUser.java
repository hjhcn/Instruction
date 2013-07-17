package instruction.model;

/**
 * 该类用于同步登陆时返回用户信息
 */
public class LoginUser {
	private User user;
	private String sync;
	private int feedback;
	private int lfb;
	/**
	 * 默认不在user类中序列化token字段
	 */
	private String token;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setLfb(int lfb) {
		this.lfb = lfb;
	}

	public int getLfb() {
		return lfb;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
