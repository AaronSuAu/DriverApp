package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OfficerLoginServlet
 */
public class OfficerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OfficerLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			// response.sendRedirect("/WEB-INF/jsp/OfficerLogin.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/OfficerLogin.jsp").forward(request, response);
		} else {
			username = username.trim();
			password = password.trim();
		}
		if (username.equals("officer1") || username.equals("officer2") || username.equals("officer3")) {
			if (password.equals("123456")) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				response.sendRedirect("/AssignValidationClient/licenses");
				// request.getRequestDispatcher("/licenses").forward(request,
				// response);
			} else {
				request.setAttribute("error", "Incorrect password!");
				request.getRequestDispatcher("/WEB-INF/jsp/OfficerLogin.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "No such officer!");
			request.getRequestDispatcher("/WEB-INF/jsp/OfficerLogin.jsp").forward(request, response);
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
