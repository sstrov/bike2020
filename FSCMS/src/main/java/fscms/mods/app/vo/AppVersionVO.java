package fscms.mods.app.vo;

import java.io.Serializable;

public class AppVersionVO implements Serializable {

	private Integer osSn;
	private String os;
	private String ios;
	private String android;
	private String registDt;
	private String updtDt;
	private String registId;
	private String updtId;
	
	
	public Integer getOsSn() {
		return osSn;
	}
	public void setOsSn(Integer osSn) {
		this.osSn = osSn;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getIos() {
		return ios;
	}
	public void setIos(String ios) {
		this.ios = ios;
	}
	public String getAndroid() {
		return android;
	}
	public void setAndroid(String android) {
		this.android = android;
	}
	public String getRegistDt() {
		return registDt;
	}
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}
	public String getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
	}
	public String getUpdtId() {
		return updtId;
	}
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	
}
