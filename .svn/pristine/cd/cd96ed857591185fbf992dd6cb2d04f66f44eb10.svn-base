/**
 * Copyright(C) 2020  Luvina Software
 * MstJapanDao.java, Jul 28, 2020 Phan Văn Hiệp
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapanEntity;

/**
 * Interface Xử lý Thao tác với DB bảng mst_japan
 * 
 * @author Phan Van Hiep
 */
public interface MstJapanDao extends BaseDao{

	/**
	 * Lấy tất cả các nhóm trong bảng mst_japan
	 * 
	 * @return trả về danh sách tất cả các nhóm trong bảng mst_japan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException;

	/**
	 * Lấy nameLevel tương ứng codeLevel trong DB
	 * 
	 * @param codeLevel để tìm ra nameLevel tương ứng
	 * @return trả về tên trình độ tiếng nhật
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	String getNameLevel(String codeLevel) throws ClassNotFoundException, SQLException;
}
