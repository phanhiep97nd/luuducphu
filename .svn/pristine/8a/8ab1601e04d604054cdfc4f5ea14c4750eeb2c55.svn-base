/**
 * Copyright(C) 2020  Luvina Software
 * ShowUserController.java, Jul 26, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfoEntity;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Hiển thị thông tin chi tiết User
 * 
 * @author Phan Van Hiep
 */
public class ViewDetailUserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí phương thức doGet
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khai báo session
			HttpSession session = req.getSession();
			// Khai báo đối tượng UserInfoEntity
			UserInfoEntity userInfoEntity = null;
			// Khởi tạo đối tượng TblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();

			// Lấy id từ request
			int userId = Common.convertStringToInt(req.getParameter(Constant.REQUEST_ID), Constant.USER_ID_DEFAULT);
			// Nếu tồn tại user
			if (!tblUserLogic.checkExistUserById(userId)) {
				// Thông báo lỗi không tồn tại Id
				resp.sendRedirect(Constant.URL_SYSTEMERROR + Constant.URL_ERROR_NOTEXIST_ID);
			} else {
				// Lấy về giá trị cho đối tượng userInfoEntity
				userInfoEntity = tblUserLogic.getUserInfoByUserId(userId);
				// gán userInfoEntity lên req
				req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
				// forward sang trang ADM002
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM005).forward(req, resp);
			}
		} catch (Exception e) {
			// Hiển thị lỗi
			System.out.println("Error : LoginServletController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}
}
