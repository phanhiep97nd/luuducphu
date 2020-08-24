/**
 * Copyright(C) 2020  Luvina Software
 * TblUserLogic.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.logics;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.UserInfoEntity;

/**
 * Interface Xử lý logic cho các chức năng liên quan đến tbl_user
 * 
 * @author Phan Van Hiep
 */
public interface TblUserLogic {

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
	Boolean checkExistLogin(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;

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
	ArrayList<UserInfoEntity> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException;

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
	int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException;

	/**
	 * Kiểm tra xem đã tồn tại loginName chưa
	 * 
	 * @param loginName
	 *            loginName cần kiểm tra
	 * @return trả về true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Boolean checkExistedLoginName(String loginName) throws ClassNotFoundException, SQLException;

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
	Boolean checkExistedEmail(String email, int userId) throws ClassNotFoundException, SQLException;

	/**
	 * Insert data user vào bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfoEntity
	 *            truyền vào đối tượng userInfoEntity cần insert
	 * @return true nếu insert thành công, false nếu insert không thành công
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	boolean createUser(UserInfoEntity userInfoEntity)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;

	/**
	 * Lấy ra đối tượng userInfo từ userId
	 * 
	 * @param userId
	 *            để lấy ra đối tượng userInfo trong DB từ userId
	 * @return đối tượng UserInfoEntity lấy được
	 */
	UserInfoEntity getUserInfoByUserId(int userId) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra có tồn tại user với Id cần kiểm tra ko
	 * 
	 * @param userId
	 *            userId cần kiểm tra
	 * @return trả về true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Boolean checkExistUserById(int userId) throws ClassNotFoundException, SQLException;

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
	boolean editUser(UserInfoEntity userInfoEntity)
			throws ClassNotFoundException, SQLException, NullPointerException, NoSuchAlgorithmException;

	/**
	 * Lấy ra Rule tương ứng với Id truyền vào
	 * 
	 * @param userId
	 *            userId cần kiểm tra
	 * @return trả về Rule tìm được
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NullPointerException 
	 */
	int getRuleById(int userId) throws NullPointerException, ClassNotFoundException, SQLException;
	
	/**
	 * xóa dữ liệu ở bảng tbl_user, tbl_detail_user_japan
	 * @param userId id của user muốn xóa
	 * @return true nếu xóa thành công và ngược lại
	 * @throws NullPointerException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	boolean deleteUser(int userId) throws ClassNotFoundException, SQLException, NullPointerException;
}
