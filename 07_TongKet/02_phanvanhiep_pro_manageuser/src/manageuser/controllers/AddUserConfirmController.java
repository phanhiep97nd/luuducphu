/**
 * Copyright(C) 2020  Luvina Software
AddUserConfirmController.java, Jul 29, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;

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
 * Controller xử lí hiển thị thông tin để confirm add và thực hiện add user
 * 
 * @author Phan Van Hiep
 */
public class AddUserConfirmController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * xử lí khi nhấn xác nhận ở màn hình ADM003
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khai báo session
			HttpSession session = req.getSession();
			// Lấy về keyConfirm từ ADM003 trên session
			String confirmADM003 = (String) session.getAttribute(Constant.SESSION_CONFIRM_ADM003);
			// nếu từ màn ADM003 click xác nhận
			if (Common.compareString(Constant.SESSION_CONFIRM_ADM003, confirmADM003)) {
				// Xóa session confirmAdDM003
				session.removeAttribute(Constant.SESSION_CONFIRM_ADM003);
				// Lấy key từ req
				String key = req.getParameter(Constant.REQUEST_KEY);
				// lấy userInfoEntity từ session
				UserInfoEntity userInfoEntity = (UserInfoEntity) session
						.getAttribute(Constant.SESSION_ADDUSER_CONFIRM + key);
				// truyền userInfoEntity vào request
				req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
				// truyền key lên req
				req.setAttribute(Constant.REQUEST_KEY, key);
				// forward
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM004).forward(req, resp);

				// không phải là từ click xác nhận ở màn hình ADM003
			} else {
				// hiển thị ra màn hình ADM002
				resp.sendRedirect(Constant.URL_SYSTEMERROR);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserConfirmController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}

	/*
	 * Xử lí khi nhấn button Ok ở ADM004
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khai báo session
			HttpSession session = req.getSession();
			// key từ request
			String key = req.getParameter(Constant.REQUEST_KEY);
			// lấy userInfoEntity từ session
			UserInfoEntity userInfoEntity = (UserInfoEntity) session
					.getAttribute(Constant.SESSION_ADDUSER_CONFIRM + key);
			// xóa giá trị userInfo trên sesion
			session.removeAttribute(Constant.SESSION_ADDUSER_CONFIRM + key);
			// khởi tạo đối tượng tblUserLogicImpl
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Kiểm tra tồn tại loginName và email
			boolean checkExistLoginName = tblUserLogicImpl.checkExistedLoginName(userInfoEntity.getLoginName());
			boolean checkExistEmail = tblUserLogicImpl.checkExistedEmail(userInfoEntity.getEmail(),
					userInfoEntity.getUserId());
			// Nếu không tồn tại loginName và email
			if (!checkExistLoginName && !checkExistEmail) {
				// gọi đến hàm createUser
				boolean checkInsert = tblUserLogicImpl.createUser(userInfoEntity);
				// Nếu thêm thành công
				if (checkInsert) {
					// gọi đến URL Success
					resp.sendRedirect(Constant.URL_SUCCESS + Constant.TYPE_ADD_SUCCESS);
				} else {
					// Chuyển đến màn hình System_Error
					resp.sendRedirect(Constant.URL_SYSTEMERROR);
				}
			} else {
				// Chuyển đến màn hình System_Error
				resp.sendRedirect(Constant.URL_SYSTEMERROR);
			}

		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserConfirmController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}
}
