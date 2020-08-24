/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;

/**
 * Controller để xử lý  khi người dùng click lickLogout
 * 
 * @author Phan Van Hiep
 */
public class LogoutController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi người dùng click link logout
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khai báo và khởi tạo session từ request
			HttpSession session = req.getSession();
			// Hủy session
			session.invalidate();
			// chuyển hướng về trang login
			resp.sendRedirect(Constant.URL_LOGIN);
			// Nếu có lỗi
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : LoginServletController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}
	}
}
