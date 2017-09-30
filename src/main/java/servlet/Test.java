package servlet;

import http.HttpDeleteDemo;
import http.HttpGetDemo;
import http.HttpPostDemo;
import http.HttpPutDemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RenewalNotices;

import com.google.gson.Gson;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				servletResponse.getWriter().append("Served at: ").append(servletRequest.getContextPath());
				Gson gson = new Gson();
				String method = servletRequest.getParameter("method");
				if(method.equals("get")){
					String url = "http://localhost:8090/renewals";
					HttpGetDemo request = new HttpGetDemo("access_token", url);
					servletResponse.getWriter().append(request.sendGetRequest());
				} else if (method.equals("post")){
					String url = "http://localhost:8090/renewals";
					RenewalNotices renewalNotices = new RenewalNotices();
					renewalNotices.setAddress("access_token");
					renewalNotices.setContact_email("new email");
					renewalNotices.setLicid(1);
					HttpPostDemo request = new HttpPostDemo(url, "access_token", gson.toJson(renewalNotices));
					servletResponse.getWriter().append(request.sendPostRequest());
				} else if(method.equals("put")){
					String url = "http://localhost:8090/renewals";
					RenewalNotices renewalNotices = new RenewalNotices();
					renewalNotices.setAddress("access_token");
					renewalNotices.setContact_email("changed email");
					renewalNotices.setLicid(1);
					renewalNotices.setNid(9);
					HttpPutDemo request = new HttpPutDemo(url, "access_token", gson.toJson(renewalNotices));
					servletResponse.getWriter().append(request.sendPutRequest());
				} else if(method.equals("delete")){
					String url = "http://localhost:8090/renewals/6";
					HttpDeleteDemo request = new HttpDeleteDemo("access_token", url);
					servletResponse.getWriter().append(request.sendDeleteRequest());
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
