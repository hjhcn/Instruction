package instruction.model;

public class CreditRule implements java.io.Serializable {

	private static final long serialVersionUID = -4911669196247429745L;
	private Integer id;
	private Integer credit;
	private String name;
	private String description;
	/**
	 * 每天积分阀值，=0时表示不设阀值, 必须和credit同正同负
	 */
	private Integer dayThreshold;
	/**
	 * 每月积分阀值，=0时表示不设阀值, 必须和credit同正同负
	 */
	private Integer monthThreshold;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDayThreshold() {
		return this.dayThreshold;
	}

	public void setDayThreshold(Integer dayThreshold) {
		this.dayThreshold = dayThreshold;
	}

	public void setMonthThreshold(Integer monthThreshold) {
		this.monthThreshold = monthThreshold;
	}

	public Integer getMonthThreshold() {
		return monthThreshold;
	}

}