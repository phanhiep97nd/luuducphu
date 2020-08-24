/**
 * Copyright(C) 2020  Luvina Software
 * MstJapanLogic.java, Jul 28, 2020 Phan Văn Hiệp
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapanEntity;

/**
 * Interface Xử lý logic cho các chức năng liên quan đến tbl_detail_user_japan
 * @author Phan Van Hiep
 */
public interface MstJapanLogic {
	
	/**
	 * Lấy tất cả các nhóm trong bảng mst_japan
	 * 
	 * @return trả về danh sách tất cả các nhóm trong bảng mst_japan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException;
	
	/**
	 * checkExistCodeLevel kiểm tra trình độ tiếng Nhật có tồn tại hay không
	 * 
	 * @param codeLevel trình độ tiếng Nhật cần kiếm tra
	 * @return true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Boolean checkExistCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException;
	
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
