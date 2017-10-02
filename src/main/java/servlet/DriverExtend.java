package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import model.FeePayments;
import model.RenewalNotices;

/**
 * Servlet implementation class DriverExtend
 */
public class DriverExtend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverExtend() {
        super();
        // TODO Auto-generated constructor stub
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
		RenewalNotices rNotices = DriverUpdateAdd.getRenewalsByNid(nid);
		if(rNotices == null){
			servletResponse.getWriter().append("Error");
			return;
		}
		String extend = servletRequest.getParameter("extend").toString();
		if(extend != null &&extend.equals("true")){
			rNotices.setStatus("5YearReview");
			JsonObject jsonObject = DriverUpdateAdd.updateRenewas(rNotices);
			if(jsonObject.has("code") && jsonObject.get("code").toString().equals("200")){
				servletRequest.getRequestDispatcher("/WEB-INF/jsp/ExtendWait.jsp").forward(servletRequest, servletResponse);
			}else{
				servletResponse.getWriter().append("Server Error. Try later");
			}
		}else{
			servletRequest.setAttribute("renewalNotices", rNotices);
			FeePayments fPayments = DriverLink1.getPayments(rNotices);
			if(fPayments != null){
				servletRequest.setAttribute("feePayments", fPayments);
				servletRequest.getSession().setAttribute("paymentId", fPayments.getPid());
				servletRequest.getRequestDispatcher("/WEB-INF/jsp/Payment.jsp").forward(servletRequest, servletResponse);
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
