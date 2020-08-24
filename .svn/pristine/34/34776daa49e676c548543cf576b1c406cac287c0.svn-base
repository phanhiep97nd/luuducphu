/**
 * Copyright(C) 2020  Luvina Software
 * MstJapanDaoImpl.java, Jul 28, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.utils.Constant;

/**
 * Implement MstJapanDao để Xử lý Thao tác với DB bảng mst_japan
 * 
 * @author Phan Van Hiep
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/**
	 * Lấy tất cả các nhóm trong bảng mst_japan
	 * 
	 * @return trả về danh sách tất cả các nhóm trong bảng mst_japan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public List<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException {
		// Khai báo listGroup để chứa các đối tượng MstJapanEntity lấy được
		List<MstJapanEntity> listMstJapan = new ArrayList<MstJapanEntity>();
		try {
			// Mở kết nối với DB
			openConnection();
			// Nếu mở thành công kết nối
			if (conn != null) {
				// Câu truy vấn DB
				String sql = "SELECT code_level, name_level FROM mst_japan ORDER BY name_level ASC";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Khởi tạo ResultSet để chứa kết quả từ executeQuery()
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					// Khởi tạo đối tượng MstJapanEntity
					MstJapanEntity mstJapanEntity = new MstJapanEntity();
					// Gán giá trị cho đối tượng japanEntity từ kết quả lấy từ ResultSet
					mstJapanEntity.setCodeLevel(rs.getString("code_level"));
					mstJapanEntity.setNameLevel(rs.getString("name_level"));
					// Thêm vào list japan
					listMstJapan.add(mstJapanEntity);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstJapanDaoImpl.getAllMstJapan " + e.getMessage());
			// Throw lỗi
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		return listMstJapan;
	}

	/**
	 * Lấy nameLevel tương ứng codeLevel trong DB
	 * 
	 * @param codeLevel để tìm ra nameLevel tương ứng
	 * @return trả về tên trình độ tiếng nhật
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String getNameLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		// khai báo groupName
		String nameLevel = "";
		try {
			// mở kết nối tới cơ sở dữ liệu
			openConnection();
			// kiểm tra xem đã kết nối được chưa
			if (conn != null) {
				// mở kết nối thành công câu lệnh sql lấy dữ liệu namelevel trong bảng mst_japan
				String sql = "SELECT name_level FROM mst_japan WHERE code_level LIKE ?";
				// thực thi truy vấn
				pstm = conn.prepareStatement(sql);
				// khai báo vị trí tham số
				int index = 1;
				// truyền các giá trị vào cho các tham số
				pstm.setString(index++, codeLevel);
				// bảng kết quả trả về khi thực hiện câu truy vấn sql
				ResultSet rs = pstm.executeQuery();

				// lấy dữ liệu từ ResultSet gán cho biến nameLevel
				while (rs.next()) {
					nameLevel = rs.getString("name_level");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// thông báo lỗi
			System.out.println("Error: MstJapanDaoImpl.getNameLevel " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối lại
			closeConnection();
		}
		// trả vè namelevel lấy được trong bảng mst_japan
		return nameLevel;
	}

}
