package fscms.mods.mngr.vo;

import java.io.Serializable;

public class MngrIpEstbsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer ipEstbsSn;		// 아이피_설정_일련번호
	private String  ipEstbsTy;		// 아이피_설정_타입 (1:허용, 2:차단)
	private String  ipEstbsDc;		// 아이피_설정_설정
	private String  registIp;		// 등록_아이피
	private String  registDe;		// 등록_일자
	private String  updtDe;			// 수정_일자
	
	private String[] keyArr;
	
	public Integer getIpEstbsSn() {
		return ipEstbsSn;
	}
	public void setIpEstbsSn(Integer ipEstbsSn) {
		this.ipEstbsSn = ipEstbsSn;
	}
	public String getIpEstbsTy() {
		return ipEstbsTy;
	}
	public void setIpEstbsTy(String ipEstbsTy) {
		this.ipEstbsTy = ipEstbsTy;
	}
	public String getIpEstbsDc() {
		return ipEstbsDc;
	}
	public void setIpEstbsDc(String ipEstbsDc) {
		this.ipEstbsDc = ipEstbsDc;
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
	public String getUpdtDe() {
		return updtDe;
	}
	public void setUpdtDe(String updtDe) {
		this.updtDe = updtDe;
	}
	public String[] getKeyArr() {
		if(keyArr == null) {
			return null;
		}
		String[] newArr = new String[keyArr.length];
		System.arraycopy(keyArr, 0, newArr, 0, keyArr.length);
		return newArr;
	}
	public void setKeyArr(String[] keyArr) {
		String[] newArr = new String[keyArr.length];
		System.arraycopy(keyArr, 0, newArr, 0, keyArr.length);
		this.keyArr = newArr;
	}

}
