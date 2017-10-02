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
import model.RenewalNotices;
import response.JsonResponseList;

/**
 * Servlet implementation class RenewalNoticeListServlet
 */
public class RenewalNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RenewalNoticeListServlet() {
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
		String url = "http://localhost:8090/renewals";
		HttpGetDemo request = new HttpGetDemo("123456", url);
		String responseJson = request.sendGetRequest();
		JsonResponseList<RenewalNotices> jsonObject = new Gson().fromJson(responseJson,
				new TypeToken<JsonResponseList<RenewalNotices>>() {
				}.getType());
		List<RenewalNotices> notices = jsonObject.getList();
		servletRequest.setAttribute("notices", notices);
		servletRequest.getRequestDispatcher("/WEB-INF/jsp/NoticeList.jsp").forward(servletRequest, servletResponse);
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
