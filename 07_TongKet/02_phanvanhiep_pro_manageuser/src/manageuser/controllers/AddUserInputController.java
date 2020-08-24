/**
 * Copyright(C) 2020  Luvina Software
 * AddUserController.java, Jul 27, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller để xử lý cho màn hình ADM003 truong hợp Add
 * 
 * @author Phan Van Hiep
 */
public class AddUserInputController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lý khi click vào button Add của ADM002
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			// Khởi tạo session từ request
			HttpSession session = req.getSession();

			// gọi đến hàm setDataLogic để truyền các giá trị của pulldown
			// lên req
			Common.setDataLogic(req);
			// lấy userInfoEntity
			UserInfoEntity userInfoEntity = getDefaultValue(req);

			// gán userInfoEntity lên req
			req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);

			// forward đến trang ADM003
			req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserInputController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}

	/**
	 * Xử lý khi click vào button Xác nhận của ADM003
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			// Khởi tạo session từ request
			HttpSession session = req.getSession();
			// Khởi tạo listError để lấy về danh sách lỗi từ hàm
			// valadateUserInfor
			List<String> listError = new ArrayList<>();
			// Khởi tạo đối tượng UserInfoEntity để lấy về giá trị từ hàm
			// getDefaultValue
			UserInfoEntity userInfoEntity = new UserInfoEntity();

			// gán giá trị trả về từ hàm UserInfoEntity vào đối tượng
			// userInfoEntity
			userInfoEntity = getDefaultValue(req);

			// gán giá trị trả về từ hàm validateuserInfor vào listError
			listError = ValidateUser.validateUserInfor(userInfoEntity);
			// Kiểm tra xem có lỗi hay không
			if (listError.size() == 0) {
				// lấy ra key
				String key = Common.generateSalt();
				// gán userInfoEntity lên sesion
				session.setAttribute(Constant.SESSION_ADDUSER_CONFIRM + key, userInfoEntity);
				// gán lên session một giá trị confirmADM003 để kiểm tra có
				// từ màn ADM003 qua hay ko
				session.setAttribute(Constant.SESSION_CONFIRM_ADM003, Constant.SESSION_CONFIRM_ADM003);
				// sendRedirect đến URL addUserConfirm
				resp.sendRedirect(Constant.URL_ADDUSERCONFIRM + key);
			} else {
				Common.setDataLogic(req);
				req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
				req.setAttribute(Constant.REQUEST_LISTERROR, listError);
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
			}

		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserInputController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}

	}

	/**
	 * Lấy về các giá trị mặc đinh của các các hạng mục màn hình ADM003 tương
	 * ứng với các thuộc tính của đối tượng UserInfoEntity
	 * 
	 * @return đối tượng tblUserInfoEntity
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */
	private UserInfoEntity getDefaultValue(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		try {
			// Khởi tạo listGroup để lưu tất cả các group lấy được
			ArrayList<MstGroupEntity> listMstGroup = new ArrayList<>();
			// Khởi tạo listMstJapan để lưu tất cả các group lấy được
			List<MstJapanEntity> listMstJapan = new ArrayList<>();

			// Khởi tạo đối tượng mstGroupLogicImpl
			MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
			// Khởi tạo đối tượng mstJapanLogicImpl
			MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();
			// Khởi tạo đối tượng UserInfoEntity
			UserInfoEntity userInforEntity = new UserInfoEntity();
			// Khởi tạo biến type để lấy type từ request
			String type = Constant.DEFAULT_EMPTY;
			// Khởi tạo biến lấy về ngày, tháng, năm hiện tại
			int yearNow = Common.getDate().get(Calendar.YEAR);
			int monthNow = Common.getDate().get(Calendar.MONTH) + 1;
			int dayNow = Common.getDate().get(Calendar.DATE);
			// Khai báo và khởi tạo giá trị default các thuộc tính của đối tượng
			// UserInfoEntity
			String loginName = Constant.DEFAULT_EMPTY;
			String groupName = Constant.DEFAULT_EMPTY;
			int groupId = Constant.DEFAULT_ZERO;
			String fullName = Constant.DEFAULT_EMPTY;
			String fullNameKatana = Constant.DEFAULT_EMPTY;
			String birthday = Common.setFormatDate(Integer.toString(yearNow), Integer.toString(monthNow),
					Integer.toString(dayNow));
			String email = Constant.DEFAULT_EMPTY;
			String tel = Constant.DEFAULT_EMPTY;
			String password = Constant.DEFAULT_EMPTY;
			String passwordConfirm = Constant.DEFAULT_EMPTY;
			String nameLevel = Constant.DEFAULT_EMPTY;
			String codeLevel = Constant.DEFAULT_EMPTY;
			String endDate = Common.setFormatDate(Integer.toString(yearNow + 1), Integer.toString(monthNow),
					Integer.toString(dayNow));
			String startDate = Common.setFormatDate(Integer.toString(yearNow), Integer.toString(monthNow),
					Integer.toString(dayNow));
			String total = Constant.DEFAULT_EMPTY;

			// Lấy về type từ req
			type = req.getParameter(Constant.REQUEST_TYPE);
			// Nếu từ màn hình ADM002 hoặc click button submit ở màn hình ADM003
			if (type == null || Common.compareString(Constant.TYPE_VALIDATE, type)) {
				if (Common.compareString(Constant.TYPE_VALIDATE, type)) {
					// Lấy các giá trị người dùng nhập từ request về
					loginName = req.getParameter(Constant.LOGIN_NAME_ADM003);
					groupId = Common.convertStringToInt(req.getParameter(Constant.GROUPID_ADM003),
							Constant.GROUPID_DEFAULT);
					if (groupId != 0) {
						// Lấy về groupName từ groupId
						groupName = mstGroupLogicImpl.getGroupName(groupId);
					}
					fullName = req.getParameter(Constant.FULL_NAME_ADM003);
					fullNameKatana = req.getParameter(Constant.FULL_NAME_KATANA_ADM003);
					String yearOfBirth = req.getParameter(Constant.YEAR_OF_BIRTH_ADM003);
					String monthOfBirth = req.getParameter(Constant.MONTH_OF_BIRTH_ADM003);
					String dayOfBirth = req.getParameter(Constant.DAY_OF_BIRTH_ADM003);
					birthday = Common.setFormatDate(yearOfBirth, monthOfBirth, dayOfBirth);
					email = req.getParameter(Constant.EMAIL_ADM003);
					tel = req.getParameter(Constant.TEL_ADM003);
					password = req.getParameter(Constant.PASSWORD_ADM003);
					passwordConfirm = req.getParameter(Constant.PASSWORD_CONFIRM_ADM003);
					codeLevel = req.getParameter(Constant.CODE_LEVEL_ADM003);
					if (!"".equals(codeLevel)) {
						// Lấy về nameLevel theo codeLevel
						nameLevel = mstJapanLogicImpl.getNameLevel(codeLevel);
						String startYear = req.getParameter(Constant.START_YEAR_ADM003);
						String startMonth = req.getParameter(Constant.START_MONTH_ADM003);
						String startDay = req.getParameter(Constant.START_DAY_ADM003);
						startDate = Common.setFormatDate(startYear, startMonth, startDay);
						String endYear = req.getParameter(Constant.END_YEAR_ADM003);
						String endMonth = req.getParameter(Constant.END_MONTH_ADM003);
						String endDay = req.getParameter(Constant.END_DAY_ADM003);
						endDate = Common.setFormatDate(endYear, endMonth, endDay);
						total = req.getParameter(Constant.TOTAL_ADM003).trim();
					}
				}

				// gán giá trị các thuộc tính đối tượng userInforEntity
				userInforEntity.setLoginName(loginName);
				userInforEntity.setGroupId(groupId);
				userInforEntity.setGroupName(groupName);
				userInforEntity.setFullName(fullName);
				userInforEntity.setFullNameKatana(fullNameKatana);
				userInforEntity.setBirthday(birthday);
				userInforEntity.setEmail(email);
				userInforEntity.setTel(tel);
				userInforEntity.setPassword(password);
				userInforEntity.setPasswordConfirm(passwordConfirm);
				userInforEntity.setNameLevel(nameLevel);
				userInforEntity.setCodeLevel(codeLevel);
				userInforEntity.setEndDate(endDate);
				userInforEntity.setStartDate(startDate);
				userInforEntity.setTotal(total);
			} else if (Common.compareString(Constant.TYPE_BACK, type)) {
				// khai báo session
				HttpSession session = req.getSession();
				// Lấy key từ req
				String key = req.getParameter("key");
				// lấy userInforEntity từ session
				userInforEntity = (UserInfoEntity) session.getAttribute(Constant.SESSION_ADDUSER_CONFIRM + key);
				// xóa session
				session.removeAttribute(Constant.SESSION_ADDUSER_CONFIRM + key);
				// nếu không nhập trình độ tiếng nhật
				if ("".equals(userInforEntity.getCodeLevel())) {
					userInforEntity.setEndDate(endDate);
					userInforEntity.setStartDate(startDate);
					userInforEntity.setTotal(total);
				}
			}

			// Trả về đối tượng userInforEntity
			return userInforEntity;
		} catch (SQLException | NullPointerException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserInputController.getDefaultValue " + e.getMessage());
			// throw lỗi
			throw e;
		}
	}
}
