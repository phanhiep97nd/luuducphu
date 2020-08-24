/**
 * Copyright(C) 2020  Luvina Software
 * SystemErrorController.java, Jul 26, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Xử lí khi có lỗi
 * 
 * @author Phan Van Hiep
 */
public class SystemErrorController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi có lỗi
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy message từ req
		String messReq = req.getParameter(Constant.REQUEST_MESSAGE);
		// Khai báo chuỗi chứa lỗi
		String message = "";
		if (messReq == null) {
			message = MessageProperties.getValueByKey(Constant.ER015);
		} else if (Constant.ER013.equals(messReq)) {
			message = MessageProperties.getValueByKey(Constant.ER013);
		} else if (Constant.ER020.equals(messReq)) {
			message = MessageProperties.getValueByKey(Constant.ER020);
		} else if (Constant.ER014.equals(messReq)) {
			message = MessageProperties.getValueByKey(Constant.ER014);
		}
		// gán lên req và forward sang màn hình systemError
		req.setAttribute(Constant.REQUEST_MESSAGE, message);
		req.getServletContext().getRequestDispatcher(Constant.PATH_SYSTEM_ERROR).forward(req, resp);
	}
}
