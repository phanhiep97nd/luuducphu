/**
 * Copyright(C) 2020  Luvina Software
 * TblUserLogicImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.logics.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Implement UserLogic để Xử lý logic cho các chức năng liên quan đến tbl_user
 * 
 * @author Phan Van Hiep
 */
public class TblUserLogicImpl implements TblUserLogic {
	/**
	 * Kiểm tra xem loginName và pass người dùng nhập có tồn tại trong DB hay
	 * khoong
	 * 
	 * @param loginName
	 *            login_name người dùng nhập
	 * @param pass
	 *            password người dùng nhập
	 * @return trả về kết quả là giá trị boolean có tồn tại trong DB không
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public Boolean checkExistLogin(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		try {
			// Khởi tạo một TblUserDao
			TblUserDao userDaoImpl = new TblUserDaoImpl();
			// Khái báo một đối tượng TblUserEntity và gán bằng giá trị lấy về
			// từ hàm
			// getTblUserByLoginName
			TblUserEntity user = userDaoImpl.getTblUserByLoginId(loginName);
			// Khởi tạo một String lấy giá trị trả về từ hàm enscryptPassword
			String passEnscrypt = Common.enscryptPassword(pass, user.getSalt());
			// Khởi tạo một biến boolean lấy giá trị trả về từ hàm ompareString
			boolean check = Common.compareString(passEnscrypt, user.getPass());
			// Trả về biến check
			return check;
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			System.out.println("Error : TblUserLogicImpl.checkExistLogin " + e.getMessage());
			throw e;
		}

	}

	/**
	 * lấy các thông tin chi tiết của user từ bảng tbl_user, mst_group,
	 * mst_japan, tbl_detail_user_japan
	 * 
	 * @param offset
	 *            vị trí bắt đầu lấy
	 * @param limit
	 *            số bản ghi tối đa trên 1 page
	 * @param groupId
	 *            là id của nhóm được chọn trong pulldown
	 * @param fullName
	 *            là fullname tìm kiếm nhập vào từ textbox
	 * @param sortType
	 *            là loại sắp xếp theo fullName, codeLevel hay endDate
	 * @param sortByFullName
	 *            giá trị sắp xếp (ASC/DESC) cột fullName
	 * @param sortByCodeLevel
	 *            giá trị sắp xếp (ASC/DESC) cột codelevel
	 * @param sortByEndDate
	 *            giá trị sắp xếp (ASC/DESC) cột endDate
	 * @return trả về 1 list danh sách các UserInfo
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<UserInfoEntity> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng userDaoImpl
		TblUserDao userDaoImpl = new TblUserDaoImpl();
		// khai báo listUserInfo là danh sách các userinfo
		ArrayList<UserInfoEntity> listUserInfo = new ArrayList<UserInfoEntity>();
		try {
			// gán bằng giá trị trả về của hàm getListUser
			listUserInfo = userDaoImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserLogicImpl.getListUser " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return listUserInfo;
	}

	/**
	 * Đếm tổng số User tìm được
	 * 
	 * @param groupId
	 *            là nhóm được chọn trong selectbox
	 * @param fullName
	 *            là tên tìm kiếm được nhập từ textbox
	 * @return trả về số bản ghi có trong bảng thỏa mãn điều kiện tìm kiếm
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		// khai báo số bản khi
		int countUser = 0;
		// khởi tạo đối tượng TblUserDaoImpl
		TblUserDao tblUserImpl = new TblUserDaoImpl();
		try {
			// Gọi đến hàm getTotalUsers ở lớp TblUserDaoImpl
			countUser = tblUserImpl.getTotalUsers(groupId, fullName);
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error : TblUserLogicImpl.getTotalUsers " + e.getMessage());
			// Throw lỗi
			throw e;
		}

		return countUser;
	}

	/**
	 * Kiểm tra xem đã tồn tại email chưa
	 * 
	 * @param email
	 *            email cần kiểm tra
	 * @param userId
	 *            để check trong trường hợp edit
	 * @return trả về true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Boolean checkExistedLoginName(String loginName) throws ClassNotFoundException, SQLException {
		// Khởi tạo một TblUserDao
		TblUserDao userDaoImpl = new TblUserDaoImpl();
		try {
			// Khái báo một đối tượng TblUserEntity và gán bằng giá trị lấy về
			// từ hàm
			// getTblUserByLoginName
			TblUserEntity user = userDaoImpl.getUserByLoginName(loginName);
			// Mếu loginNae rỗng nghĩa là chưa tồn tại
			if (user.getLoginName() != null) {
				return true;
			} 
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error : TblUserLogicImpl.checkExistedLoginName " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return false;
	}

	/**
	 * Kiểm tra xem đã tồn tại email chưa
	 * 
	 * @param email
	 *            email cần kiểm tra
	 * @param userId
	 *            để check trong trường hợp edit
	 * @return trả về true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Boolean checkExistedEmail(String email, int userId) throws ClassNotFoundException, SQLException {
		// Khởi tạo một TblUserDao
		TblUserDao userDaoImpl = new TblUserDaoImpl();
		try {
			// Khái báo một đối tượng TblUserEntity và gán bằng giá trị lấy về
			// từ hàm
			// getTblUserByEmail
			TblUserEntity user = userDaoImpl.getUserByEmail(email, userId);
			// Nếu email rỗng nghĩa là chưa tồn tại
			if (user.getLoginName() != null) {
				return true;
			} 
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error : TblUserLogicImpl.checkExistedEmail " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return false;
	}

	/**
	 * Insert data user vào bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfoEntity
	 *            truyền vào đối tượng userInfoEntity cần insert
	 * @return true nếu insert thành công, false nếu insert không thành công
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */
	@Override
	public boolean createUser(UserInfoEntity userInfoEntity)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		// Khởi tạo đối tượng tblUserDaoImpl
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		try {
			// mở kết nối database
			tblUserDaoImpl.openConnection();
			// Lấy về Connection
			Connection conn = tblUserDaoImpl.getConnection();
			if (conn != null) {
				// dữ liệu chỉ được thêm khi gọi lệnh commit
				tblUserDaoImpl.setDisableCommit(false);
				// lấy ra tblUserEntity
				TblUserEntity tblUserEntity = Common.getTblUserEntityFromTblUserInfor(userInfoEntity);
				// insert vào bảng tbl_user và lấy về userId
				int userId = tblUserDaoImpl.insertUser(tblUserEntity);
				userInfoEntity.setUserId(userId);
				// nếu lấy về được userId nghĩa là thêm thành công vào tbl_user
				if (userId != 0) {
					// nếu có nhập trình độ tiếng nhật
					if (!Common.checkEmpty(userInfoEntity.getCodeLevel())) {
						// lấy ra tblDetailUserJapanEntity
						TblDetailUserJapanEntity tblDetailUserJapanEntity = Common
								.getTblDetailUserJapanEntity(userInfoEntity);

						// khởi tạo tblDetailUserJapanDaoImpl
						TblDetailUserJapanDao tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
						// set connection
						tblDetailUserJapanDaoImpl.setConn(conn);
						// insert vào bảng tbl_detail_user_japan
						tblDetailUserJapanDaoImpl.insertTblDetailUserJapan(tblDetailUserJapanEntity);
					}
					// thêm vào cơ sở dữ liệu
					tblUserDaoImpl.commitData();
					// trả về thêm thành công
					return true;
				}
			}
			return false;
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			// lấy lại dữ liệu ban đầu
			tblUserDaoImpl.rollBack();
			// thông báo lỗi
			System.out.println("Error : TblUserLogicImpl.createUser " + e.getMessage());
			// gửi lỗi
			throw e;
		} finally {
			// đóng cơ sở dữ liệu
			tblUserDaoImpl.closeConnection();
		}
	}

	/**
	 * Lấy ra đối tượng userInfo từ userId
	 * 
	 * @param userId
	 *            để lấy ra đối tượng userInfo trong DB từ userId
	 * @return đối tượng UserInfoEntity lấy được
	 */
	@Override
	public UserInfoEntity getUserInfoByUserId(int userId) throws SQLException, ClassNotFoundException {
		// Khai báo đối tượng UserInfoEntity
		UserInfoEntity userInfoEntity = null;
		// Khởi tạo đối tượng TblUserDaoImpl
		TblUserDao tblUserDao = new TblUserDaoImpl();
		try {
			// Gán giá trị cho userInfoEntity bằng cách gọi đến hàm
			// getUserInfoByUserId
			userInfoEntity = tblUserDao.getUserInfoByUserId(userId);
			// trả về
			return userInfoEntity;
		} catch (SQLException | ClassNotFoundException e) {
			// thông báo lỗi
			System.out.println("Error : TblUserLogicImpl.getUserInfoByUserId " + e.getMessage());
			// gửi lỗi
			throw e;
		}
	}

	/**
	 * Kiểm tra có tồn tại user với Id cần kiểm tra ko
	 * 
	 * @param userId
	 *            userId cần kiểm tra
	 * @return trả về true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Boolean checkExistUserById(int userId) throws ClassNotFoundException, SQLException {
		// Khởi tạo một TblUserDao
		TblUserDao userDaoImpl = new TblUserDaoImpl();
		try {
			// Khái báo một đối tượng TblUserEntity và gán bằng giá trị lấy về
			// từ hàm
			// getTblUserByLoginName
			TblUserEntity user = userDaoImpl.getTblUserById(userId);
			// Mếu loginNae rỗng nghĩa là không tồn tại
			if (user.getLoginName() != null) {
				return true;
			} 
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error : TblUserLogicImpl.checkExistUserById " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return false;
	}

	/**
	 * edit data user vào bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfoEntity
	 *            truyền vào đối tượng userInfoEntity dùng để edit
	 * @return true nếu edit thành công, false nếu insert không thành công
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NullPointerException
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public boolean editUser(UserInfoEntity userInfoEntity)
			throws ClassNotFoundException, SQLException, NullPointerException, NoSuchAlgorithmException {
		// Khởi tạo đối tượng TblDetailUserJapanLogic
		TblDetailUserJapanLogic tblDetailUserJapanLogic = new TblDetailUserJapanLogicImpl();
		// Khởi tạo đối tượng tblUserDaoImpl
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		// khởi tạo tblDetailUserJapanDaoImpl
		TblDetailUserJapanDao tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
		try {
			// Kiểm tra tồn tại trình độ tiếng nhật và gán vào biến isExistJapan
			boolean isExistJapan = tblDetailUserJapanLogic.checkExistJapan(userInfoEntity.getUserId());
			// mở kết nối database
			tblUserDaoImpl.openConnection();
			// Lấy về Connection
			Connection conn = tblUserDaoImpl.getConnection();
			if (conn != null) {
				// dữ liệu chỉ được thêm khi gọi lệnh commit
				tblUserDaoImpl.setDisableCommit(false);
				// lấy ra tblUserEntity
				TblUserEntity tblUserEntity = Common.getTblUserEntityFromTblUserInfor(userInfoEntity);
				// Thực hiện update bảng tbl_user và gán giá trị trả về vào biến
				// checkUpdateTblUser
				boolean checkUpdateTblUser = tblUserDaoImpl.updateTblUser(tblUserEntity);
				// Nếu update bảng tbl_user thành công
				if (checkUpdateTblUser) {
					// set connection
					tblDetailUserJapanDaoImpl.setConn(conn);
					// Kiểm tra người dùng có nhập trình độ tiếng Nhật không
					// Nếu có nhập trình độ tiếng Nhật
					if (!Common.checkEmpty(userInfoEntity.getCodeLevel())) {
						// lấy ra tblDetailUserJapanEntity từ userInfoEntity
						TblDetailUserJapanEntity tblDetailUserJapanEntity = Common
								.getTblDetailUserJapanEntity(userInfoEntity);
						// Nếu trong bảng tbl_detail_user_japan chưa tồn tại
						// trình độ tiếng Nhật
						if (!isExistJapan) {
							// insert vào bảng tbl_detail_user_japan
							tblDetailUserJapanDaoImpl.insertTblDetailUserJapan(tblDetailUserJapanEntity);
						} else {
							// update vào bảng tbl_detail_user_japan
							tblDetailUserJapanDaoImpl.updateTblDetailUserJapan(tblDetailUserJapanEntity);
						}
						// Nếu không nhập trình độ tiếng Nhật
					} else {
						// Nếu trong bảng tbl_detail_user_japan đã tồn tại trình
						// độ tiếng Nhật
						if (isExistJapan) {
							// delete các giá trị trong bảng
							// tbl_detail_user_japan
							tblDetailUserJapanDaoImpl.deleteTblDetailUserJapan(userInfoEntity.getUserId());
						}
					}
					// thực hiện thao tác vào cơ sở dữ liệu
					tblUserDaoImpl.commitData();
					// trả về update thành công
					return true;
				}
			}
			return false;
		} catch (ClassNotFoundException | SQLException | NullPointerException | NoSuchAlgorithmException e) {
			// lấy lại dữ liệu ban đầu
			tblUserDaoImpl.rollBack();
			// thông báo lỗi
			System.out.println("Error : TblUserLogicImpl.editUser " + e.getMessage());
			// gửi lỗi
			throw e;
		} finally {
			// đóng cơ sở dữ liệu
			tblUserDaoImpl.closeConnection();
		}
	}

	/**
	 * xóa dữ liệu ở bảng tbl_user, tbl_detail_user_japan
	 * 
	 * @param userId
	 *            id của user muốn xóa
	 * @return true nếu xóa thành công và ngược lại
	 * @throws SQLException
	 */
	@Override
	public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException, NullPointerException {
		// Khởi tạo đối tượng tblUserDaoImpl
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		// khởi tạo tblDetailUserJapanDaoImpl
		TblDetailUserJapanDao tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
		try {
			// mở kết nối database
			tblDetailUserJapanDaoImpl.openConnection();
			// Lấy về Connection
			Connection conn = tblDetailUserJapanDaoImpl.getConnection();
			// Nếu conn khác null
			if (conn != null) {
				// dữ liệu chỉ được thêm khi gọi lệnh commit
				tblDetailUserJapanDaoImpl.setDisableCommit(false);
				// xóa trong bảng tbl_detail_user_japan
				tblDetailUserJapanDaoImpl.deleteTblDetailUserJapan(userId);
				// set Connection
				tblUserDaoImpl.setConn(conn);
				// xóa dữ liệu ở bảng tbl_user
				if (tblUserDaoImpl.deleteTblUser(userId)) {
					// thực hiện xóa
					tblUserDaoImpl.commitData();
					// trả về xóa thành công
					return true;
				}
			}
			return false;
		} catch (ClassNotFoundException | SQLException | NullPointerException e) {
			// lấy lại dữ liệu ban đầu
			tblUserDaoImpl.rollBack();
			// thông báo lỗi
			System.out.println("Error : TblUserLogicImpl.editUser " + e.getMessage());
			// gửi lỗi
			throw e;
		} finally {
			// đóng cơ sở dữ liệu
			tblUserDaoImpl.closeConnection();
		}
	}

	/**
	 * Lấy ra Rule tương ứng với Id truyền vào
	 * 
	 * @param userId
	 *            userId cần kiểm tra
	 * @return trả về Rule tìm được
	 * @throws NullPointerException,
	 *             ClassNotFoundException, SQLException
	 */
	@Override
	public int getRuleById(int userId) throws NullPointerException, ClassNotFoundException, SQLException {
		int rule = -1;
		// Khởi tạo đối tượng TblUserDao
		TblUserDao tblUserDao = new TblUserDaoImpl();
		try {
			rule = tblUserDao.getRuleById(userId);
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			// thông báo lỗi
			System.out.println("Error : TblUserLogicImpl.getRuleById " + e.getMessage());
			// gửi lỗi
			throw e;
		}
		return rule;
	}
}
