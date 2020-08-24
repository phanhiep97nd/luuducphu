/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupLogic.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstGroupEntity;

/**
 * Interface Xử lý logic cho các chức năng liên quan đến mst_group
 * 
 * @author Phan Van Hiep
 */
public interface MstGroupLogic {

	/**
	 * getAllMstGroup lấy tất cả các group trong bảng mst_group
	 * 
	 * @param
	 * @return list group trong bảng mst_group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException;

	/**
	 * checkExistGroup kiểm tra group có tồn tại hay không
	 * 
	 * @param groupId
	 *            Id group cần kiểm tra
	 * @return true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Boolean checkExistGroup(int groupId) throws ClassNotFoundException, SQLException;
	
	/**
	 * getGroupName lấy groupName tương ứng groupId
	 * @param
	 * @return trả về tên nhóm
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	String getGroupName(int groupId) throws ClassNotFoundException, SQLException;

}
