package fscms.mods.auth.vo;

import java.io.Serializable;

public class AuthNoteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer listSn;
	private Integer userSn;
	private String  noteSn;
	private String  authYn;
	private String  registDe;
	private String  updtDe;
	private String  userNm;
	private String  authSn;
	
	public Integer getListSn() {
		return listSn;
	}
	public void setListSn(Integer listSn) {
		this.listSn = listSn;
	}
	public Integer getUserSn() {
		return userSn;
	}
	public void setUserSn(Integer userSn) {
		this.userSn = userSn;
	}
	public String getNoteSn() {
		return noteSn;
	}
	public void setNoteSn(String noteSn) {
		this.noteSn = noteSn;
	}
	public String getAuthYn() {
		return authYn;
	}
	public void setAuthYn(String authYn) {
		this.authYn = authYn;
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
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getAuthSn() {
		return authSn;
	}
	public void setAuthSn(String authSn) {
		this.authSn = authSn;
	}
	
}
