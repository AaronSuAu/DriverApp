package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		// 判断是否是http请求
		if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);

		String[] strs = { "login" }; // 路径中包含这些字符串的,可以不用登录直接访问
		StringBuffer url = httpRequest.getRequestURL();

		/**
		 * 过滤掉根目录
		 */
		String path = httpRequest.getContextPath();
		String protAndPath = httpRequest.getServerPort() == 80 ? "" : ":" + httpRequest.getServerPort();
		String basePath = httpRequest.getScheme() + "://" + httpRequest.getServerName() + protAndPath + path + "/";
		if (basePath.equalsIgnoreCase(url.toString())) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		// 特殊用途的路径可以直接访问
		if (strs != null && strs.length > 0) {
			for (String str : strs) {
				if (url.indexOf(str) >= 0) {
					filterChain.doFilter(servletRequest, servletResponse);
					return;
				}
			}
		}
		// 从session中获取用户信息
		String loginInfo = (String) session.getAttribute("user");
		if (null != loginInfo && !"".equals(loginInfo)) {
			// 用户存在,可以访问此地址
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			// 用户不存在,踢回登录页面
			httpResponse.sendRedirect("/AssignValidationClient/login");
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
