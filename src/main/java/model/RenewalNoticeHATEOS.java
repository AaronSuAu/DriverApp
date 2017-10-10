package model;

public class RenewalNoticeHATEOS extends RenewalNotices{
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public RenewalNoticeHATEOS(RenewalNotices rNotices){
		setAccess_token(rNotices.getAccess_token());
		setAddress(rNotices.getAddress());
		setContact_email(rNotices.getContact_email());
		setLicid(rNotices.getLicid());
		setNid(rNotices.getNid());
		setReview_result(rNotices.getReview_result());
		setStatus(rNotices.getStatus());
	}
	
}
