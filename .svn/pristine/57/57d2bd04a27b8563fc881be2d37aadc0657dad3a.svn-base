/**
 * Copyright(C) 2020  Luvina Software
 * TblDetailUserJapanDao.java, Aug 4, 2020 Phan Văn Hiệp
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Interface Xử lý Thao tác với DB bảng tbl_detail_user_japan
 * 
 * @author Phan Van Hiep
 */
public interface TblDetailUserJapanDao extends BaseDao {

	/**
	 * insert dữ liệu vào bảng tb_detail_user_japan
	 * 
	 * @param tblDetailUserJapanEntity dữ liệu người dùng nhập vào ở phần trình độ
	 *                                 tiếng Nhật
	 * @return true là thêm thành công, false ngược lại
	 * @throws SQLException
	 */
	void insertTblDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity) throws SQLException;

	/**
	 * Lấy ra một TblDetailUserJapanEntity theo userId từ bảng Tbl_detai_user_japan
	 * 
	 * @param userId userId dùng để lấy ra
	 * @return đối tượng TblDetailUserJapanEntity lấy được
	 * @throws NullPointerException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	TblDetailUserJapanEntity getTblDetailJapanById(int userId)
			throws SQLException, NullPointerException, ClassNotFoundException;

	/**
	 * Thực hiện update các trường trong bảng tbl_detail_user_japan
	 * 
	 * @param TblDetailUserJapanEntity dùng để lấy ra các giá trị cần update
	 * @return trả về true nếu update thành công
	 * @throws SQLException
	 */
	boolean updateTblDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity) throws SQLException;

	/**
	 * Thực hiện delete giá trị các trường trong bảng tbl_detail_user_japan
	 * 
	 * @param userId dùng để tạo điều kiện delete
	 * @return trả về true nếu delete thành công và ngược lại
	 * @throws SQLException 
	 */
	boolean deleteTblDetailUserJapan(int userId) throws SQLException;

}
