package instruction.model;

public class BBSThread  implements java.io.Serializable{
	private static final long serialVersionUID = 8270617213150160862L;
	private Integer id;
	private String title;
	private String link;
	private Integer order;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

	
}
