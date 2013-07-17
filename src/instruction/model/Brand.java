package instruction.model;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


public class Brand implements java.io.Serializable {

	private static final long serialVersionUID = 1069838233620484877L;
	private int id;
	private String name;
	private String nameZh;
	private String nameEn;
	private String iconUrl;
	private Short isHot;
	private Integer count;
	private Set<Category> cats;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setIsHot(Short isHot) {
		this.isHot = isHot;
	}

	public Short getIsHot() {
		return isHot;
	}

	public void setCats(Set<Category> cats) {
		this.cats = cats;
	}

	@JSON(serialize = false)
	public Set<Category> getCats() {
		return cats;
	}

}