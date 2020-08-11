package fscms.mods.cntnts.vo;

import java.io.Serializable;

public class CntntsChghstVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cntntsChghstSn;
	private String  menuSn;
	private String  cntntsChghstCn;
	private String  registId;
	private String  registIp;
	private String  registDe;
	
	private Integer tCount;
	
	public Integer getCntntsChghstSn() {
		return cntntsChghstSn;
	}
	public void setCntntsChghstSn(Integer cntntsChghstSn) {
		this.cntntsChghstSn = cntntsChghstSn;
	}
	public String getMenuSn() {
		return menuSn;
	}
	public void setMenuSn(String menuSn) {
		this.menuSn = menuSn;
	}
	public String getCntntsChghstCn() {
		return cntntsChghstCn;
	}
	public void setCntntsChghstCn(String cntntsChghstCn) {
		this.cntntsChghstCn = cntntsChghstCn;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
	}
	public String getRegistIp() {
		return registIp;
	}
	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}

}
