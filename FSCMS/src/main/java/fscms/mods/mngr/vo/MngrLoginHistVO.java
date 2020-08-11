package fscms.mods.mngr.vo;

import java.io.Serializable;

public class MngrLoginHistVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer loginHistSn;	// 로그인_이력_일련번호
	private Integer mngrSn;			// 관리자_일련번호
	private String  sesionId;		// 세션_아이디
	private String  loginIp;		// 로그인_아이피
	private String  loginPltfom;	// 로그인_플랫폼
	private String  loginBrwsrNm;	// 로그인_브라우저_명
	private String  loginBrwsrVer;	// 로그인_브라우저_버전
	private String  loginBgnde;		// 로그인_시작일
	private String  loginEndde;		// 로그인_종료일
	
	private Integer tCount;
	private String  mngrId;
	private String  mngrNm;
	private String  mngrSalt;
	
	public Integer getLoginHistSn() {
		return loginHistSn;
	}
	public void setLoginHistSn(Integer loginHistSn) {
		this.loginHistSn = loginHistSn;
	}
	public Integer getMngrSn() {
		return mngrSn;
	}
	public void setMngrSn(Integer mngrSn) {
		this.mngrSn = mngrSn;
	}
	public String getSesionId() {
		return sesionId;
	}
	public void setSesionId(String sesionId) {
		this.sesionId = sesionId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginPltfom() {
		return loginPltfom;
	}
	public void setLoginPltfom(String loginPltfom) {
		this.loginPltfom = loginPltfom;
	}
	public String getLoginBrwsrNm() {
		return loginBrwsrNm;
	}
	public void setLoginBrwsrNm(String loginBrwsrNm) {
		this.loginBrwsrNm = loginBrwsrNm;
	}
	public String getLoginBrwsrVer() {
		return loginBrwsrVer;
	}
	public void setLoginBrwsrVer(String loginBrwsrVer) {
		this.loginBrwsrVer = loginBrwsrVer;
	}
	public String getLoginBgnde() {
		return loginBgnde;
	}
	public void setLoginBgnde(String loginBgnde) {
		this.loginBgnde = loginBgnde;
	}
	public String getLoginEndde() {
		return loginEndde;
	}
	public void setLoginEndde(String loginEndde) {
		this.loginEndde = loginEndde;
	}
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getMngrSalt() {
		return mngrSalt;
	}
	public void setMngrSalt(String mngrSalt) {
		this.mngrSalt = mngrSalt;
	}

}
