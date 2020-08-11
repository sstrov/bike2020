package fscms.mods.error.vo;

import java.io.Serializable;

public class ErrorHistVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer errorHistSn;	// 오류_이력_일련번호
	private String  errorMssage;	// 오류_메시지
	private String  errorCn;		// 오류_내용
	private String  errorItnadr;	// 오류_인터넷주소
	private String  registIp;		// 등록_아이피
	private String  registDe;		// 등록_일자
	
	private Integer tCount;			// 총_개수
	
	public Integer getErrorHistSn() {
		return errorHistSn;
	}
	public void setErrorHistSn(Integer errorHistSn) {
		this.errorHistSn = errorHistSn;
	}
	public String getErrorMssage() {
		return errorMssage;
	}
	public void setErrorMssage(String errorMssage) {
		this.errorMssage = errorMssage;
	}
	public String getErrorCn() {
		return errorCn;
	}
	public void setErrorCn(String errorCn) {
		this.errorCn = errorCn;
	}
	public String getErrorItnadr() {
		return errorItnadr;
	}
	public void setErrorItnadr(String errorItnadr) {
		this.errorItnadr = errorItnadr;
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
