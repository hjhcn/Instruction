package instruction.model;

public class Notification implements java.io.Serializable {
	private static final long serialVersionUID = 9138345225537408645L;
	private int id;
	private String content;
	private int dateline;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDateline() {
		return this.dateline;
	}

	public void setDateline(Integer dateline) {
		this.dateline = dateline;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}