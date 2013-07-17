package instruction.model;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 9138345225537408645L;
	private int id;
	private String fromAdmin;
	private Integer fromId;
	private Integer toId;
	private String subject;
	private String content;
	private Integer dateline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromAdmin() {
		return this.fromAdmin;
	}

	public void setFromAdmin(String fromAdmin) {
		this.fromAdmin = fromAdmin;
	}

	public Integer getFromId() {
		return this.fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public Integer getToId() {
		return this.toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

}