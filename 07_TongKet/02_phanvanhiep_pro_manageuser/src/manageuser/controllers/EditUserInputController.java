/**
 * Copyright(C) 2020  Luvina Software
 * EditUserInputController.java, Aug 10, 2020 Phan Văn Hiệp
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
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller để xử lý cho màn hình ADM003 truong hợp Edit
 * 
 * @author Phan Van Hiep
 */
public class EditUserInputController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lý khi click vào button Edit của ADM005
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session từ request
			HttpSession session = req.getSession();
			// Khởi tạo đối tượng TblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();

			// Lấy id từ request
			int userId = Common.convertStringToInt(req.getParameter(Constant.REQUEST_ID), Constant.USER_ID_DEFAULT);
			// Kiểm tra tồn tại user có id là userId
			if (tblUserLogic.checkExistUserById(userId)) {
				// gọi đến hàm setDataLogic để truyền các giá trị của
				// pulldown
				// lên req
				Common.setDataLogic(req);
				// lấy userInfoEntity
				UserInfoEntity userInfoEntity = getDefaultValue(req);
				// gán userInfoEntity lên req
				req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
				// forward đến trang ADM003
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
			} else {
				// Thông báo lỗi không tồn tại Id
				resp.sendRedirect(Constant.URL_SYSTEMERROR + Constant.URL_ERROR_NOTEXIST_ID);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : EditUserInputController.doGet " + e.getMessage());
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
			// Khởi tạo session từ request
			HttpSession session = req.getSession();
			// Khởi tạo listError để lấy về danh sách lỗi từ hàm
			// valadateUserInfor
			List<String> listError = new ArrayList<>();
			// Khởi tạo đối tượng UserInfoEntity để lấy về giá trị từ hàm
			// getDefaultValue
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			// Khởi tạo đối tượng TblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();

			// Lấy id từ request
			int userId = Common.convertStringToInt(req.getParameter(Constant.REQUEST_ID), Constant.USER_ID_DEFAULT);
			// kiểm tra tồn tại user
			if (tblUserLogic.checkExistUserById(userId)) {
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
					session.setAttribute(Constant.SESSION_EDITUSER_CONFIRM + key, userInfoEntity);
					// gán lên session một giá trị confirmADM003 để kiểm tra
					// có từ màn ADM003 qua hay ko
					session.setAttribute(Constant.SESSION_CONFIRM_ADM003, Constant.SESSION_CONFIRM_ADM003);
					// sendRedirect đến URL editUserConfirm
					resp.sendRedirect(Constant.URL_EDITUSERCONFIRM + key);
				} else {
					Common.setDataLogic(req);
					req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
					req.setAttribute(Constant.REQUEST_LISTERROR, listError);
					req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
				}
			} else {
				// Thông báo lỗi không tồn tại Id
				resp.sendRedirect(Constant.URL_SYSTEMERROR + Constant.URL_ERROR_NOTEXIST_ID);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : EditserInputController.doPost " + e.getMessage());
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
			// Khởi tạo đối tượng TblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Khởi tạo đối tượng UserInfoEntity
			UserInfoEntity userInfoEntity = new UserInfoEntity();
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
			// Lấy id từ request
			int userId = Common.convertStringToInt(req.getParameter(Constant.REQUEST_ID), Constant.USER_ID_DEFAULT);
			if (type == null) {
				// Lấy về giá trị cho đối tượng userInfoEntity
				userInfoEntity = tblUserLogic.getUserInfoByUserId(userId);
				// nếu không có trình độ tiếng nhật
				if (userInfoEntity.getCodeLevel() == null) {
					// set ngày bắt đầu, ngày hết hạn, điểm với các giá trị
					// default
					userInfoEntity.setStartDate(startDate);
					userInfoEntity.setEndDate(endDate);
					userInfoEntity.setTotal(total);
				}
			} else if (Common.compareString(Constant.TYPE_VALIDATE, type)) {
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

				// gán giá trị các thuộc tính đối tượng userInforEntity
				userInfoEntity.setUserId(userId);
				userInfoEntity.setLoginName(loginName);
				userInfoEntity.setGroupId(groupId);
				userInfoEntity.setGroupName(groupName);
				userInfoEntity.setFullName(fullName);
				userInfoEntity.setFullNameKatana(fullNameKatana);
				userInfoEntity.setBirthday(birthday);
				userInfoEntity.setEmail(email);
				userInfoEntity.setTel(tel);
				userInfoEntity.setNameLevel(nameLevel);
				userInfoEntity.setCodeLevel(codeLevel);
				userInfoEntity.setEndDate(endDate);
				userInfoEntity.setStartDate(startDate);
				userInfoEntity.setTotal(total);
			} else if (Common.compareString(Constant.TYPE_BACK, type)) {
				// khai báo session
				HttpSession session = req.getSession();
				// Lấy key từ req
				String key = req.getParameter("key");
				// lấy userInforEntity từ session
				userInfoEntity = (UserInfoEntity) session.getAttribute(Constant.SESSION_EDITUSER_CONFIRM + key);
				// xóa session
				session.removeAttribute(Constant.SESSION_EDITUSER_CONFIRM + key);
				// nếu không nhập trình độ tiếng nhật
				if ("".equals(userInfoEntity.getCodeLevel())) {
					userInfoEntity.setEndDate(endDate);
					userInfoEntity.setStartDate(startDate);
					userInfoEntity.setTotal(total);
				}
			}
			// Trả về đối tượng userInforEntity
			return userInfoEntity;
		} catch (SQLException | NullPointerException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : EditUserInputController.getDefaultValue " + e.getMessage());
			// throw lỗi
			throw e;
		}
	}
}
