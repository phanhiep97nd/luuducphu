/**
 * Copyright(C) 2020  Luvina Software
 * BaseDaoImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.tomcat.dbcp.dbcp2.Jdbc41Bridge;

import com.mysql.jdbc.Connection;

import manageuser.dao.BaseDao;
import manageuser.utils.Constant;
import manageuser.utils.DatabaseProperties;

/**
 * Thực hiện các phương thức openConnection, closeConnection
 * 
 * @author Phan Van Hiep
 */
public class BaseDaoImpl implements BaseDao {
	// Khởi tạo đối tượng Connection
	protected Connection conn = null;

	/**
	 * Lấy về Connection
	 * 
	 * @return đối tượng Connection
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * set Connection
	 * 
	 * @param conn đối tượng Connection
	 * @return
	 */
	public void setConn(java.sql.Connection conn) {
		this.conn = (Connection)conn;
	}

	// Khởi tạo đối tượng PrepareStatement
	protected PreparedStatement pstm = null;
	// khai báo danh sách tên các cột trong cơ sở dữ liệu
	protected static HashMap<String, String> listColumn = null;

	/**
	 * Mở kết nối đến Database
	 * 
	 * @return conn
	 * @throws SQLException, ClassNotFoundException
	 */
	@Override
	public Connection openConnection() throws SQLException, ClassNotFoundException {
		// Lấy từ file properties và gán các giá trị driver, userName, pass, url
		String driver = DatabaseProperties.getValueByKey((Constant.DRIVER));
		String userName = DatabaseProperties.getValueByKey((Constant.USER_NAME));
		String pass = DatabaseProperties.getValueByKey((Constant.PASS));
		String url = DatabaseProperties.getValueByKey((Constant.URL));
		try {
			// Thực hiện kết nối đến DB
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, userName, pass);
		} catch (SQLException | ClassNotFoundException e) {
			// Hiển thị lỗi ở console
			System.out.println("Error : BaseDaoImpl.openConnection " + e.getMessage());
			// Throw lỗi đi
			throw e;
		}
		// trả về một đối tượng connection
		return conn;
	}

	/**
	 * Đóng kết nối đến DB
	 */
	@Override
	public void closeConnection() throws SQLException {
		try {
			// Nếu đang tồn tại đối tượng PrepareStatement thì đóng PrepareStatement
			if (pstm != null) {
				pstm.close();
			}
			// Nếu đang tồn tại đối tượng Connection và chưa đóng thì đóng Connection
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// Hiển thị lỗi ở console
			System.out.println("Error : BaseDaoImpl.closeConnection " + e.getMessage());
			// Throw lỗi đi
			throw e;
		}

	}

	/**
	 * getColumn Lấy ra danh list tất cả cột trong cơ sở dữ liệu
	 * 
	 * @return trả về list các cột trong cơ sở dữ liệu
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public HashMap<String, String> getListColumn() throws ClassNotFoundException, SQLException {
		// Khởi tạo một listColumn để chứa kết quả
		HashMap<String, String> listColumn = new HashMap<>();
		// khởi tạo Connection
		// khởi tạo PrepareStatement
		PreparedStatement pstm = null;
		Statement stm = null;
		try {
			// mở kết nối tới DB
			openConnection();
			if (conn != null) {
				stm = conn.createStatement();
				// Lấy ra tên các bảng có trong database
				ResultSet rs = stm.executeQuery("SHOW TABLES");
				// Duyệt tất cả các bảng
				while (rs.next()) {
					// lệnh sql lấy tất cả tên các cột trong cơ sở dũ liệu
					String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'manageuser_phanvanhiep' AND TABLE_NAME = ?";
					// thực hiện truy vấn
					pstm = (PreparedStatement) conn.prepareStatement(sql);
					// Khởi tạo chuỗi lưu trữ các tên cột lấy được
					String tableName = rs.getString(1);
					// khai báo vị trí tham số
					int index = 1;
					// truyền giá trị vào tham số
					pstm.setString(index++, tableName);
					// Lấy ra tên các cột có trong 1 bảng
					ResultSet resultColumn = pstm.executeQuery();
					// Duyệt tất cả các cột
					while (resultColumn.next()) {
						// Thêm tên các cột vào trong listColumnSort
						listColumn.put(tableName + "_" + resultColumn.getString(1), resultColumn.getString(1));
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị lỗi ở console
			System.out.println("Error: BaseDaoImpl.getListColumn " + e.getMessage());
			// Ném ra ngoại lệ
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// Trả về danh sách tên các cột trong cơ sở dữ liệu
		return listColumn;
	}

	/**
	 * quyết định có tự động ghi vào DB hay ko
	 * 
	 * @param isAutoCommit
	 *            có tự động commit hay là ko
	 * @throws SQLException
	 */
	@Override
	public void setDisableCommit(boolean isAutoCommit) throws SQLException {
		try {
			if (conn != null) {
				conn.setAutoCommit(isAutoCommit);
			}
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error : BaseDaoImpl.setAutoCommit " + e.getMessage());
			// gửi lỗi
			throw e;
		}

	}
	
	/**
	 * Thực hiện insert
	 * 
	 * @throws SQLException
	 */
	@Override
	public void commitData() throws SQLException {
		try {
			if (conn != null) {
				// bắt đầu thực hiện thao tác vào DB
				conn.commit();
			}
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error : BaseDaoImpl.commitData " + e.getMessage());
			// gửi lỗi
			throw e;
		}
	}

	/**
	 * Trả lại dữ liệu về trạng thái chưa insert
	 * 
	 * @throws SQLException
	 */
	@Override
	public void rollBack() throws SQLException {
		try {
			if (conn != null) {
				// lấy lại dữ liệu ban đầu
				conn.rollback();
			}
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error: BaseDaoImpl.rollBack " + e.getMessage());
			// throw lỗi
			throw e;
		}

	}
}
