/**
 * Copyright(C) 2020  Luvina Software
 * ValidateUser.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.validates;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Xử lý validate thông tin nhập vào từ màn hình
 * 
 * @author Phan Van Hiep
 */
public class ValidateUser {

	/**
	 * hàm validate các thông tin nhập từ màn hình login
	 * 
	 * @param loginName login_name nhập từ màn hình
	 * @param pass      pass nhập từ màn hình
	 * @return list lỗi
	 */
	public static List<String> validateLogin(String loginName, String pass)
			throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		List<String> lstError = new ArrayList<String>();
		TblUserLogicImpl userLogic = new TblUserLogicImpl();
		try {
			if (Common.compareString(Constant.DEFAULT_EMPTY, loginName)) {
				lstError.add(MessageProperties.getValueByKey((Constant.ER001_USERNAME)));
			}
			if (Common.compareString(Constant.DEFAULT_EMPTY, pass)) {
				lstError.add(MessageProperties.getValueByKey((Constant.ER001_PASS)));
			}
			if (lstError.size() == 0) {
				if (!userLogic.checkExistLogin(loginName, pass)) {
					lstError.add(MessageProperties.getValueByKey((Constant.ER016)));
				}
			}

		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateLogin " + e.getMessage());
			throw e;
		}
		return lstError;
	}

	/**
	 * hàm validate các thông tin nhập từ màn hình ADM003
	 * 
	 * @param userInfor đối tượng userInfor để validate các thuộc tính
	 * @return list lỗi
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<String> validateUserInfor(UserInfoEntity userInforEntity)
			throws ClassNotFoundException, SQLException {
		List<String> listError = new ArrayList<>();
		try {
			// validate loginName
			if (userInforEntity.getUserId() == 0) {
				String errorLoginName = validateLoginName(userInforEntity.getLoginName());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorLoginName)) {
					// thêm lỗi vào trong danh sách lỗi
					listError.add(errorLoginName);
				}
			}

			// validate selecbox group
			String errorGroup = validateGroup(userInforEntity.getGroupId());
			if (!Common.compareString(Constant.DEFAULT_EMPTY, errorGroup)) {
				listError.add(errorGroup);
			}

			// validate fullName
			String errorFullName = validateFullName(userInforEntity.getFullName());
			if (!Common.compareString(Constant.DEFAULT_EMPTY, errorFullName)) {
				listError.add(errorFullName);
			}

			// Validate fullnameKana
			// nếu có nhập fullNameKatana
			if (!Common.compareString(Constant.DEFAULT_EMPTY, userInforEntity.getFullNameKatana())) {
				String errorFullNameKatana = validateFullNameKatana(userInforEntity.getFullNameKatana());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorFullNameKatana)) {
					listError.add(errorFullNameKatana);
				}
			}

			// Validate birthday
			String errorBirthday = validateBirthday(userInforEntity.getBirthday());
			if (!Common.compareString(Constant.DEFAULT_EMPTY, errorBirthday)) {
				listError.add(errorBirthday);
			}

			// Validate email
			String errorEmail = validateEmail(userInforEntity.getEmail(), userInforEntity.getUserId());
			if (!Common.compareString(Constant.DEFAULT_EMPTY, errorEmail)) {
				listError.add(errorEmail);
			}

			// Validate Tel
			String errorTel = validateTel(userInforEntity.getTel());
			if (!Common.compareString(Constant.DEFAULT_EMPTY, errorTel)) {
				listError.add(errorTel);
			}

			if (userInforEntity.getUserId() == 0) {
				// Validate Password
				String errorPassword = validatePassword(userInforEntity.getPassword());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorPassword)) {
					listError.add(errorPassword);
				}

				// Validate Password Confirm
				// Nếu password không có lỗi
				if (Common.compareString(Constant.DEFAULT_EMPTY, errorPassword)) {
					String errorPassConfirm = validatePassConfirm(userInforEntity.getPassword(),
							userInforEntity.getPasswordConfirm());
					if (!Common.compareString(Constant.DEFAULT_EMPTY, errorPassConfirm)) {
						listError.add(errorPassConfirm);
					}
				}
			}

			// Nếu có chọn codeLevel
			if (!Common.compareString(Constant.DEFAULT_EMPTY, userInforEntity.getCodeLevel())) {
				// Validate codeLevel
				String errorCodeLevel = validateCodeLevel(userInforEntity.getCodeLevel());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorCodeLevel)) {
					listError.add(errorCodeLevel);
				}

				// Validate startDate
				String errorStartDate = validateStartDate(userInforEntity.getStartDate());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorStartDate)) {
					listError.add(errorStartDate);
				}

				// Validate endDate
				String errorEndDate = validateEndDate(userInforEntity.getStartDate(), userInforEntity.getEndDate());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorEndDate)) {
					listError.add(errorEndDate);
				}

				// Validate Total
				String errorTotal = validateTotal(userInforEntity.getTotal());
				if (!Common.compareString(Constant.DEFAULT_EMPTY, errorTotal)) {
					listError.add(errorTotal);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateUserInfor " + e.getMessage());
			throw e;
		}
		return listError;
	}

	/**
	 * Validate hạng mục total
	 * 
	 * @param total giá trị cần kiểm tra
	 * @return lỗi nếu có
	 */
	private static String validateTotal(String total) {
		// khai báo biến chữ lỗi
		String errTotal = Constant.DEFAULT_EMPTY;
		// check chưa nhập(Mặc định total bằng 0 nên phải check 0)
		if (Common.checkEmpty(total)) {
			errTotal = Constant.ER001_TOTAL;
			// check hallfsize
		} else if (!Common.isHalfsizeNumber(total)) {
			errTotal = Constant.ER018_TOTAL;
			// check max length
		} else if (!Common.checkLength(total, 0, Constant.MAX_LENGTH_TOTAL)) {
			errTotal = Constant.ER006_TOTAL;
		}
		return errTotal;
	}

	/**
	 * Validate hạng mục enđate
	 * 
	 * @param endDate giá trị cần kiếm tra
	 * @return lỗi nếu có
	 */
	private static String validateEndDate(String startDate, String endDate) {
		// Khởi tạo biến chứa lỗi
		String errEndDate = Constant.DEFAULT_EMPTY;
		// Kiểm tra ngày hợp lệ
		if (!Common.checkDate(endDate)) {
			errEndDate = Constant.ER011_ENDDATE;
			// Kiểm tra ngày hết hạn lớn hơn
		} else if (!Common.checkER012(startDate, endDate)) {
			errEndDate = Constant.ER012_ENDDATE;
		}
		return errEndDate;
	}

	/**
	 * Validate hạng mục startDate
	 * 
	 * @param startDate giá trị cần kiểm tra
	 * @return trả về lỗi nếu có
	 */
	private static String validateStartDate(String startDate) {
		// Khởi tạo biến chứa lỗi
		String errStartDate = Constant.DEFAULT_EMPTY;
		// Kiểm tra ngày hợp lệ
		if (!Common.checkDate(startDate)) {
			errStartDate = Constant.ER011_STARTDATE;
		}
		return errStartDate;
	}

	/**
	 * Validate pulldown codeLevel
	 * 
	 * @param codeLevel giá trị cần kiểm tra
	 * @return lỗi nếu có
	 * @throws SQLException, ClassNotFoundException
	 */
	private static String validateCodeLevel(String codeLevel) throws SQLException, ClassNotFoundException {
		// Khởi tạo biến chứa lỗi
		String errCodeLevel = Constant.DEFAULT_EMPTY;
		// Khởi tạo đối tượng mstJapanLogicImpl
		MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();
		try {
			if (!mstJapanLogicImpl.checkExistCodeLevel(codeLevel)) {
				errCodeLevel = Constant.ER004_CODELEVEL;
			}
			return errCodeLevel;
		} catch (SQLException | ClassNotFoundException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateCodeLevel " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Validate passConfirm
	 * 
	 * @param pass            dùng để check với passConfirm
	 * @param passwordConfirm chuỗi cần kiểm tra
	 * @return lỗi nếu có
	 */
	private static String validatePassConfirm(String password, String passwordConfirm) {
		// Khởi tạo biến chứa lỗi
		String errPassConfirm = Constant.DEFAULT_EMPTY;
		// Kiểm tra passwordConfirm có bằng password không?
		if (!Common.compareString(password, passwordConfirm)) {
			errPassConfirm = Constant.ER017_PASSWORD_CONFIRM;
		}
		return errPassConfirm;
	}

	/**
	 * Validate hạng mục password
	 * 
	 * @param password chuỗi cần validate
	 * @return lỗi nếu có
	 */
	private static String validatePassword(String password) {
		// Khởi tạo biến chứa lỗi
		String errPassword = "";
		// check không nhập
		if (Common.checkEmpty(password)) {
			errPassword = Constant.ER001_PASSWORD;
			// check kí tự 1 byte
		} else if (!Common.isHalfsize(password)) {
			errPassword = Constant.ER008_PASSWORD;
			// check độ dài khoảng
		} else if (!Common.checkLength(password, Constant.MIN_LENGTH_PASS, Constant.MAX_LENGTH_PASS)) {
			errPassword = Constant.ER007_PASSWORD;
		}
		return errPassword;
	}

	/**
	 * validate hạng mục tel
	 * 
	 * @param tel chuỗi cần validate
	 * @return lỗi nếu có lỗi
	 */
	private static String validateTel(String tel) {
		// Khởi tạo biến chứa lỗi
		String errTel = "";
		// kiểm tra chưa nhập
		if (Common.checkEmpty(tel)) {
			errTel = Constant.ER001_TEL;
			// kiểm tra max kí tự
		} else if (!Common.checkLength(tel, 0, Constant.MAX_LENGTH_TEL)) {
			errTel = Constant.ER006_TEL;
			// kiểm tra forrmart
		} else if (!Common.checkFormat(tel, Constant.FORMAT_TEL)) {
			errTel = Constant.ER005_TEL;
		}
		return errTel;
	}

	/**
	 * validate hạng mục email
	 * 
	 * @param userId để bổ xung điều kiện check trong trường hợp edit
	 * @param email  email cần kiểm tra
	 * @return lỗi nếu có
	 * @throws ClassNotFoundException, SQLException
	 */
	private static String validateEmail(String email, int userId) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến chứa lỗi
		String errEmail = Constant.DEFAULT_EMPTY;
		// Khởi tạo đối tượng tblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		try {
			// Kiểm tra không nhập
			if (Common.checkEmpty(email)) {
				errEmail = Constant.ER001_MAIL;
				// kiểm tra max kí tự
			} else if (!Common.checkLength(email, 0, Constant.MAX_LENGTH_EMAIL)) {
				errEmail = Constant.ER006_MAIL;
				// kiểm tra forrmat
			} else if (!Common.checkFormat(email, Constant.FORMAT_EMAIL)) {
				errEmail = Constant.ER005_MAIL;
				// kiểm tra tồn tại
			} else if (tblUserLogic.checkExistedEmail(email, userId)) {
				errEmail = Constant.ER003_MAIL;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateEmail " + e.getMessage());
			throw e;
		}
		return errEmail;
	}

	/**
	 * validate hạng mục birthday
	 * 
	 * @param birthday chuỗi cần kiểm tra
	 * @return lỗi nếu có
	 */
	private static String validateBirthday(String birthday) {
		// Khởi tạo biến chứa lỗi
		String errBirthday = Constant.DEFAULT_EMPTY;
		// Kiểm tra ngày tháng năm hợp lệ
		if (!Common.checkDate(birthday)) {
			errBirthday = Constant.ER011_BIRTHDAY;
		}
		return errBirthday;
	}

	/**
	 * validate selectbox group
	 * 
	 * @param groupId groupId cần kiểm tra
	 * @return lỗi nếu có
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static String validateGroup(int groupId) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến chứa lỗi
		String errGroup = Constant.DEFAULT_EMPTY;
		// Khởi tạo đối tượng mstGroupLogicImpl
		MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
		try {
			// Kiểm tra chưa chọn
			if (Constant.GROUPID_DEFAULT == groupId) {
				errGroup = Constant.ER002_GROUPID;
				// Kiểm tra tồn tại groupId
			} else if (!mstGroupLogicImpl.checkExistGroup(groupId)) {
				errGroup = Constant.ER004_GROUPID;
			}
			return errGroup;
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateGroup " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Validate trường Fullname Kana
	 * 
	 * @param fullNameKatana tên kana cần validate
	 * @return lỗi nếu có
	 */
	private static String validateFullNameKatana(String fullNameKatana) {
		// Khởi tạo biến chứa lỗi
		String errFullNameKatana = Constant.DEFAULT_EMPTY;
		// Kiểm tra kí tư Kana
		if (!Common.isKatakana(fullNameKatana)) {
			errFullNameKatana = Constant.ER009_FULLNAMEKANA;
			// Kiểm tra max kí tự
		} else if (!Common.checkLength(fullNameKatana, 0, Constant.MAX_LENGTH_FULLNAMEKATANA)) {
			errFullNameKatana = Constant.ER006_FULLNAMEKANA;
		}
		return errFullNameKatana;
	}

	/**
	 * Validate trường fullName
	 * 
	 * @param fullName giá trị cần validate
	 * @return lỗi
	 */
	private static String validateFullName(String fullName) {
		// Khởi tạo biến chữa lỗi
		String errFullName = Constant.DEFAULT_EMPTY;
		// Kiểm tra rỗng
		if (Common.checkEmpty(fullName)) {
			errFullName = Constant.ER001_FULLNAME;
			// Kiểm tra max kí tự
		} else if (!Common.checkLength(fullName, 0, Constant.MAX_LENGTH_FULLNAME)) {
			errFullName = Constant.ER006_FULLNAME;
		}
		return errFullName;
	}

	/**
	 * validate giá trị của trường loginName
	 * 
	 * @param loginName giá trị cần validate
	 * @return lỗi nếu có
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static String validateLoginName(String loginName) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến chứa lỗi
		String errLoginName = Constant.DEFAULT_EMPTY;
		// Khởi tạo đối tượng tblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// Nếu rỗng thì gán mã lỗi ER001
		try {
			if (Common.checkEmpty(loginName)) {
				errLoginName = Constant.ER001_LOGINNAME;
				// Kiểm tra format
			} else if (!Common.checkFormat(loginName, Constant.FORMAT_LOGINNAME)) {
				errLoginName = Constant.ER0019_LOGINNAME;
				// Kiểm tra độ dài khoảng
			} else if (!Common.checkLength(loginName, Constant.MIN_LENGTH_LOGINNAME, Constant.MAX_LENGTH_LOGINNAME)) {
				errLoginName = Constant.ER007_LOGINNAME;
				// Check tồn tại trong DB
			} else if (tblUserLogic.checkExistedLoginName(loginName)) {
				errLoginName = Constant.ER003_LOGINNAME;
			}
			// trả về lỗi
			return errLoginName;
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateLoginName " + e.getMessage());
			throw e;
		}
	}

}
