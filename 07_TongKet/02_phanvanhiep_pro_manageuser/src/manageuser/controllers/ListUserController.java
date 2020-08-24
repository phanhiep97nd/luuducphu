/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConfigProperties;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Controller để xử lý cho màn hình ADM002
 * 
 * @author Phan Van Hiep
 */
public class ListUserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi vào màn hình, click button search, click link sort và paging ở MH
	 * ADM002
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session từ request
			HttpSession session = req.getSession();

			// Khởi tạo đối tượng groupLogicImpl
			MstGroupLogic groupLogicImpl = new MstGroupLogicImpl();
			// khởi tạo đối tượng userLogicImpl
			TblUserLogic userLogicImpl = new TblUserLogicImpl();
			// khai báo đối tượng userSearch để lưu điều kiện search
			TblUserEntity userSearch = new TblUserEntity();

			// Khởi tạo listUserInfo để chứa kết qẩu trả về hàm getListUser
			ArrayList<UserInfoEntity> listUserInfo = new ArrayList<UserInfoEntity>();
			// Khởi tạo listUser chứa kết quả trả về hàm getAllMstGroup()
			ArrayList<MstGroupEntity> listGroup = new ArrayList<MstGroupEntity>();
			// Khởi tạo listPaging chứa kết quả trả về hàm getListPaging()
			ArrayList<Integer> listPaging = new ArrayList<Integer>();

			// khai báo giá trị default cho các biến
			int limit = Common.getLimit();
			int offset = Constant.OFFSET_DEFAULT;
			int groupId = Constant.GROUPID_DEFAULT;
			String fullName = Constant.FULLNAME_DEFAULT;
			String sortType = Constant.SORTTYPE_DEFAULT;
			String sortLike = Constant.SORTLIKE_DEFAULT;
			String sortByFullName = Constant.ASC;
			String sortByCodeLevel = Constant.ASC;
			String sortByEndDate = Constant.DESC;
			int currentPage = Constant.CURRENTPAGE_DEFAULT;

			// Lấy giá trị của type trên request
			String type = req.getParameter(Constant.REQUEST_TYPE);
			// Nếu type là default, hoặc search, hoặc sort, hoặc paging, hoặc back
			if (type == null || Common.compareString(Constant.TYPE_DEFAULT, type)) {
				// xóa hết các session
				session.removeAttribute(Constant.SESSION_SEARCH);
				session.removeAttribute(Constant.SESSION_CURRENTPAGE);
				session.removeAttribute(Constant.SESSION_SORT_TYPE);
				session.removeAttribute(Constant.SESSION_SORT_LIKE);
			} else if (Common.compareString(Constant.TYPE_SEARCH, type)) {
				// Lấy fullNam và groupId người dùng nhập ở request
				fullName = req.getParameter(Constant.NAME_TEXTBOX_FULLNAME);
				groupId = Common.convertStringToInt(req.getParameter(Constant.NAME_PULLDOWN_GROUP),
						Constant.GROUPID_DEFAULT);
			} else if (Common.compareString(Constant.TYPE_SORT, type) || Common.compareString(Constant.TYPE_PAGING, type)) {
				// Lấy fullNam và groupId người dùng nhập từ request
				fullName = req.getParameter(Constant.NAME_TEXTBOX_FULLNAME);
				groupId = Common.convertStringToInt(req.getParameter(Constant.NAME_PULLDOWN_GROUP),
						Constant.GROUPID_DEFAULT);
				// Lấy về sortType từ request
				sortType = req.getParameter(Constant.REQUEST_SORTTYPE);
				// Lấy về kiểu sort ASC hay DESC từ request
				sortLike = req.getParameter(Constant.REQUEST_SORTLIKE);
				// lấy currentPage từ request để giữ lại paging
				currentPage = Common.convertStringToInt(req.getParameter(Constant.REQUEST_CURRENTPAGE),
						Constant.CURRENTPAGE_DEFAULT);
				if (Common.compareString(Constant.TYPE_SORT, type)) {
					// Nếu là sort theo fullName, hoặc sort theo codeLevel, hoặc là sort theo endDate
					if (Common.compareString(Constant.SORT_TYPE_FULLNAME, sortType)) {
						// Nếu kiểu sort là ASC hoặc ngược lại kiểu sort là DESC
						if (Common.compareString(Constant.ASC, sortLike)) {
							sortByFullName = Constant.DESC;
						} else if (Common.compareString(Constant.DESC, sortLike)) {
							sortByFullName = Constant.ASC;
						}
						// Gán giấ trị cho sortLike bằng với giá trị của sortByFullName để khi click
						// paging hoặc back có thể gán lại sort ASC hay DESC cho các trường
						sortLike = sortByFullName;
					} else if (Common.compareString(Constant.SORT_TYPE_CODELEVEL, sortType)) {
						// Nếu kiểu sort là ASC hoặc ngược lại kiểu sort là DESC
						if (Common.compareString(Constant.ASC, sortLike)) {
							sortByCodeLevel = Constant.DESC;
						} else if (Common.compareString(Constant.DESC, sortLike)) {
							sortByCodeLevel = Constant.ASC;
						}
						// Gán giấ trị cho sortLike bằng với giá trị của sortByFullName để khi click
						// paging hoặc back có thể gán lại sort ASC hay DESC cho các trường
						sortLike = sortByCodeLevel;
					} else if (Common.compareString(Constant.SORT_TYPE_ENDDATE, sortType)) {
						// Nếu kiểu sort là ASC hoặc ngược lại kiểu sort là DESC
						if (Common.compareString(Constant.ASC, sortLike)) {
							sortByEndDate = Constant.DESC;
						} else if (Common.compareString(Constant.DESC, sortLike)) {
							sortByEndDate = Constant.ASC;
						}
						// Gán giấ trị cho sortLike bằng với giá trị của sortByFullName để khi click
						// paging hoặc back có thể gán lại sort ASC hay DESC cho các trường
						sortLike = sortByEndDate;
					}
				} else if (Common.compareString(Constant.TYPE_PAGING, type)) {
					currentPage = Common.convertStringToInt(req.getParameter(Constant.REQUEST_CURRENTPAGE),
							Constant.CURRENTPAGE_DEFAULT);
					// Nếu là loại sắp xếp theo full_name
					if (Common.compareString(Constant.SORT_TYPE_FULLNAME, sortType)) {
						// Lấy giá trị sắp xếp theo fullname từ sortLike
						sortByFullName = sortLike;
						// nếu là loại sắp xếp theo code level
					} else if (Common.compareString(Constant.SORT_TYPE_CODELEVEL, sortType)) {
						// Lấy giá trị sắp xếp theo code level từ sortLike
						sortByCodeLevel = sortLike;
						// nếu là sắp xếp theo end_date
					} else if (Common.compareString(Constant.SORT_TYPE_ENDDATE, sortType)) {
						// Lấy giá trị sắp xếp theo code level từ sortLike
						sortByEndDate = sortLike;
					}
				}
			} else if (Common.compareString(Constant.TYPE_BACK, type)) {
				// Nếu tồn tại session search (Lấy xuống để khi click vẫn giữ được giá trị cùng điều kiện tìm kiếm)
				if (session.getAttribute(Constant.SESSION_SEARCH) != null) {
					// lấy đối tượng userSearch từ SESSION_SEARCH
					TblUserEntity userSearchGet = (TblUserEntity) session.getAttribute(Constant.SESSION_SEARCH);
					// Lấy ra thuộc tính
					groupId = userSearchGet.getGroupId();
					fullName = userSearchGet.getFullName();
				}
				// Nếu có tồn tại session currentPage (Lấy xuống để vẫn giữ được paging)
				if (session.getAttribute(Constant.SESSION_CURRENTPAGE) != null) {
					// lấy trang từ session
					currentPage = (int) session.getAttribute(Constant.SESSION_CURRENTPAGE);
				}
				// Nếu tồn tại session sortType (Lấy xuống để vẫn giữ được các giá trị sort)
				if (session.getAttribute(Constant.SESSION_SORT_TYPE) != null) {
					// Lấy giá trị sortLike từ SESSION_SORT_TYPE
					sortType = (String) session.getAttribute(Constant.SESSION_SORT_TYPE);
					// Lấy giá trị sortLike từ session
					sortLike = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
					// Nếu là loại sắp xếp theo full_name
					if (Common.compareString(Constant.SORT_TYPE_FULLNAME, sortType)) {
						// Lấy giá trị sắp xếp theo fullname từ sortLike trên session
						sortByFullName = sortLike;
						// Nếu là loại sắp xếp theo code level
					} else if (Common.compareString(Constant.SORT_TYPE_CODELEVEL, sortType)) {
						// lấy giá trị sắp xếp theo code level từ sortLike trên session
						sortByCodeLevel = sortLike;
						// Nếu là sắp xếp theo end_date
					} else if (Common.compareString(Constant.SORT_TYPE_ENDDATE, sortType)) {
						// Lấy giá trị sắp xếp theo code level từ sortLike trên session
						sortByEndDate = sortLike;
					}
				}
			}
			// Gán giá trị các thuộc tính cho đối tượng userSearch để có thể gửi lên session
			userSearch.setGroupId(groupId);
			userSearch.setFullName(fullName);
			// Lấy về listGroup từ hàm getAllMstGroup để gán lên pulldown
			listGroup = groupLogicImpl.getAllMstGroup();
			// Lấy về totalUser(tổng số user) theo groupId và fullName
			int totalUser = userLogicImpl.getTotalUsers(groupId, fullName);
			// Lấy về totalPage
			int totalPage = Common.getTotalPage(totalUser, limit);
			// Lấy về limitPage
			int limitPage = Common.convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_PAGE),
					Constant.LIMITPAGE_DEFAULT);
			// Nếu số user timd được lớn hơn 0
			if (totalUser > 0) {
				// Trường hợp người dùng sửa URL currentPage ngoài phạm vi số page
				if (currentPage > totalPage) {
					currentPage = totalPage;
				} else if (currentPage <= 0) {
					currentPage = Constant.CURRENTPAGE_DEFAULT;
				}
				// Lấy về giá trị offset
				offset = Common.getOffset(currentPage, limit);
				// (Lấy về list user))Gán kết quả trả về hàm getListUser vào listUserInfo
				listUserInfo = userLogicImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
						sortByCodeLevel, sortByEndDate);
				// (Lấy về list paging)Gán kết quả trả về hàm getListPaging vào listPaging
				listPaging = Common.getListPaging(totalUser, currentPage, limit);
				// Gán danh sách userInfor lên request
				req.setAttribute(Constant.REQUEST_LISTUSERINFO, listUserInfo);
				// Gán listPaging lên Request
				req.setAttribute(Constant.REQUEST_LISTPAGING, listPaging);
			} else {
				// Nếu không tìm được user nào thì hiển thị câu thông báo MSG005
				String notiMSG005 = MessageProperties.getValueByKey(Constant.MSG005);
				req.setAttribute(Constant.REQUEST_NOTIMSG005, notiMSG005);
			}
			// Gán danh sách group lên request(Để hiển thị pullDown)
			req.setAttribute(Constant.REQUEST_LISTGROUP, listGroup);
			// Truyền fullname tìm kiếm vào request(Để giữ lại giá trị cho textbox)
			req.setAttribute(Constant.REQUEST_FULLNAME, fullName);
			// Truyền groupID tìm kiếm vào request(Để giữ lại giá trị cho pulldown)
			req.setAttribute(Constant.REQUEST_GROUPID, groupId);
			// Gán totalPage lên request (Để phía jsp xử lí có hiện link >> không)
			req.setAttribute(Constant.REQUEST_TOTALPAGE, totalPage);
			// Gán limitPage lên request (Để ở jsp tính ra currentPage khi click << hoặc >>)
			req.setAttribute(Constant.REQUEST_LIMITPAGE, limitPage);
			// Gán currentPage lên request (để jsp không hiển thị currentpage là một link)
			req.setAttribute(Constant.REQUEST_CURRENTPAGE, currentPage);
			// Gán các trường hợp của sortType lên request
			req.setAttribute(Constant.REQUEST_SORTTYPE_FULLNAME, Constant.SORT_TYPE_FULLNAME);
			req.setAttribute(Constant.REQUEST_SORTTYPE_CODELEVEL, Constant.SORT_TYPE_CODELEVEL);
			req.setAttribute(Constant.REQUEST_SORTTYPE_ENDDATE, Constant.SORT_TYPE_ENDDATE);
			// Gán sortLike của các trường lên request(Là ASC hay DESC của từng trường)
			req.setAttribute(Constant.REQUEST_SORTBY_FULLNAME, sortByFullName);
			req.setAttribute(Constant.REQUEST_SORTBY_CODELEVEL, sortByCodeLevel);
			req.setAttribute(Constant.REQUEST_SORTBY_ENDDATE, sortByEndDate);
			// Gán sortType và sortLike lên request(Để khi click paging có thể truyền các
			// giá trị này lên req, từ đó có thể giữ lại sort)
			req.setAttribute(Constant.REQUEST_SORTTYPE, sortType);
			req.setAttribute(Constant.REQUEST_SORTLIKE, sortLike);

			// Truyền userSearch lên session SESSION_SEARCH để khi back có thể giữ dược các
			// giá trị ng dùng đã nhập
			session.setAttribute(Constant.SESSION_SEARCH, userSearch);
			// Truyền crrentPage lên session để khi back có thể giữ được list paging
			session.setAttribute(Constant.SESSION_CURRENTPAGE, currentPage);
			// Gán sortType và sortLike lên session để khi back có thể giữ được giá trị sort
			session.setAttribute(Constant.SESSION_SORT_TYPE, sortType);
			session.setAttribute(Constant.SESSION_SORT_LIKE, sortLike);

			// Chuyển hướng sang trang ADM002
			req.getServletContext().getRequestDispatcher(Constant.PATH_ADM002).forward(req, resp);
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ListUserController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}

	}
}
