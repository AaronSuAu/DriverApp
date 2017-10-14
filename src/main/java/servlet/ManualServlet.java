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
import http.HttpToken;
import model.FeePayments;
import model.RenewalNotices;
import response.JsonResponseList;

/**
 * Servlet implementation class ManualServlet
 */
public class ManualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManualServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int nid = Integer.parseInt(servletRequest.getParameter("nid"));
		String url = "http://localhost:8090/renewals/" + nid;
		HttpGetDemo request = new HttpGetDemo(HttpToken.OFFICER_TOKEN, url);
		String responseJson = request.sendGetRequest();
		JsonResponseList<RenewalNotices> jsonObject = new Gson().fromJson(responseJson,
				new TypeToken<JsonResponseList<RenewalNotices>>() {
				}.getType());
		List<RenewalNotices> notices = jsonObject.getList();
		// get payment
		url = "http://localhost:8090/payments/nid/" + nid;
		request = new HttpGetDemo(HttpToken.OFFICER_TOKEN, url);
		responseJson = request.sendGetRequest();
		JsonResponseList<FeePayments> paymentJson = new Gson().fromJson(responseJson,
				new TypeToken<JsonResponseList<FeePayments>>() {
				}.getType());
		List<FeePayments> payments = paymentJson.getList();
		servletRequest.setAttribute("notice", notices.get(0));
		servletRequest.setAttribute("payment", payments.get(0));
		servletRequest.getRequestDispatcher("/WEB-INF/jsp/ManualProcess.jsp").forward(servletRequest, servletResponse);
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
