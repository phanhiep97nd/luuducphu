/**
 * Copyright(C) 2020  Luvina Software
 * MstJapanEntity.java, Jul 28, 2020 Phan Văn Hiệp
 */
package manageuser.entities;

import java.io.Serializable;

/**
 * Bean chứa các thuộc tính của bảng mst_japan trong db
 * @author Phan Van Hiep
 */
public class MstJapanEntity implements Serializable{
	private String codeLevel;
	private String nameLevel;
	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}
	/**
	 * @param codeLevel the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}
	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
	
}
