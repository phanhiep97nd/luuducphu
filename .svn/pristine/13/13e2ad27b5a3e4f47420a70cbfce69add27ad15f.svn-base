/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupDao.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstGroupEntity;

/**
 * Interface Xử lý Thao tác với DB bảng mst_group
 * 
 * @author Phan Van Hiep
 */
public interface MstGroupDao extends BaseDao {

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
	 * getGroupName lấy groupName tương ứng groupId
	 * @param
	 * @return trả về tên nhóm
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	String getGroupName(int groupId) throws ClassNotFoundException, SQLException;

}
