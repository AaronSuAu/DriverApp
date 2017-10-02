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
import http.HttpPutDemo;
import http.HttpToken;
import model.FeePayments;
import model.RenewalNotices;
import response.JsonResponseList;

/**
 * Servlet implementation class DriverUpdateAdd
 */
public class DriverUpdateAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverUpdateAdd() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static RenewalNotices getRenewalsByNid(int nid){
		HttpGetDemo getDemo = new HttpGetDemo(HttpToken.CLIENT_TOKEN, HttpToken.ROOT_URL+"/renewals/"+nid);
    		String result = getDemo.sendGetRequest();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
			JsonResponseList<RenewalNotices> jsonList= new Gson().fromJson(result, new TypeToken<JsonResponseList<RenewalNotices>>() {
			}.getType());
			if(jsonList.getList().size() != 0){
				return jsonList.getList().get(0);
			}
		}
		return null;
    }
    
    public static JsonObject updateRenewas(RenewalNotices rNotices){
    		HttpPutDemo putDemo = new HttpPutDemo(HttpToken.ROOT_URL+"/renewals", HttpToken.CLIENT_TOKEN, new Gson().toJson(rNotices));
		String result = putDemo.sendPutRequest();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		return jsonObject;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(servletRequest.getSession().getAttribute("nId") == null){
			servletResponse.getWriter().append("Error");
			return;
		} 
		
		int nid = Integer.parseInt(servletRequest.getSession().getAttribute("nId").toString());
		RenewalNotices rNotices = getRenewalsByNid(nid);
		String email = servletRequest.getParameter("email").toString();
		String address = servletRequest.getParameter("address").toString();
		if(rNotices!= null){
			rNotices.setAddress(address);
			rNotices.setContact_email(email);
			rNotices.setStatus("validated");
			HttpPutDemo putDemo = new HttpPutDemo(HttpToken.ROOT_URL+"/renewals", HttpToken.CLIENT_TOKEN, new Gson().toJson(rNotices));
			String result = putDemo.sendPutRequest();
			JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
			if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
				servletRequest.getRequestDispatcher("/WEB-INF/jsp/Extend.jsp").forward(servletRequest, servletResponse);
			}else{
				servletResponse.getWriter().append("Server Error. Try later");

			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
