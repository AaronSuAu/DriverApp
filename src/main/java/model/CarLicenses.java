package model;




public class CarLicenses {
	private int licid;
	private String driver_name;
	private String current_address;
	private String license_number;
	private String license_class;
	private String contact_email;
	private String expiry_date;
	public int getLicid() {
		return licid;
	}
	public void setLicid(int licid) {
		this.licid = licid;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getCurrent_address() {
		return current_address;
	}
	public void setCurrent_address(String current_address) {
		this.current_address = current_address;
	}
	public String getLicense_number() {
		return license_number;
	}
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	public String getLicense_class() {
		return license_class;
	}
	public void setLicense_class(String license_class) {
		this.license_class = license_class;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public CarLicenses(int licid, String driver_name, String current_address, String license_number,
			String license_class, String contact_email, String expiry_date) {
		super();
		this.licid = licid;
		this.driver_name = driver_name;
		this.current_address = current_address;
		this.license_number = license_number;
		this.license_class = license_class;
		this.contact_email = contact_email;
		this.expiry_date = expiry_date;
	}
	public CarLicenses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
