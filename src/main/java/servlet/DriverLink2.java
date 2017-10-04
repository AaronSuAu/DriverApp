package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import http.HttpGetDemo;
import http.HttpToken;
import model.FeePayments;
import model.LicenseNotice;
import model.RenewalNotices;
import response.JsonResponseList;

/**
 * Servlet implementation class DriverLink2
 */
public class DriverLink2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DriverLink2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static LicenseNotice getLNByNid(int nid) {
		HttpGetDemo getDemo2 = new HttpGetDemo(HttpToken.CLIENT_TOKEN,
				HttpToken.ROOT_URL + "/licenseNotice/nid/" + nid);
		String result = getDemo2.sendGetRequest();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		if (jsonObject.has("code") && jsonObject.get("code").toString().equals("200")) {
			JsonResponseList<LicenseNotice> jsonList = new Gson().fromJson(result,
					new TypeToken<JsonResponseList<LicenseNotice>>() {
					}.getType());
			if (jsonList.getList().size() != 0) {
				return jsonList.getList().get(0);
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (servletRequest.getParameter("token") != null) {
			String token = servletRequest.getParameter("token");
			HttpGetDemo getDemo = new HttpGetDemo(HttpToken.CLIENT_TOKEN,
					HttpToken.ROOT_URL + "/licenseNotice/token/" + token);
			String result = getDemo.sendGetRequest();
			JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
			if (jsonObject.has("code") && jsonObject.get("code").toString().equals("200")) {
				JsonResponseList<LicenseNotice> jsonList = new Gson().fromJson(result,
						new TypeToken<JsonResponseList<LicenseNotice>>() {
						}.getType());
				if (jsonList.getList().size() != 1) {

					servletRequest.getRequestDispatcher("/WEB-INF/jsp/TokenNotValidated.jsp").forward(servletRequest,
							servletResponse);
					return;
				} else {
					LicenseNotice ln = jsonList.getList().get(0);
					servletRequest.setAttribute("licenseNotice", ln);
					servletRequest.getRequestDispatcher("/WEB-INF/jsp/DriverInfo.jsp").forward(servletRequest,
							servletResponse);
					return;
				}

			}
		}
		servletRequest.getRequestDispatcher("/WEB-INF/jsp/TokenNotValidated.jsp").forward(servletRequest,
				servletResponse);

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
