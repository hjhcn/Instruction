package instruction.model;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Category implements java.io.Serializable {

	private static final long serialVersionUID = -3459289910410282463L;
	private int id;
	private String name;
	private Integer count;
	private Integer parentId;
	private int level = 0;
	private Set<Brand> brands;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return this.id;
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	@JSON(serialize = false)
	public Set<Brand> getBrands() {
		return brands;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return parentId;
	}

}