/**
 * Copyright(C) 2020  Luvina Software
 * TblDetailUserJapanDaoImpl.java, Aug 4, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.utils.Constant;

/**
 * Implement TblDeatilUserJapanDao để Xử lý Thao tác với DB bảng
 * tbl_detail_user_japan
 * 
 * @author Phan Van Hiep
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/**
	 * insert dữ liệu vào DB
	 * 
	 * @param tblDetailUserJapanEntity đối tượng tblDetailUserJapanEntity cần ghi
	 *                                 vào DB
	 * @return
	 */
	@Override
	public void insertTblDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity) throws SQLException {
		try {
			if (conn != null) {
				// tạo câu truy vấn
				StringBuilder sql = new StringBuilder(
						"INSERT INTO tbl_detail_user_japan (user_id, code_level, start_date, end_date, total)");
				sql.append(" VALUES(?,?,?,?,?)");
				// gọi đến hàm prepareStatement
				pstm = conn.prepareStatement(sql.toString());
				// khai báo vị trí tham số
				int index = 1;
				// set các giá trị cho các tham số
				pstm.setInt(index++, tblDetailUserJapanEntity.getUserId());
				pstm.setString(index++, tblDetailUserJapanEntity.getCodeLevel());
				pstm.setString(index++, tblDetailUserJapanEntity.getStartDate());
				pstm.setString(index++, tblDetailUserJapanEntity.getEndDate());
				pstm.setString(index++, tblDetailUserJapanEntity.getTotal());

				// thực hiện câu truy vấn truy vấn
				pstm.execute();
			}
		} catch (SQLException e) {
			// thông báo lỗi ở màn hình console
			System.out.println("Error : TblDetailUserJapanDaoImpl.insertTblDetailUserJapan " + e.getMessage());
			// throw lỗi
			throw e;

		}
	}

	/**
	 * Lấy ra một TblDetailUserJapanEntity theo userId từ bảng Tbl_detai_user_japan
	 * 
	 * @param userId userId dùng để lấy ra
	 * @return đối tượng TblDetailUserJapanEntity lấy được
	 * @throws SQLException,          NullPointerException
	 * @throws ClassNotFoundException
	 */
	@Override
	public TblDetailUserJapanEntity getTblDetailJapanById(int userId)
			throws SQLException, NullPointerException, ClassNotFoundException {
		// Khởi taho một đối tượng TblUserEntity
		TblDetailUserJapanEntity detailUserJapanEntity = new TblDetailUserJapanEntity();
		try {
			// Mở kết nối
			openConnection();
			// Check nếu mở kết nối thành công
			if (conn != null) {
				// Câu lệnh SQL
				String sql = "SELECT user_id FROM tbl_detail_user_japan WHERE user_id = ?";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Gán giá trị tương ứng vào câu truy vấn
				int index = 1;
				pstm.setInt(index++, userId);
				// Khởi tạo một đối tượng ResultSet để nhận kết quả trả về từ
				// câu Query
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					detailUserJapanEntity.setUserId(rs.getInt("user_id"));
				}
			}
		} catch (SQLException | NullPointerException | ClassNotFoundException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblDetailUserJapanDaoImpl.getTblDetailJapanById " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// Trả về đối tượng user
		return detailUserJapanEntity;
	}

	/**
	 * Thực hiện update các trường trong bảng tbl_user
	 * 
	 * @param tblUserEntity dùng để lấy ra các giá trị cần update
	 * @return trả về true nếu update thành công
	 * @throws SQLException
	 */
	@Override
	public boolean updateTblDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity) throws SQLException {
		try {
			// Câu truy vẫn upate dữ liệu vào bảng tbl_detail_user_japan
			StringBuilder sql = new StringBuilder("UPDATE tbl_detail_user_japan");
			sql.append(" SET code_level = ?, start_date = ?, end_date = ?, total = ? ");
			sql.append("WHERE user_id = ?");
			// khai báo PreparedStatement
			pstm = conn.prepareStatement(sql.toString());
			int index = 1;
			// Gán giá trị tương ứng vào câu truy vấn
			pstm.setString(index++, tblDetailUserJapanEntity.getCodeLevel());
			pstm.setString(index++, tblDetailUserJapanEntity.getStartDate());
			pstm.setString(index++, tblDetailUserJapanEntity.getEndDate());
			pstm.setString(index++, tblDetailUserJapanEntity.getTotal());
			pstm.setInt(index++, tblDetailUserJapanEntity.getUserId());
			// thực thi truy vấn(Trả về số cột bị tác động)
			int columnUpdated = pstm.executeUpdate();
			if (columnUpdated != 0) {
				// update thành công
				return true;
			}
			// update thất bại
			return false;
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error : TblDetailUserJapanDaoImpl.updateTblDetailUserJapan " + e.getMessage());
			// throw exception
			throw e;
		}

	}

	/**
	 * Thực hiện delete giá trị các trường trong bảng tbl_detail_user_japan
	 * 
	 * @param userId dùng để tạo điều kiện delete
	 * @return trả về true nếu delete thành công và ngược lại
	 * @throws SQLException
	 */
	@Override
	public boolean deleteTblDetailUserJapan(int userId) throws SQLException {
		try {
			// Tạo câu lệnh SQL thực hiện delete
			String sql = "DELETE FROM tbl_detail_user_japan WHERE user_id = ?";
			// khai báo PreparedStatement
			pstm = conn.prepareStatement(sql);
			int index = 1;
			// Gán giá trị tương ứng vào câu truy vấn
			pstm.setInt(index++, userId);
			// thực thi truy vấn(Trả về số cột bị tác động)
			int columnUpdated = pstm.executeUpdate();
			if (columnUpdated != 0) {
				// delete thành công
				return true;
			}
			// delete thất bại
			return false;
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error : TblDetailUserJapanDaoImpl.deleteTblDetailUserJapan " + e.getMessage());
			// throw ngoại lệ
			throw e;
		}
	}

}
