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
import model.LicenseNotice;
import response.JsonResponseList;

/**
 * Servlet implementation class LicenseListServlet
 */
public class LicenseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LicenseListServlet() {
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
		String url = "http://localhost:8090/licenseNotice";
		HttpGetDemo request = new HttpGetDemo("123456", url);
		String responseJson = request.sendGetRequest();
		JsonResponseList<LicenseNotice> jsonObject = new Gson().fromJson(responseJson,
				new TypeToken<JsonResponseList<LicenseNotice>>() {
				}.getType());
		List<LicenseNotice> licenses = jsonObject.getList();
		servletRequest.setAttribute("licenses", licenses);
		servletRequest.getRequestDispatcher("LicenseList.jsp").forward(servletRequest, servletResponse);
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
