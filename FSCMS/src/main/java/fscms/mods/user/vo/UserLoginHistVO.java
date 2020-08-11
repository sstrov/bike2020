package fscms.mods.user.vo;

import java.io.Serializable;

public class UserLoginHistVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer loginHistSn;	// 로그인_이력_일련번호
	private Integer userSn;			// 유저_일련번호
	private String  sesionId;		// 세션_아이디
	private String  loginIp;		// 로그인_아이피
	private String  loginPltfom;	// 로그인_플랫폼
	private String  loginBrwsrNm;	// 로그인_브라우저_명
	private String  loginBrwsrVer;	// 로그인_브라우저_버전
	private String  loginBgnde;		// 로그인_시작일
	private String  loginEndde;		// 로그인_종료일
	
	private Integer tCount;
	private String  userId;
	private String  userNm;
	private String  userSalt;
	
	public Integer getLoginHistSn() {
		return loginHistSn;
	}
	public void setLoginHistSn(Integer loginHistSn) {
		this.loginHistSn = loginHistSn;
	}
	public Integer getUserSn() {
		return userSn;
	}
	public void setUserSn(Integer userSn) {
		this.userSn = userSn;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
