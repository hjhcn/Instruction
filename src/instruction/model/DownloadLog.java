package instruction.model;

public class DownloadLog implements java.io.Serializable {
	private static final long serialVersionUID = 3192864899581261016L;
	private int id;
	private User user;
	private Instruction instruction;
	private int dateline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getDateline() {
		return dateline;
	}

	public void setDateline(int dateline) {
		this.dateline = dateline;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public Instruction getInstruction() {
		return instruction;
	}

}
