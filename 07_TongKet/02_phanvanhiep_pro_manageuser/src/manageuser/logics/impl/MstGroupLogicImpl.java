/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupLogicImpl.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.MstGroupDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.logics.MstGroupLogic;

/**
 * Implement MstGroupLogic để Xử lý logic cho các chức năng liên quan đến
 * mst_group
 * 
 * @author Phan Van Hiep
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	/**
	 * getAllMstGroup lấy tất cả các group
	 * 
	 * @param
	 * @return list group trong bảng mst_group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException {
		// Khởi tạo listGroup để lưu tất cả các group lấy được
		ArrayList<MstGroupEntity> listGroup = new ArrayList<>();
		// Khởi tạo đối tượng mstGroupImpl
		MstGroupDao mstGroupImpl = new MstGroupDaoImpl();
		try {
			// gán giá trị listGroup bằng kết quả trả về từ hàm getAllMstGroup()
			// ở lớp MstGroupDaoImpl
			listGroup = mstGroupImpl.getAllMstGroup();
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstGroupLogicImpl.getAllMstGroup " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return listGroup;
	}

	/**
	 * checkExistGroup kiểm tra group có tồn tại hay không
	 * 
	 * @param groupId
	 *            Id group cần kiểm tra
	 * @return true nếu tồn tại, false nếu ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Boolean checkExistGroup(int groupId) throws ClassNotFoundException, SQLException {
		try {
			// Khởi tạo listGroup để lưu tất cả các group lấy được
			ArrayList<MstGroupEntity> listGroup = getAllMstGroup();
			// Khởi tạo một biến check
			boolean check = false;
			// Nếu có tồn tại groupId thì gán check bằng true
			for (int i = 0; i < listGroup.size(); i++) {
				if (listGroup.get(i).getGroupId() == groupId) {
					check = true;
				}
			}
			return check;
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstGroupLogicImpl.checkExistGroup " + e.getMessage());
			// Throw lỗi
			throw e;
		}
	}
	
	/**
	 * getGroupName lấy groupName tương ứng groupId
	 * @param
	 * @return trả về tên nhóm
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public String getGroupName(int groupId) throws ClassNotFoundException, SQLException {
		//khai báo groupName
		String groupName = "";
		//khởi tạo đối tượng mstGroupDaoImpl
		MstGroupDao mstGroupDaoImpl = new MstGroupDaoImpl();
		//lấy groupName tương ứng
		groupName = mstGroupDaoImpl.getGroupName(groupId);
		//trả về groupName lấy được
		return groupName;
	}
}
