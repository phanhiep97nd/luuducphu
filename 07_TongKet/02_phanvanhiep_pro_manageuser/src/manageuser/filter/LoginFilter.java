/**
 * Copyright(C) 2020  Luvina Software
LoginFilter.java, Aug 17, 2020 Phan Van Hiep
 */
package manageuser.filter;

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

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Filter xử lí check đăng nhập khi vào các màn hình
 * 
 * @author Phan Van Hiep
 */
public class LoginFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// Ép kiểu dữ liệu sang HttpServlet
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// Lấy path của servlet
		String reqPath = request.getServletPath();
		// lấy session từ request
		HttpSession session = request.getSession();

		try {
			// Nếu vào MH login hoặc đẵ đăng nhập
			if (reqPath.equals(Constant.PATH_LOGIN_CONTROLLER) || reqPath.equals(Constant.PATH_SYSTEMERROR_CONTROLLER) || Common.checkLogin(session)) {
				// Cho phép request vượt qua filter
				chain.doFilter(request, response);
				// Nếu chưa login
			} else {
				// Redirect sang MH login
				response.sendRedirect(Constant.URL_LOGIN);
			}
			// Bắt exception
		} catch (Exception e) {
			// Hiển thị ở MH console
			System.out.println("Error : filter.LoginFilter " + e.getMessage());
			// Chuyển đến màn hình System_Error
			response.sendRedirect(Constant.URL_SYSTEMERROR);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
