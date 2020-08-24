/**
 * Copyright(C) 2020  Luvina Software
 * Common.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;

/**
 * Chứa các hàm common của dự án
 * 
 * @author Phan Van Hiep
 */
public class Common {

	/**
	 * Tạo ra chuỗi salt
	 * 
	 * @return trả về chuỗi salt vừa random ra
	 * @throws SQLException
	 */
	public static String generateSalt() {
		String saltFinal = "";
		String random = java.time.LocalDateTime.now().toString();
		byte[] salt = new byte[32];
		salt = random.getBytes();
		saltFinal = Base64.getEncoder().encodeToString(salt);
		return saltFinal;
	}

	/**
	 * Hàm mã hóa password
	 * 
	 * @param salt
	 *            password người dùng nhập vào
	 * @param salt
	 *            chuỗi salt là ngày giờ hiện tại
	 * @return chuỗi khi đã được mã hóa
	 * @throws NoSuchAlgorithmException
	 */
	public static String enscryptPassword(String pass, String salt) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			String saltPassword = salt + pass;
			byte[] messageDigest = md.digest(saltPassword.getBytes());
			return Base64.getEncoder().encodeToString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error : Common.enscryptPassword " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Kiểm tra 2 chuỗi có giống nhau không
	 * 
	 * @param str1
	 *            chuỗi cần so sánh
	 * @param str2
	 *            chuỗi cần so sánh
	 * @return boolean
	 */
	public static boolean compareString(String str1, String str2) {
		// Nếu chuỗi 1 bằng chuỗi 2 thì trả về true, không thì trả về false
		if (str1.equals(str2)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Kiểm tra xem đã đăng nhập thành công chưa
	 * 
	 * @param session
	 *            truyền vào session để kiểm tra
	 * @return boolean (Nếu đăng nhập rồi thì trả về true, chưa đăng nhập thì
	 *         trả về false)
	 * @throws Exception
	 */
	public static boolean checkLogin(HttpSession session) throws Exception {
		try {
			// Nếu giá trị session loginName != null nghĩa là đã có session
			if (session.getAttribute(Constant.SESSION_LOGINNAME) != null) {
				// Khởi tạp một đối tượng TblUserDaoImpl
				TblUserDao userDaoImpl = new TblUserDaoImpl();
				// Lấy giá trị từ session
				String loginName = (String) session.getAttribute(Constant.SESSION_LOGINNAME);
				// Nếu lấy được đối tượng UserDaoImpl từ hàm
				// getTblUserByLoginName nghĩa là
				// loginName này có tồn tại trong DB
				if (userDaoImpl.getTblUserByLoginId(loginName) != null) {
					return true;
				}
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error : Common.checkLogin " + e.getMessage());
			throw e;
		}
		return false;
	}

	/**
	 * replaceWildCard thay đổi giá trị wideCard nhập vào tránh lỗi sqlInjection
	 * 
	 * @param str
	 *            giá trị nhập vào
	 * @return str đã được được thay thế
	 */
	public static String replaceWildCard(String str) {
		if (!"".equals(str)) {
			str = str.replace("%", "\\%");
			str = str.replace("_", "\\_");
		}
		// trả về giá trị đã được thay đổi các kí tự đặc biệt
		return str;
	}

	/**
	 * Chuyển chuỗi về dạng số nguyên
	 * 
	 * @param input
	 *            là giá trị chuỗi cần chuyển
	 * @param defaultValue
	 *            giá trị mặc định mong muốn
	 * @return number trả về số nguyên
	 */
	public static int convertStringToInt(String input, int defaultValue) {
		// khai báo number
		int numberValue = 0;
		try {
			// chuyển đổi sang số nguyên
			numberValue = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// Nếu giá trị truyền vào không phải chuỗi, gán bằng giá trị mặc
			// định
			numberValue = defaultValue;
		}
		// trả về giá trị số đã được chuyển đổi
		return numberValue;
	}

	/**
	 * Lấy limit từ file config.properties
	 * 
	 * @return trả về số bản ghi tối đa hiển thị trên 1 page
	 */
	public static int getLimit() {
		// khai báo limit
		int limit = Constant.LIMIT_DEFAULT;
		// Lấy giá trị limit từ file config.properties
		String limitValue = ConfigProperties.getValueByKey(Constant.LIMIT);
		// Nếu limitValue rỗng hoặc null
		if (!"".equals(limitValue) && limitValue != null) {
			// CHuyển sang Interger
			limit = Integer.parseInt(limitValue);
		}
		return limit;
	}

	/**
	 * Lấy offset(Vị trí bắt đầu lấy data)
	 * 
	 * @param currentPage
	 *            trang hiện tại
	 * @param limit
	 *            số lượng bản ghi hiển thị trên 1 page
	 * @return trả về offset
	 */
	public static int getOffset(int currentPage, int limit) {
		// khai báo offset
		int offset = Constant.OFFSET_DEFAULT;
		// tính toán offset
		offset = (currentPage - 1) * limit;
		// trả về offset cần lấy
		return offset;
	}

	/**
	 * Tính tổng số trang
	 * 
	 * @param totalUser
	 *            tổng số user tìm được
	 * @param limit
	 *            số lượng bản ghi hiển thị trên 1 trang
	 * @return trả về tổng số trang
	 */
	public static int getTotalPage(int totalUser, int limit) {
		// khai báo tổng số trang
		int totalPage = 0;
		// Nếu tổng số User chia hết cho số bản ghi trên 1 page
		if (totalUser % limit == 0) {
			// totalPage bằng thương của totalUser và limit
			totalPage = totalUser / limit;
			// ngược lại nếu tổng số User không chia hết cho số bản ghi trên 1
			// page
		} else {
			// totalPage bằng thương của totalUser và limit cộng thêm 1
			totalPage = totalUser / limit + 1;
		}
		// trả về tổng số trang
		return totalPage;
	}

	/**
	 * Lấy ra danh sách chuỗi paging để hiển thị lên màn hình
	 * 
	 * @param totalUser
	 *            tổng số user tìm kiếm được
	 * @param currentPage
	 *            trang hiện tại
	 * @param limit
	 *            số lượng bản ghi hiển thị trên 1 trang
	 * @return listPaging trả về list paging hiển thị
	 */
	public static ArrayList<Integer> getListPaging(int totalUser, int currentPage, int limit) {
		// khai báo listPaging
		ArrayList<Integer> listPaging = new ArrayList<>();
		// Khai báo và lấy về tổng số page
		int totalPage = Common.getTotalPage(totalUser, limit);
		// Khai báo và lấy về số page trên 1 chuỗi paging
		int limitPage = Common.convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_PAGE),
				Constant.LIMITPAGE_DEFAULT);
		// Page bắt đầu chuỗi paging
		int startPage = ((currentPage - 1) / limitPage) * limitPage + 1;
		// Page kết thúc chuỗi paging
		int endPage = startPage + limitPage - 1;
		// Nếu page kết thúc chuỗi lớn hơn tổng số page thì page kết thúc bằng
		// page cuối
		// cùng
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// thêm trang vào listPageing
		for (int i = startPage; i <= endPage; i++) {
			listPaging.add(i);
		}
		// trả về list trang hiển thị
		return listPaging;
	}

	/**
	 * Lấy ra danh sách năm để gắn cho selectbox năm
	 * 
	 * @param startYear
	 *            năm bắt đầu
	 * @param endYear
	 *            năm kết thúc
	 * @return trả về danh sách năm
	 */
	public static List<Integer> getListYear(int startYear, int endYear) {
		// khởi tạo listYear
		List<Integer> listYear = new ArrayList<>();
		// thếm các giá trị vào listYear
		for (int i = startYear; i <= endYear; i++) {
			listYear.add(i);
		}
		// trả về danh sách các năm từ startYear đến endYear
		return listYear;
	}

	/**
	 * Lấy ra danh sách các tháng để gán cho selectbox tháng
	 * 
	 * @return trả về danh sách các tháng trong năm
	 */
	public static List<Integer> getListMonth() {
		// khởi tạo listMonth
		List<Integer> listMonth = new ArrayList<>();
		// thếm các giá trị vào listMonth
		for (int i = 1; i <= 12; i++) {
			listMonth.add(i);
		}
		// trả về danh sách các tháng
		return listMonth;
	}

	/**
	 * Lấy ra danh sách các ngày để gán cho selectbox ngày
	 * 
	 * @return trả về danh sách các ngày trong tháng
	 */
	public static ArrayList<Integer> getListDay() {
		// khởi tạo listMonth
		ArrayList<Integer> listDay = new ArrayList<>();
		// thếm các giá trị vào listDay
		for (int i = 1; i <= 31; i++) {
			listDay.add(i);
		}
		// trả về danh sách các ngày
		return listDay;
	}

	/**
	 * Sử dụng để có thể lấy về ngày tháng năm hiện tại
	 * 
	 * @return trả về một đối tượng calender
	 */
	public static Calendar getDate() {
		Calendar calender = Calendar.getInstance();
		return calender;
	}

	/**
	 * Sử dụng để chuyển các chuỗi về định dạng ngày YYYY/MM/DD
	 * 
	 * @param year
	 *            chuỗi năm
	 * @param month
	 *            chuỗi tháng
	 * @param day
	 *            chuỗi ngày
	 * @return chuỗi có đạnh dạng YYYY/MM/DD
	 */
	public static String setFormatDate(String year, String month, String day) {
		String result = year + "/" + month + "/" + day;
		return result;
	}

	/**
	 * Kiểm tra xem một chuỗi có rỗng không
	 * 
	 * @param str
	 *            chuỗi cần kiểm tra
	 * @return true nếu rỗng và ngược lại
	 */
	public static boolean checkEmpty(String str) {
		// Nếu rỗng thì trả về true
		if ("".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra độ dài của chuỗi có nằm trong khoảng từ minLength đến maxLength
	 * không
	 * 
	 * @param maxLength
	 *            độ dài lớn nhất
	 * @param minLength
	 *            độ dài nhỏ nhất
	 * @return true nếu độ dài chuỗi trong khoảng cho phép, ngược lại là false
	 */
	public static boolean checkLength(String str, int minLength, int maxLength) {
		if (minLength <= str.length() && str.length() <= maxLength) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra chuỗi truyền vào đã đúng format chưa
	 * 
	 * @param str
	 *            chuỗi cần kiểm tra
	 * @param format
	 *            format dùng để kiểm tra
	 * @return true nếu chuỗi đúng format, false nếu ngược lại
	 */
	public static boolean checkFormat(String str, String format) {
		if (str.matches(format)) {
			return true;
		}
		return false;
	}

	/**
	 * check kí tự Kana
	 * 
	 * @param text
	 *            chuỗi cần check
	 * @return true nếu là kí tự kana, false nếu ngược lại
	 */
	public static boolean isKatakana(String text) {
		char[] c = text.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if ((Character.UnicodeBlock.of(c[i]) != Character.UnicodeBlock.KATAKANA) && (!isDigit(c[i]))
					&& (!Character.isWhitespace(c[i]))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Kiểm tra digit một kí tự
	 * 
	 * @param c
	 *            kí tự cần kiểm tra
	 * @return true nếu là kí tự diGit
	 */
	private static boolean isDigit(char c) {
		int x = (int) c;
		if ((x >= 48) && (x <= 57)) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra ngày tháng năm hợp lệ
	 * 
	 * @param birthday
	 *            chuỗi cần kiểm tra
	 * @return true nếu ngày tháng năm hợp lệ, false nếu
	 */
	public static boolean checkDate(String birthday) {
		// Lấy ra ngày, tháng, năm từ chuỗi truyền vào
		String[] lstDate = birthday.trim().split("/");
		int year = Common.convertStringToInt(lstDate[0], 0);
		int month = Common.convertStringToInt(lstDate[1], 0);
		int day = Common.convertStringToInt(lstDate[2], 0);
		int yearNow = Common.getDate().get(Calendar.YEAR);
		boolean check = true;
		// Kiểm tra ngày tháng
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			if (day < 0 || day > 30) {
				check = false;
			}
			break;
		case 2:
			if (year % 4 == 0) {
				if (day < 0 || day > 29) {
					check = false;
				}
			} else {
				if (day < 0 || day > 28) {
					check = false;
				}
			}

			break;
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day < 0 || day > 31) {
				check = false;
			}
			break;

		default:
			check = false;
			break;
		}
		return check;
	}

	/**
	 * Check kí tự hallfsize
	 * 
	 * @param text
	 *            chuỗi cần kiểm tra
	 * @return true nếu là hallfsize, false nếu ngược lại
	 */
	public static boolean isHalfsize(String text) {
		return Pattern.matches("[a-zA-Z0-9]+", text);
	}
	
	/**
	 * Check số hallfsize
	 * 
	 * @param text
	 *            chuỗi cần kiểm tra
	 * @return true nếu là hallfsize, false nếu ngược lại
	 */
	public static boolean isHalfsizeNumber(String text) {
		return Pattern.matches("[0-9]+", text);
	}

	/**
	 * Check ngày hết hạn lớn hơn ngày bắt đàu
	 * 
	 * @param startDate
	 *            ngày bắt đầu
	 * @param endDate
	 *            ngày hết hạn
	 * @return true nếu là ngày hết hạn lướn hơn
	 */
	public static boolean checkER012(String startDate, String endDate) {
		boolean checkER012 = false;
		// Lấy về ngày tháng năm bắt đầu
		String[] lstStartDate = startDate.trim().split("/");
		int startYear = Integer.parseInt(lstStartDate[0]);
		int startMonth = Integer.parseInt(lstStartDate[1]);
		int startDay = Integer.parseInt(lstStartDate[1]);
		// Lấy về ngày tháng năm kết thúc
		String[] lstEndDate = endDate.trim().split("/");
		int endYear = Integer.parseInt(lstEndDate[0]);
		int endMonth = Integer.parseInt(lstEndDate[1]);
		int endDay = Integer.parseInt(lstEndDate[1]);
		if (endYear > startYear) {
			checkER012 = true;
		} else if (endYear == startYear && endMonth > startMonth) {
			checkER012 = true;
		} else if (endYear == startYear && endMonth == startMonth && endDay > startDay) {
			checkER012 = true;
		}
		return checkER012;
	}

	/**
	 * lấy về đối tượng TblUserEntity từ đối tượng UserInfoEntity
	 * 
	 * @param userInfoEntity
	 *            dùng để lấy ra các giá trị của thuộc tính để gán vào
	 *            TblUserEntity
	 * @return đối tượng TblUserEntity đã lấy được
	 * @throws NoSuchAlgorithmException
	 */
	public static TblUserEntity getTblUserEntityFromTblUserInfor(UserInfoEntity userInfoEntity)
			throws NoSuchAlgorithmException {
		// khởi tạo tblUser
		TblUserEntity tblUserEntity = new TblUserEntity();
		try {
			// set các giá trị cho thuộc tính của tblUserEntity
			tblUserEntity.setGroupId(userInfoEntity.getGroupId());
			tblUserEntity.setFullName(userInfoEntity.getFullName());
			tblUserEntity.setFullNameKana(userInfoEntity.getFullNameKatana());
			tblUserEntity.setBirthday(userInfoEntity.getBirthday());
			tblUserEntity.setEmail(userInfoEntity.getEmail());
			tblUserEntity.setTel(userInfoEntity.getTel());
			tblUserEntity.setUserId(userInfoEntity.getUserId());
			// Nếu là trường hợp add
			if (tblUserEntity.getUserId() == 0) {
				tblUserEntity.setLoginName(userInfoEntity.getLoginName());
				// lấy ra pass
				String pass = userInfoEntity.getPassword();
				// tạo salt
				String salt = generateSalt();
				// mã hóa past word
				pass = enscryptPassword(pass, salt);
				tblUserEntity.setPass(pass);
				tblUserEntity.setSalt(salt);
				tblUserEntity.setRule(Constant.RULE_USER);
			}
			return tblUserEntity;
		} catch (NoSuchAlgorithmException e) {
			// thông báo lỗi
			System.out.println("Error : Common.getTblUserEntityFromTblUserInfor " + e.getMessage());
			// gửi lỗi
			throw e;
		}
	}

	/**
	 * lấy về đối tượng TblDetailUserJapanEntity từ đối tượng userInfoEntity
	 * 
	 * @param userInfoEntity
	 *            dùng để lấy ra các giá trị của thuộc tính để gán vào
	 *            TblDetailUserJapanEntity
	 * @return đối tượng TblDetailUserJapanEntity đã lấy được
	 */
	public static TblDetailUserJapanEntity getTblDetailUserJapanEntity(UserInfoEntity userInfoEntity) {
		// khởi tạo tblDetailUserJapanEntity
		TblDetailUserJapanEntity tblDetailUserJapanEntity = new TblDetailUserJapanEntity();
		// set các giá trị cho các thuộc tính của tblDetailUserJapanEntity
		tblDetailUserJapanEntity.setUserId(userInfoEntity.getUserId());
		tblDetailUserJapanEntity.setCodeLevel(userInfoEntity.getCodeLevel());
		tblDetailUserJapanEntity.setEndDate(userInfoEntity.getEndDate());
		tblDetailUserJapanEntity.setStartDate(userInfoEntity.getStartDate());
		tblDetailUserJapanEntity.setTotal(userInfoEntity.getTotal());
		// trả về đối tượng tblDetailUserJapanEntity đã được gán giá trị cho các
		// thuộc tính
		return tblDetailUserJapanEntity;
	}

	/**
	 * Set các giá trị cho các selectbox ở màn hình ADM003
	 * 
	 * @param request
	 */
	public static void setDataLogic(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		// Khởi tạo listGroup để lưu tất cả các group lấy được
		ArrayList<MstGroupEntity> listMstGroup = new ArrayList<>();
		// Khởi tạo listMstJapan để lưu tất cả các group lấy được
		List<MstJapanEntity> listMstJapan = new ArrayList<>();
		// khởi tạo listYear
		List<Integer> listYear = new ArrayList<>();
		// khởi tạo listMonth
		List<Integer> listMonth = new ArrayList<>();
		// khởi tạo listMonth
		ArrayList<Integer> listDay = new ArrayList<>();

		// Khởi tạo đối tượng mstGroupLogicImpl
		MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
		// Khởi tạo đối tượng mstJapanLogicImpl
		MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();

		try {
			// lấy về giá trị listMstGroup
			listMstGroup = mstGroupLogicImpl.getAllMstGroup();
			// Lấy về giá trị listMstJapan
			listMstJapan = mstJapanLogicImpl.getAllMstJapan();
			// Lấy về listYear
			listYear = Common.getListYear(1990, Common.getDate().get(Calendar.YEAR));
			// Lấy về listMonth
			listMonth = Common.getListMonth();
			// Lấy về listDay
			listDay = Common.getListDay();

			// gán lên request
			req.setAttribute(Constant.REQUEST_LISTMSTGROUP, listMstGroup);
			req.setAttribute(Constant.REQUEST_LISTMSTJAPAN, listMstJapan);
			req.setAttribute(Constant.REQUEST_LISTYEAR, listYear);
			req.setAttribute(Constant.REQUEST_LISTMONTH, listMonth);
			req.setAttribute(Constant.REQUEST_LISTDAY, listDay);
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserInputController.setDataLogic " + e.getMessage());
			// throw lỗi
			throw e;
		}
	}
}
