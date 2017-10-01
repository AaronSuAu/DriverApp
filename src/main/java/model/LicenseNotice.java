package model;


public class LicenseNotice {
	private int licid;
	private int nid;
	private String driver_name;
	private String license_number;
	private String email;
	private String expiry_date;
	private String address;
	private String review_result;
	private String status;
	private String license_class;
	
	public String getLicense_class() {
		return license_class;
	}
	public void setLicense_class(String license_class) {
		this.license_class = license_class;
	}
	public int getLicid() {
		return licid;
	}
	public void setLicid(int licid) {
		this.licid = licid;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getLicense_number() {
		return license_number;
	}
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReview_result() {
		return review_result;
	}
	public void setReview_result(String review_result) {
		this.review_result = review_result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
