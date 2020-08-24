/**
 * Copyright(C) 2020  Luvina Software
 * ConfigProperties.java, Jul 19, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Đọc các thông tin về biến config cần dùng cho logic
 * @author Phan Van Hiep
 */
public class ConfigProperties {
	// lưu các cặp <key, value> trong file properties
		private static Map<String, String> mapMessageProperties = new HashMap<String, String>();
		static {
			try {
				// tạo đối tượng kiểu Properties
				Properties properties = new Properties();
				// đọc file properties
				properties.load(new InputStreamReader(
						DatabaseProperties.class.getClassLoader().getResourceAsStream(Constant.CONFIG_MESSAGE_PATH),
						"UTF-8"));
				// lưu các giá trị key trong file properties
				Enumeration<?> enumeration = properties.propertyNames();
				// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
				while (enumeration.hasMoreElements()) {
					// key = key tiếp theo
					String key = (String) enumeration.nextElement();
					// lấy value tương ứng với key
					String value = properties.getProperty(key);
					// thêm vào list
					mapMessageProperties.put(key, value);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				// throw e;
			}
		}

		/**
		 * lấy value tương ứng với key trong file properties
		 * 
		 * @param key
		 *            tên key trong file properties
		 * @return trả về value tương ứng với key
		 */
		public static String getValueByKey(String key) {
			String value = mapMessageProperties.get(key);
			return value;
		}
}
