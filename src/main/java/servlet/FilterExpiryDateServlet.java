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
import model.LicenseNotice;
import response.JsonResponse;
import response.JsonResponseList;

/**
 * Servlet implementation class FilterExpiryDateServlet
 */
public class FilterExpiryDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilterExpiryDateServlet() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String daysString = servletRequest.getParameter("days");
		int days = -3650;
		try {
			days = Integer.parseInt(daysString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String url = "http://localhost:8090/licenseNotice/expiry/" + days;
		HttpGetDemo request = new HttpGetDemo(HttpToken.OFFICER_TOKEN, url);
		String responseJson = request.sendGetRequest();
		try {
			JsonResponseList<LicenseNotice> jsonObject = new Gson().fromJson(responseJson,
					new TypeToken<JsonResponseList<LicenseNotice>>() {
					}.getType());
			List<LicenseNotice> licenses = jsonObject.getList();
			servletRequest.setAttribute("licenses", licenses);
			servletRequest.getRequestDispatcher("/WEB-INF/jsp/LicenseList.jsp").forward(servletRequest,
					servletResponse);
		} catch (Exception e) {
			// TODO: handle exception
			JsonResponse jsonResponse = new Gson().fromJson(responseJson, JsonResponse.class);
			servletRequest.setAttribute("error",
					jsonResponse.getDescription() + "(code: " + jsonResponse.getCode() + ")");
			servletRequest.getRequestDispatcher("/WEB-INF/jsp/LicenseList.jsp").forward(servletRequest,
					servletResponse);
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
