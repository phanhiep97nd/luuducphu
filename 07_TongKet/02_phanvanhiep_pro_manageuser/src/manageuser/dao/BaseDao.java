/**
 * Copyright(C) 2020  Luvina Software
 * BaseDao.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

/**
 * Tạo ra các phương thức openConnection, closeConnection
 * 
 * @author Phan Van Hiep
 */
public interface BaseDao {

	/**
	 * Lấy về Connection
	 * 
	 * @return đối tượng Connection
	 */
	Connection getConnection();

	/**
	 * set Connection
	 * 
	 * @param conn đối tượng Connection
	 * @return
	 */
	void setConn(java.sql.Connection conn);

	/**
	 * Mở kết nối đến Database
	 * 
	 * @return conn
	 * @throws SQLException, ClassNotFoundException
	 */
	Connection openConnection() throws SQLException, ClassNotFoundException;

	/**
	 * closeDatabase đóng kết nối đến database
	 */
	void closeConnection() throws SQLException;

	/**
	 * getColumn Lấy ra danh list tất cả cột trong cơ sở dữ liệu
	 * 
	 * @return trả về list các cột trong cơ sở dữ liệu
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	HashMap<String, String> getListColumn() throws ClassNotFoundException, SQLException;
	
	/**
	 * quyết định có tự động ghi vào DB hay ko 
	 * @param isAutoCommit có tự động commit hay là ko
	 * @throws SQLException 
	 */
	void setDisableCommit(boolean isAutoCommit) throws SQLException;
	
	/**
	 * Thực hiện insert 
	 * @throws SQLException 
	 */
	void commitData() throws SQLException;

	/**
	 * Trả lại dữ liệu về trạng thái chưa insert
	 * @throws SQLException 
	 */
	void rollBack() throws SQLException;
}
