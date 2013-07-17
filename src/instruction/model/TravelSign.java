package instruction.model;

public class TravelSign implements java.io.Serializable {

	private static final long serialVersionUID = 2510306338158657468L;
	private Integer id;
	private String type;
	private String name;
	private Short gender;
	private String idCardNo;
	private String phone;
	private Integer year;
	private Integer enrollment;
	private String plateNumber;
	private Short studentOrCommunity;
	private Short playerOrFriends;
	private Short needBusOrNot;
	private Short needDormOrNot;
	private String motorcycleType;
	private String school;

	public TravelSign() {

	}

	public TravelSign(TravelSign travelSign) {
		this.type = travelSign.getType();
		this.name = travelSign.getName();
		this.gender = travelSign.getGender();
		this.idCardNo = travelSign.getIdCardNo();
		this.phone = travelSign.getPhone();
		this.year = travelSign.getYear();
		this.enrollment = travelSign.getEnrollment();
		this.plateNumber = travelSign.getPlateNumber();
		this.studentOrCommunity = travelSign.getStudentOrCommunity();
		this.playerOrFriends = travelSign.getPlayerOrFriends();
		this.needBusOrNot = travelSign.getNeedBusOrNot();
		this.needDormOrNot = travelSign.getNeedDormOrNot();
		this.motorcycleType = travelSign.getMotorcycleType();
		this.school = travelSign.getSchool();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getGender() {
		return this.gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getEnrollment() {
		return this.enrollment;
	}

	public void setEnrollment(Integer enrollment) {
		this.enrollment = enrollment;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getMotorcycleType() {
		return this.motorcycleType;
	}

	public void setMotorcycleType(String motorcycleType) {
		this.motorcycleType = motorcycleType;
	}

	public Short getStudentOrCommunity() {
		return this.studentOrCommunity;
	}

	public void setStudentOrCommunity(Short studentOrCommunity) {
		this.studentOrCommunity = studentOrCommunity;
	}

	public Short getPlayerOrFriends() {
		return this.playerOrFriends;
	}

	public void setPlayerOrFriends(Short playerOrFriends) {
		this.playerOrFriends = playerOrFriends;
	}

	public Short getNeedBusOrNot() {
		return this.needBusOrNot;
	}

	public void setNeedBusOrNot(Short needBusOrNot) {
		this.needBusOrNot = needBusOrNot;
	}

	public Short getNeedDormOrNot() {
		return this.needDormOrNot;
	}

	public void setNeedDormOrNot(Short needDormOrNot) {
		this.needDormOrNot = needDormOrNot;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

}