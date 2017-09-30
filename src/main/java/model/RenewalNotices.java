package model;

import java.io.Serializable;
public class RenewalNotices{
	private int nid;
	private String address;
	private String contact_email;
	private String status;
	private String review_result;
	private String access_token;
	private int licid;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReview_result() {
		return review_result;
	}
	public void setReview_result(String review_result) {
		this.review_result = review_result;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getLicid() {
		return licid;
	}
	public void setLicid(int licid) {
		this.licid = licid;
	}
	public RenewalNotices(int nid, String address, String contact_email, String status, String review_result,
			String access_token, int licid) {
		super();
		this.nid = nid;
		this.address = address;
		this.contact_email = contact_email;
		this.status = status;
		this.review_result = review_result;
		this.access_token = access_token;
		this.licid = licid;
	}
	public RenewalNotices() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
