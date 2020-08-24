/**
 * Copyright(C) 2020  Luvina Software
 * Constant.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

/**
 * Chứa các constant của dự án
 * 
 * @author Phan Van Hiep
 */
public class Constant {
	// constant database va message
	public static final String DRIVER = "DRIVER";
	public static final String USER_NAME = "USER_NAME";
	public static final String PASS = "PASS";
	public static final String URL = "URL";
	public static final String ER001_USERNAME = "ER001_USERNAME";
	public static final String ER001_PASS = "ER001_PASS";
	public static final String ER016 = "ER016";
	public static final String MSG005 = "MSG005";
	public static final String ER015 = "ER015";
	public static final String ER013 = "ER013";
	public static final String ER014 = "ER014";
	public static final String ER020 = "ER020";

	// Các thông báo validate ở màn hình ADM003
	public static final String ER001_LOGINNAME = MessageProperties.getValueByKey("ER001_LOGINNAME");
	public static final String ER003_LOGINNAME = MessageProperties.getValueByKey("ER003_LOGINNAME");
	public static final String ER007_LOGINNAME = MessageProperties.getValueByKey("ER007_LOGINNAME");
	public static final String ER0019_LOGINNAME = MessageProperties.getValueByKey("ER0019_LOGINNAME");
	public static final String ER002_GROUPID = MessageProperties.getValueByKey("ER002_GROUPID");
	public static final String ER004_GROUPID = MessageProperties.getValueByKey("ER004_GROUPID");
	public static final String ER001_FULLNAME = MessageProperties.getValueByKey("ER001_FULLNAME");
	public static final String ER006_FULLNAME = MessageProperties.getValueByKey("ER006_FULLNAME");
	public static final String ER006_FULLNAMEKANA = MessageProperties.getValueByKey("ER006_FULLNAMEKANA");
	public static final String ER009_FULLNAMEKANA = MessageProperties.getValueByKey("ER009_FULLNAMEKANA");
	public static final String ER011_BIRTHDAY = MessageProperties.getValueByKey("ER011_BIRTHDAY");
	public static final String ER001_MAIL = MessageProperties.getValueByKey("ER001_MAIL");
	public static final String ER003_MAIL = MessageProperties.getValueByKey("ER003_MAIL");
	public static final String ER005_MAIL = MessageProperties.getValueByKey("ER005_MAIL");
	public static final String ER006_MAIL = MessageProperties.getValueByKey("ER006_MAIL");
	public static final String ER001_TEL = MessageProperties.getValueByKey("ER001_TEL");
	public static final String ER005_TEL = MessageProperties.getValueByKey("ER005_TEL");
	public static final String ER006_TEL = MessageProperties.getValueByKey("ER006_TEL");
	public static final String ER008_TEL = MessageProperties.getValueByKey("ER008_TEL");
	public static final String ER001_PASSWORD = MessageProperties.getValueByKey("ER001_PASSWORD");
	public static final String ER007_PASSWORD = MessageProperties.getValueByKey("ER007_PASSWORD");
	public static final String ER008_PASSWORD = MessageProperties.getValueByKey("ER008_PASSWORD");
	public static final String ER017_PASSWORD_CONFIRM = MessageProperties.getValueByKey("ER017_PASSWORD_CONFIRM");
	public static final String ER004_CODELEVEL = MessageProperties.getValueByKey("ER004_CODELEVEL");
	public static final String ER011_STARTDATE = MessageProperties.getValueByKey("ER011_STARTDATE");
	public static final String ER011_ENDDATE = MessageProperties.getValueByKey("ER011_ENDDATE");
	public static final String ER012_ENDDATE = MessageProperties.getValueByKey("ER012_ENDDATE");
	public static final String ER001_TOTAL = MessageProperties.getValueByKey("ER001_TOTAL");
	public static final String ER018_TOTAL = MessageProperties.getValueByKey("ER018_TOTAL");
	public static final String ER006_TOTAL = MessageProperties.getValueByKey("ER006_TOTAL");

	// constant path properties
	public static final String PROPERTIES_MESSAGE_PATH = "//message.properties";
	public static final String PROPERTIES_DATABASE_PATH = "//database.properties";
	public static final String CONFIG_MESSAGE_PATH = "//config.properties";

	// constant path màn hình
	public static final String PATH_ADM001 = "/Views/jsp/ADM001.jsp";
	public static final String PATH_ADM002 = "/Views/jsp/ADM002.jsp";
	public static final String PATH_ADM003 = "/Views/jsp/ADM003.jsp";
	public static final String PATH_ADM004 = "/Views/jsp/ADM004.jsp";
	public static final String PATH_ADM005 = "/Views/jsp/ADM005.jsp";
	public static final String PATH_ADM006 = "/Views/jsp/ADM006.jsp";
	public static final String PATH_SYSTEM_ERROR = "/Views/jsp/System_Error.jsp";

	// constant URL
	public static final String PATH_LOGIN_CONTROLLER = "/login.do";
	public static final String URL_LOGIN = "login.do";
	public static final String URL_OUT = "logout.do";
	public static final String URL_LISTUSER = "listUser.do";
	public static final String URL_SYSTEMERROR = "systemError.do";
	public static final String PATH_SYSTEMERROR_CONTROLLER = "/systemError.do";
	public static final String URL_ADDUSERCONFIRM = "addUserConfirm.do?key=";
	public static final String URL_EDITUSERCONFIRM = "editUserConfirm.do?key=";

	// constant session
	public static final String SESSION_LOGINNAME = "loginName";
	public static final String SESSION_SEARCH = "search";
	public static final String SESSION_SORT_TYPE = "sortType";
	public static final String SESSION_SORT_LIKE = "sortLike";
	public static final String SESSION_CURRENTPAGE = "currentPage";
	public static final String SESSION_ADDUSER_CONFIRM = "addUserConfirm";
	public static final String SESSION_EDITUSER_CONFIRM = "editUserConfirm";

	// constant các hạng mục ở màn hình login
	public static final String NAME_TEXTBOX_LOGINNAME = "loginId";
	public static final String NAME_TEXTBOX_PASSWORD = "password";
	public static final String REQUEST_LISTERROR = "listError";
	public static final String REQUEST_LOGINNAME = "loginName";

	// constant các hạng mục ở MH listUser
	public static final int RULE_ADMIN = 0;
	public static final int RULE_USER = 1;
	public static final int USER_ID_DEFAULT = 0;
	public static final int GROUPID_DEFAULT = 0;
	public static final String FULLNAME_DEFAULT = "";
	public static final String SORTTYPE_DEFAULT = "";
	public static final String SORTLIKE_DEFAULT = "";
	public static final int CURRENTPAGE_DEFAULT = 1;
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String TYPE_DEFAULT = "default";
	public static final String TYPE_SEARCH = "search";
	public static final String TYPE_BACK = "back";
	public static final String TYPE_SORT = "sort";
	public static final String TYPE_PAGING = "paging";
	public static final int LIMIT_DEFAULT = 5;
	public static final int OFFSET_DEFAULT = 0;
	public static final int LIMITPAGE_DEFAULT = 3;
	public static final String SORT_TYPE_FULLNAME = "sortFullName";
	public static final String SORT_TYPE_CODELEVEL = "sortCodeLevel";
	public static final String SORT_TYPE_ENDDATE = "sortEndDate";
	public static final String NAME_TEXTBOX_FULLNAME = "name";
	public static final String NAME_PULLDOWN_GROUP = "group_id";
	public static final String REQUEST_SORTTYPE = "sortType";
	public static final String REQUEST_SORTLIKE = "sortLike";
	public static final String REQUEST_CURRENTPAGE = "currentPage";
	public static final String REQUEST_NOTIMSG005 = "notiMSG005";
	public static final String REQUEST_LISTGROUP = "listGroup";
	public static final String REQUEST_LISTUSERINFO = "listUserInfo";
	public static final String REQUEST_LISTPAGING = "listPaging";
	public static final String REQUEST_FULLNAME = "fullName";
	public static final String REQUEST_GROUPID = "groupId";
	public static final String REQUEST_TOTALPAGE = "totalPage";
	public static final String REQUEST_LIMITPAGE = "limitPage";
	public static final String REQUEST_SORTTYPE_FULLNAME = "sortTypeFullName";
	public static final String REQUEST_SORTTYPE_CODELEVEL = "sortTypeCodeLevel";
	public static final String REQUEST_SORTTYPE_ENDDATE = "sortTypeEndDate";
	public static final String REQUEST_SORTBY_FULLNAME = "sortByFullName";
	public static final String REQUEST_SORTBY_CODELEVEL = "sortByCodeLevel";
	public static final String REQUEST_SORTBY_ENDDATE = "sortByEndDate";

	// constant URL type
	public static final String URL_TYPE_DEFAULT = "?type=default";

	// constant config
	public static final String LIMIT = "LIMIT";
	public static final String LIMIT_PAGE = "LIMIT_PAGE";

	// constant các hạng mục ở màn hình ADM003
	public static final String DEFAULT_EMPTY = "";
	public static final int DEFAULT_ZERO = 0;
	public static final String REQUEST_LISTMSTGROUP = "listMstGroup";
	public static final String REQUEST_LISTMSTJAPAN = "listMstJapan";
	public static final String REQUEST_LISTYEAR = "listYear";
	public static final String REQUEST_LISTMONTH = "listMonth";
	public static final String REQUEST_LISTDAY = "listDay";
	public static final String REQUEST_USERINFORENTITY = "userInfoEntity";
	public static final String REQUEST_TYPE = "type";
	public static final String REQUEST_KEY = "key";
	//public static final String REQUEST_LISTERROR = "listError";
	public static final String TYPE_VALIDATE = "validate";
	public static final String LOGIN_NAME_ADM003 = "loginName";
	public static final String GROUPID_ADM003 = "groupId";
	public static final String FULL_NAME_ADM003 = "fullName";
	public static final String FULL_NAME_KATANA_ADM003 = "fullNameKatana";
	public static final String YEAR_OF_BIRTH_ADM003 = "yearofbirth";
	public static final String MONTH_OF_BIRTH_ADM003 = "monthofbirth";
	public static final String DAY_OF_BIRTH_ADM003 = "dayofbirth";
	public static final String EMAIL_ADM003 = "email";
	public static final String TEL_ADM003 = "tel";
	public static final String PASSWORD_ADM003 = "password";
	public static final String PASSWORD_CONFIRM_ADM003 = "passwordConfirm";
	public static final String CODE_LEVEL_ADM003 = "codeLevel";
	public static final String START_YEAR_ADM003 = "startyear";
	public static final String START_MONTH_ADM003 = "startmonth";
	public static final String START_DAY_ADM003 = "startday";
	public static final String END_YEAR_ADM003 = "endyear";
	public static final String END_MONTH_ADM003 = "endmonth";
	public static final String END_DAY_ADM003 = "endday";
	public static final String TOTAL_ADM003 = "total";

	// constant validate màn hình ADM003
	public static final int MIN_LENGTH_LOGINNAME = 4;
	public static final int MAX_LENGTH_LOGINNAME = 15;
	public static final String FORMAT_LOGINNAME = "[a-zA-Z_]{1,}[0-9a-zA-Z_]+";
	public static final int MAX_LENGTH_FULLNAME = 255;
	public static final int MAX_LENGTH_FULLNAMEKATANA = 255;
	public static final int MAX_LENGTH_EMAIL = 100;
	public static final String FORMAT_EMAIL = "^.+@[a-z0-9.-]{1,}\\.[a-zA-Z]{1,}$";
	public static final String FORMAT_TEL = "\\d{1,}-\\d{1,}-\\d{1,}";
	public static final int MAX_LENGTH_TEL = 14;
	public static final int MIN_LENGTH_PASS = 5;
	public static final int MAX_LENGTH_PASS = 15;
	public static final int MAX_LENGTH_TOTAL = 10;
	public static final String SESSION_CONFIRM_ADM003 = "confirmADM003";
	
	// Insert DB
	public static final String URL_SUCCESS = "success.do?type=";
	public static final String TYPE_ADD_SUCCESS = "addCuccsess";
	public static final String MSG001 = MessageProperties.getValueByKey("MSG001");
	public static final String MSG002 = MessageProperties.getValueByKey("MSG002");
	
	// ADM005
	public static final String REQUEST_ID = "id";
	public static final String URL_ERROR_NOTEXIST_ID = "?message=ER013";
	
	//systemError
	public static final String REQUEST_MESSAGE = "message";
	
	// editUser
	public static final String TYPE_EDIT_SUCCESS = "editCuccsess";
	public static final String TYPE_DELETE_SUCCESS = "deleteSuccess";
	
	// delete
	public static final String MSG003 = MessageProperties.getValueByKey("MSG003");
	public static final String MSG004 = MessageProperties.getValueByKey("MSG004");
	public static final String URL_ERROR_DELETE_ADMIN = "?message=ER020";
	public static final String URL_ERROR_DELETE_NOTEXIST = "?message=ER014";
	
}
