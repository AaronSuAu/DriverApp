package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import model.CarLicenses;
import model.FeePayments;
import model.RenewalNotices;
import response.JsonResponseList;

/**
 * Servlet implementation class DriverPayment
 */
public class DriverPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static CarLicenses getCarLicenses(RenewalNotices rNotices){
		int licid = rNotices.getLicid();
		HttpGetDemo getDemo = new HttpGetDemo(HttpToken.CLIENT_TOKEN, HttpToken.ROOT_URL+"/licenses/"+licid);
		String result = getDemo.sendGetRequest();
		if(result == null){
			return null;
		}
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
			JsonResponseList<CarLicenses> jsonList= new Gson().fromJson(result, new TypeToken<JsonResponseList<CarLicenses>>() {
		}.getType());
			if(jsonList.getList().size() != 0){
				return jsonList.getList().get(0);
			}
		}
		return null;
	}
    public static boolean updateLicenseExpiryDate(RenewalNotices rNotices){
    		int licid = rNotices.getLicid();
    		CarLicenses carLicenses = getCarLicenses(rNotices);
    		if(carLicenses!=null){
			String dateString = carLicenses.getExpiry_date();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
			Date startDate = null;
			try {
			    startDate = df.parse(dateString);
			    String newDateString = df.format(startDate);
			    System.out.println(newDateString);
			    Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				if(rNotices.getStatus().equals("validated")){
					cal.add(Calendar.YEAR, 1);
				} else if(rNotices.getStatus().equals("accepted")){
					cal.add(Calendar.YEAR, 5);
				}
				startDate = cal.getTime();
				carLicenses.setExpiry_date(df.format(startDate));
				HttpPutDemo putDemo = new HttpPutDemo(HttpToken.ROOT_URL+"/licenses", HttpToken.CLIENT_TOKEN, new Gson().toJson(carLicenses));
				String result = putDemo.sendPutRequest();
				JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
				if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
					return true;
				}
			} catch (ParseException e) {
			    e.printStackTrace();
			}
    		}
		return false;

    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(servletRequest.getSession().getAttribute("nId") == null){
			servletResponse.getWriter().append("Error");
			return;
		}
		int nid = Integer.parseInt(servletRequest.getSession().getAttribute("nId").toString());
		RenewalNotices rNotices = DriverUpdateAdd.getRenewalsByNid(nid);
		FeePayments fPayments = DriverLink1.getPayments(rNotices);
		
		if(fPayments != null){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			fPayments.setPaid_date(dateFormat.format(date));
			HttpPutDemo putDemo = new HttpPutDemo(HttpToken.ROOT_URL+"/payments", HttpToken.CLIENT_TOKEN, new Gson().toJson(fPayments));
			String result = putDemo.sendPutRequest();
			JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
			if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200") && updateLicenseExpiryDate(rNotices)){
				rNotices.setStatus("archived");
				//
				HttpPutDemo putDemo2 = new HttpPutDemo(HttpToken.ROOT_URL+"/renewals", HttpToken.CLIENT_TOKEN, new Gson().toJson(rNotices));
				String result2 = putDemo2.sendPutRequest();
				JsonObject jsonObject2 = new JsonParser().parse(result2).getAsJsonObject();
				if(jsonObject2.has("code") && jsonObject2.get("code").toString().equals("200")){
					servletRequest.getRequestDispatcher("/WEB-INF/jsp/PaymentSuccess.jsp").forward(servletRequest, servletResponse);
					return;
				}
			}
		}
		servletResponse.getWriter().append("Server Error. Try later");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
