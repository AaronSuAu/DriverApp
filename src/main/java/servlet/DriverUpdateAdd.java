package servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
import soap.CheckAddressRequest;
import soap.CheckAddressResponse;
import soap.CheckEmailAddressRequest;
import soap.CheckEmailAddressResponse;
import soap.EmployeeValidationService;
import soap.EmployeeValidationServiceImplService;
import soap.ObjectFactory;
import soap.ValidationFaultMsg;

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

	public static RenewalNotices getRenewalsByNid(int nid) {
		HttpGetDemo getDemo = new HttpGetDemo(HttpToken.CLIENT_TOKEN, HttpToken.ROOT_URL + "/renewals/" + nid);
		String result = getDemo.sendGetRequest();
		if (result == null) {
			return null;
		}
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		if (jsonObject.has("code") && jsonObject.get("code").toString().equals("200")) {
			JsonResponseList<RenewalNotices> jsonList = new Gson().fromJson(result,
					new TypeToken<JsonResponseList<RenewalNotices>>() {
					}.getType());
			if (jsonList.getList().size() != 0) {
				return jsonList.getList().get(0);
			}
		}
		return null;
	}

	public static JsonObject updateRenewas(RenewalNotices rNotices) {
		HttpPutDemo putDemo = new HttpPutDemo(HttpToken.ROOT_URL + "/renewals", HttpToken.CLIENT_TOKEN,
				new Gson().toJson(rNotices));
		String result = putDemo.sendPutRequest();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		return jsonObject;
	}
	

	public static boolean soapValidate(String email) {
		EmployeeValidationServiceImplService serviceImpl;
		try {
			serviceImpl = new EmployeeValidationServiceImplService(
					new URL("http://192.168.99.101:8888/assignment1/EmployeeValidation?wsdl"));
			EmployeeValidationService validationService = serviceImpl.getEmployeeValidationServiceImplPort();
			ObjectFactory objectFactory = new soap.ObjectFactory();
			CheckEmailAddressRequest checkEmailAddressRequest = objectFactory.createCheckEmailAddressRequest();
			checkEmailAddressRequest.setEmail(email);
			CheckEmailAddressResponse checkEmailAddressResponse = validationService
					.checkEmailAddress(checkEmailAddressRequest);
			if (checkEmailAddressResponse.isValue()) {
				return true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public static String soapValidate(String preStreet, String streetType,
			String streetName, String suburb, String state, RenewalNotices rNotices) {
		EmployeeValidationServiceImplService serviceImpl;
		String result = "";
		try {
			serviceImpl = new EmployeeValidationServiceImplService(
					new URL("http://192.168.99.101:8888/assignment1/EmployeeValidation?wsdl"));
			EmployeeValidationService validationService = serviceImpl.getEmployeeValidationServiceImplPort();
			ObjectFactory objectFactory = new soap.ObjectFactory();
			CheckAddressRequest checkAddressRequest = objectFactory.createCheckAddressRequest();
			checkAddressRequest.setPreStreet(preStreet);
			checkAddressRequest.setState(state);
			checkAddressRequest.setStreetType(streetType);
			checkAddressRequest.setStreetName(streetName);
			checkAddressRequest.setSuburb(suburb);
			CheckAddressResponse checkAddressResponse;
			try {
				checkAddressResponse = validationService.checkAddress(checkAddressRequest);
				String exactAddress = checkAddressResponse.getExactAddress();
				rNotices.setAddress(exactAddress);
			} catch (ValidationFaultMsg e) {
				result = "Error code: "+e.getFaultInfo().getErrcode()+", Error text: "+e.getFaultInfo().getErrtext();
				return result;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	public boolean isFailed(HttpServletRequest request, RenewalNotices rNotices){
		if(request.getSession().getAttribute("failedTimes") == null){
			request.getSession().setAttribute("failedTimes", (Integer)1);
			return false;
		} else{
			Integer times = (Integer) request.getSession().getAttribute("failedTimes");
			if(times < 3){
				times ++;
				request.getSession().setAttribute("failedTimes", times);
				return false;
			}else{
				request.getSession().setAttribute("failedTimes", 0);
				rNotices.setStatus("failed");
				updateRenewas(rNotices);
				
				return true;
			}
		}
		
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
		if (servletRequest.getSession().getAttribute("nId") == null) {
			servletResponse.getWriter().append("Error");
			return;
		}

		int nid = Integer.parseInt(servletRequest.getSession().getAttribute("nId").toString());
		RenewalNotices rNotices = getRenewalsByNid(nid);
		String email = servletRequest.getParameter("email").toString();
		if(rNotices == null){
			servletResponse.getWriter().append("Error");
			return;
		}
		if(!soapValidate(email)){
			servletResponse.getWriter().append("Email not validate!, please go back");
			return;
		}
		if(servletRequest.getParameter("change").toString().equals("false")){
			// address unchanged, go to the next page without validating the address
			rNotices.setContact_email(email);
			rNotices.setStatus("validated");
			JsonObject jsonObject = updateRenewas(rNotices);
			if (jsonObject.has("code") && jsonObject.get("code").toString().equals("200")) {
				servletRequest.getRequestDispatcher("/WEB-INF/jsp/Extend.jsp").forward(servletRequest, servletResponse);
				return;
			} else {
				servletResponse.getWriter().append("Server Error. Try later");
				return;
			}
		}else{
			// address changed
			String preStreet = servletRequest.getParameter("preStreet").toString().trim();
			String streetType = servletRequest.getParameter("streetType").toString().trim();
			String streetName = servletRequest.getParameter("streetName").toString().trim();
			String suburb = servletRequest.getParameter("suburb").toString().trim();
			String state = servletRequest.getParameter("state").toString().trim();
			String result = soapValidate(preStreet, streetType,
					streetName, suburb, state, rNotices);
			if(!result.equals("success")){
				// validate fail
				if(isFailed(servletRequest, rNotices)){
					// failed 3 times
					//servletRequest.setAttribute("reason", result);
					servletRequest.getRequestDispatcher("/WEB-INF/jsp/AddressError.jsp").forward(servletRequest, servletResponse);
					return;
				}else{
					// failed but the driver can still try
					servletRequest.setAttribute("reason", result);
					servletRequest.setAttribute("token", rNotices.getAccess_token());
					servletRequest.getRequestDispatcher("/WEB-INF/jsp/AddressTryAgain.jsp").forward(servletRequest, servletResponse);
					return;
				}
			}
			//address correct. update.
			rNotices.setStatus("validated");
			JsonObject jsonObject = updateRenewas(rNotices);
			if (jsonObject.has("code") && jsonObject.get("code").toString().equals("200")) {
				servletRequest.getRequestDispatcher("/WEB-INF/jsp/Extend.jsp").forward(servletRequest, servletResponse);
			} else {
				servletResponse.getWriter().append("Server Error. Try later");
			}
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
