/**
 * Copyright(C) 2020  Luvina Software
TblDetailUserJapanLogic.java, Aug 12, 2020 Phan Van Hiep
 */
package manageuser.logics;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Interface để Xử lý logic cho các chức năng liên quan đến
 * tbl_detail_user_japan
 * 
 * @author Phan Van Hiep
 */
public interface TblDetailUserJapanLogic {
	/**
	 * Kiểm tra xem có tồn trình độ tiếng Nhật tương ứng với Id truyền vào không
	 * 
	 * @param userId
	 *            ID cần kiểm tra
	 * @return trả về true nếu tồn tại trình độ tiếng Nhật với Id truyền vào,
	 *         false nếu ngược lại
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NullPointerException 
	 */
	boolean checkExistJapan(int userId) throws NullPointerException, ClassNotFoundException, SQLException;
}
