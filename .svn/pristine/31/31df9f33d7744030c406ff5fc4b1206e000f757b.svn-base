/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller để xử lý cho màn hình ADM001
 * 
 * @author Phan Van Hiep
 */
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi người dùng click button đăng nhập
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session
			HttpSession session = req.getSession();
			// Lấy giá trị loginName và pass từ request mà người dùng nhập
			String loginName = req.getParameter(Constant.NAME_TEXTBOX_LOGINNAME);
			String pass = req.getParameter(Constant.NAME_TEXTBOX_PASSWORD);
			// Khai báo 1 list để lấy kết quả trả về từ hàm validateLogin
			List<String> listErr = ValidateUser.validateLogin(loginName, pass);
			
			// Nếu danh sách lỗi rỗng(Tồn tại loginName và pass trong DB)
			if (listErr.size() == 0) {
				// chuyển hướng đến URL listuser.do
				resp.sendRedirect(Constant.URL_LISTUSER + Constant.URL_TYPE_DEFAULT);
				// gán loginName lên session để check đã login ở các màn hình
				session.setAttribute(Constant.SESSION_LOGINNAME, loginName);
				// Nếu có validate có lỗi
			} else {
				// gán danh sách lỗi lên Request
				req.setAttribute(Constant.REQUEST_LISTERROR, listErr);
				// gán loginName lên request để giữ lại giá trị loginName trên
				// textbox
				req.setAttribute(Constant.REQUEST_LOGINNAME, loginName);
				// forward đến trang ADM001.jsp
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM001).forward(req, resp);
			}
			// Nếu có lỗi
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : LoginServletController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}

	/**
	 * Xử lí khi người dùng vào web và khi logout
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Di chuyển về màn hình ADM001
			req.getServletContext().getRequestDispatcher(Constant.PATH_ADM001).forward(req, resp);
			// Nếu có lỗi
		} catch (Exception e) {
			// Hiển thị lỗi
			System.out.println("Error : LoginServletController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}

	}
}
