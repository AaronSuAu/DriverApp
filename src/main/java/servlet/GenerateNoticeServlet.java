package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import http.HttpGetDemo;
import http.HttpPostDemo;
import model.CarLicenses;
import model.FeePayments;
import model.RenewalNotices;
import response.JsonResponseList;
import util.EmailUtil;

/**
 * Servlet implementation class GenerateNoticeServlet
 */
// {{baseUrl}}/generateNotice
public class GenerateNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateNoticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		Gson gson = new Gson();
		int licid = Integer.parseInt(request.getParameter("licid"));
		String url = "http://localhost:8090/licenses/" + licid;
		HttpGetDemo getRequest = new HttpGetDemo("123456", url);
		String responseJson = getRequest.sendGetRequest();
		JsonResponseList<CarLicenses> jsonObject = new Gson().fromJson(responseJson,
				new TypeToken<JsonResponseList<CarLicenses>>() {
				}.getType());
		List<CarLicenses> licenses = jsonObject.getList();
		if (licenses == null || licenses.size() < 1) {
			request.getRequestDispatcher("/licenses").forward(request, response);
		}
		// add a renewal notice
		System.out.println(licid + "!!!!!!lciid 1");
		CarLicenses license = licenses.get(0);
		// System.out.println(license.getLicid() + "!!!!!licid 2");
		RenewalNotices newNotice = new RenewalNotices();
		newNotice.setAddress(license.getCurrent_address());
		newNotice.setContact_email(license.getContact_email());
		newNotice.setLicid(licid);
		newNotice.setStatus("new");
		newNotice.setReview_result("No_Comment");
		url = "http://localhost:8090/renewals";
		HttpPostDemo postRequest = new HttpPostDemo(url, "123456", gson.toJson(newNotice));
		String postResponse = postRequest.sendPostRequest();
		JsonResponseList<RenewalNotices> noticeJson = new Gson().fromJson(postResponse,
				new TypeToken<JsonResponseList<RenewalNotices>>() {
				}.getType());
		List<RenewalNotices> notices = noticeJson.getList();
		RenewalNotices notice = notices.get(0);
		// add a payment with paid_date null
		FeePayments newPayment = new FeePayments();
		newPayment.setAmount(100);
		newPayment.setNid(notice.getNid());
		url = "http://localhost:8090/payments";
		postRequest = new HttpPostDemo(url, "123456", gson.toJson(newPayment));
		postResponse = postRequest.sendPostRequest();
		JsonResponseList<FeePayments> paymentJson = new Gson().fromJson(postResponse,
				new TypeToken<JsonResponseList<FeePayments>>() {
				}.getType());
		if (paymentJson.getCode() == 200) {
			// send email
			if (EmailUtil.sendEmail(notice.getContact_email(), "comp9322renewal@gmail.com", notice.getAccess_token()))
				request.getRequestDispatcher("/licenses").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
