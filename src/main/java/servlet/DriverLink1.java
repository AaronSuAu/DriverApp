package servlet;

import java.io.IOException;
import java.util.List;

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
import model.CarLicenses;
import model.FeePayments;
import model.LicenseNotice;
import model.RenewalNotices;
import response.JsonResponseList;


/**
 * Servlet implementation class DriverLink1
 */
public class DriverLink1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverLink1() {
        super();
        // TODO Auto-generated constructor stub
    }
    public FeePayments getPayments(RenewalNotices rNotices){
    		HttpGetDemo getDemo2 = new HttpGetDemo(HttpToken.CLIENT_TOKEN, HttpToken.ROOT_URL+"/payments/nid/"+rNotices.getNid());
		String result = getDemo2.sendGetRequest();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
			JsonResponseList<FeePayments> jsonList= new Gson().fromJson(result, new TypeToken<JsonResponseList<FeePayments>>() {
			}.getType());
			if(jsonList.getList().size() != 0){
				return jsonList.getList().get(0);
			}
		}
		return null;
    }
    
    public CarLicenses getLicense(RenewalNotices rNotices){
    		HttpGetDemo getDemo = new HttpGetDemo(HttpToken.CLIENT_TOKEN, HttpToken.ROOT_URL+"/licenses/"+rNotices.getLicid());
    		String result = getDemo.sendGetRequest();
    		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
    		if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
	    		JsonResponseList<CarLicenses> jsonList= new Gson().fromJson(result, new TypeToken<JsonResponseList<CarLicenses>>() {
				}.getType());
	    		if(jsonList.getList().size() == 1){
	    			return jsonList.getList().get(0);
	    		}
    		}
    		return null;
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(servletRequest.getParameter("token") != null){
			String token = servletRequest.getParameter("token");
			HttpGetDemo getDemo = new HttpGetDemo(HttpToken.CLIENT_TOKEN, HttpToken.ROOT_URL+"/renewals/token/"+token);
			String result = getDemo.sendGetRequest();
			JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
			if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
				JsonResponseList<RenewalNotices> jsonList= new Gson().fromJson(result, new TypeToken<JsonResponseList<RenewalNotices>>() {
				}.getType());
				if(jsonList.getList().size() == 1){
					RenewalNotices rNotices = jsonList.getList().get(0);
					String status = rNotices.getStatus().toLowerCase();
					//set session of the notice id 
					servletRequest.getSession().setAttribute("noticeId", rNotices.getNid());
					if(status.equals("archived")){
						servletRequest.getRequestDispatcher("/WEB-INF/jsp/TokenNotValidated.jsp").forward(servletRequest, servletResponse);
				        return;
					} else if(status.equals("new")){
						CarLicenses carLicenses = getLicense(rNotices);
						if(carLicenses != null){
							servletRequest.setAttribute("carLicenses", carLicenses);
							servletRequest.setAttribute("renewalNotices", rNotices);
							servletRequest.getRequestDispatcher("/WEB-INF/jsp/UpdateAddressAndEmail.jsp").forward(servletRequest, servletResponse);
					        return;
						}
						
					} else if(status.equals("failed")){
						servletRequest.getRequestDispatcher("/WEB-INF/jsp/AddressError.jsp").forward(servletRequest, servletResponse);
				        return;
					} else if(status.equals("validated")){
						servletRequest.setAttribute("renewalNotices", rNotices);
						servletRequest.getRequestDispatcher("/WEB-INF/jsp/Extend.jsp").forward(servletRequest, servletResponse);
				        return;
					} else if(status.equals("5yearreview")){
						servletRequest.getRequestDispatcher("/WEB-INF/jsp/ExtendWait.jsp").forward(servletRequest, servletResponse);
				        return;
					} else if(status.equals("rejected") || status.equals("accepted")){
						// It will still go to the payment page to renew for one year
						// even if it is rejected. 
						servletRequest.setAttribute("renewalNotices", rNotices);
						FeePayments fPayments = getPayments(rNotices);
						if(fPayments != null){
							servletRequest.setAttribute("feePayments", fPayments);
							servletRequest.getSession().setAttribute("paymentId", fPayments.getPid());
							servletRequest.getRequestDispatcher("/WEB-INF/jsp/Payment.jsp").forward(servletRequest, servletResponse);
					        return;
						}	
					} 
				}
			}
		}
		servletRequest.getRequestDispatcher("/WEB-INF/jsp/TokenNotValidated.jsp").forward(servletRequest, servletResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
