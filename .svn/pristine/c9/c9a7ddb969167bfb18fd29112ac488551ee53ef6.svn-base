/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupDaoImpl.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroupEntity;
import manageuser.utils.Constant;

/**
 * Implement MstGroupDao để Xử lý Thao tác với DB bảng mst_group
 * 
 * @author Phan Van Hiep
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	/**
	 * getAllMstGroup lấy tất cả các group trong bảng mst_group
	 * 
	 * @param
	 * @return list group trong bảng mst_group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException {
		// Khai báo listGroup để chứa các đối tượng MstGroupEntity lấy được
		ArrayList<MstGroupEntity> listGroup = new ArrayList<MstGroupEntity>();
		try {
			// Mở kết nối với DB
			openConnection();
			// Nếu mở thành công kết nối
			if (conn != null) {
				// Câu truy vấn DB
				String sql = "SELECT group_id, group_name FROM mst_group ORDER BY group_id ASC";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Khởi tạo ResultSet để chứa kết quả từ executeQuery()
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					// Khởi tạo đối tượng MstGroupEntity
					MstGroupEntity group = new MstGroupEntity();
					// Gán giá trị cho đối tượng group từ kết quả lấy từ ResultSet
					group.setGroupId(rs.getInt("group_id"));
					group.setGroupName(rs.getString("group_name"));
					// Thêm vào list group
					listGroup.add(group);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstGroupDaoImpl.getAllMstGroup " + e.getMessage());
			// Throw lỗi
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		return listGroup;
	}

	/**
	 * getGroupName lấy groupName tương ứng groupId
	 * 
	 * @param
	 * @return trả về tên nhóm
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String getGroupName(int groupId) throws ClassNotFoundException, SQLException {
		// khổi tạo groupName
		String groupName = "";
		try {
			// mở kết nối tới cơ sở dữ liệu
			openConnection();
			// kiểm tra xem đã kết nối được chưa
			if (conn != null) {
				// mở kết nối thành công câu lệnh sql lấy dữ liệu group_name trong bảng mst_group
				String sql = "SELECT group_name FROM mst_group WHERE group_id=?";
				// thực thi truy vấn
				pstm = conn.prepareStatement(sql);
				// khai báo vị trí tham số
				int index = 1;
				// truyền các giá trị vào cho các tham số
				pstm.setInt(index++, groupId);
				// bảng kết quả trả về khi thực hiện câu truy vấn sql
				ResultSet rs = pstm.executeQuery();

				// lấy dữ liệu từ ResultSet gán cho groupName
				while (rs.next()) {
					groupName = rs.getString("group_name");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// thông báo lỗi
			System.out.println("Error: MstGroupDaoImpl.getGroupName " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối lại
			closeConnection();
		}

		// trả về danh tên nhóm mst_group
		return groupName;
	}
}
