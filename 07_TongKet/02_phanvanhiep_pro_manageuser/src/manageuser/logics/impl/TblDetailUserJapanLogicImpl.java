/**
 * Copyright(C) 2020  Luvina Software
TblDetailUserJapanLogicImpl.java, Aug 12, 2020 Phan Van Hiep
 */
package manageuser.logics.impl;

import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.logics.TblDetailUserJapanLogic;

/**
 * Description
 * 
 * @author Phan Van Hiep
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {

	/**
	 * Kiểm tra xem có tồn trình độ tiếng Nhật tương ứng với Id truyền vào không
	 * 
	 * @param userId
	 *            ID cần kiểm tra
	 * @return trả về true nếu tồn tại trình độ tiếng Nhật với Id truyền vào,
	 *         false nếu ngược lại
	 * @throws NullPointerException,
	 *             ClassNotFoundException, SQLException
	 */
	@Override
	public boolean checkExistJapan(int userId) throws NullPointerException, ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng TblDetailUserJapanDao
		TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
		// Khởi tạo đối tượng TblDetailUserJapanEntity
		TblDetailUserJapanEntity detailUserJapanEntity = new TblDetailUserJapanEntity();
		try {
			// lấy về detailUserJapanEntity từ hàm getTblDetailJapanById
			detailUserJapanEntity = tblDetailUserJapanDao.getTblDetailJapanById(userId);
			if(detailUserJapanEntity.getUserId() == 0){
				return false;
			}else{
				return true;
			}
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblDetailUserJapanLogicImpl.checkExistJapan " + e.getMessage());
			throw e;
		}

	}

}
