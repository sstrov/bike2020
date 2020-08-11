package fscms.mods.auth.vo;

import java.io.Serializable;

import fscms.mods.user.vo.UserVO;

public class AuthUploadVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer auSn;
	private String userNm;
	private String userBirth;
	private String userSex;
	private String userTelno;
	private String userZip;
	private String userAdresBass;
	private String userAdresDetail;
	private String userEmail;
	private String noteSn;
	private String authSn;
	private String courseNm;
	private String courseStart;
	private String authCenter;
	private String authStatus;
	private String pubDe;
	private String registDe;
	private String updtDe;
	private String oldData;
	
	private String userId;
	private String authCd;
	private String authNm;
	private String roadNm;
	private String roadCd;
	private String userSalt;
	
	private Integer tCount;
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserTelno() {
		return userTelno;
	}
	public void setUserTelno(String userTelno) {
		this.userTelno = userTelno;
	}
	public String getUserZip() {
		return userZip;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	public String getUserAdresDetail() {
		return userAdresDetail;
	}
	public void setUserAdresDetail(String userAdresDetail) {
		this.userAdresDetail = userAdresDetail;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getNoteSn() {
		return noteSn;
	}
	public void setNoteSn(String noteSn) {
		this.noteSn = noteSn;
	}
	public String getAuthSn() {
		return authSn;
	}
	public void setAuthSn(String authSn) {
		this.authSn = authSn;
	}
	public String getCourseNm() {
		return courseNm;
	}
	public void setCourseNm(String courseNm) {
		this.courseNm = courseNm;
	}
	public String getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(String courseStart) {
		this.courseStart = courseStart;
	}
	public String getAuthCenter() {
		return authCenter;
	}
	public void setAuthCenter(String authCenter) {
		this.authCenter = authCenter;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public String getPubDe() {
		return pubDe;
	}
	public void setPubDe(String pubDe) {
		this.pubDe = pubDe;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getAuSn() {
		return auSn;
	}
	public void setAuSn(Integer auSn) {
		this.auSn = auSn;
	}
	public String getUpdtDe() {
		return updtDe;
	}
	public void setUpdtDe(String updtDe) {
		this.updtDe = updtDe;
	}
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	public String getUserAdresBass() {
		return userAdresBass;
	}
	public void setUserAdresBass(String userAdresBass) {
		this.userAdresBass = userAdresBass;
	}
	public String getOldData() {
		return oldData;
	}
	public void setOldData(String oldData) {
		this.oldData = oldData;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public String getRoadNm() {
		return roadNm;
	}
	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}
	public String getRoadCd() {
		return roadCd;
	}
	public void setRoadCd(String roadCd) {
		this.roadCd = roadCd;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public String getAuthNm() {
		return authNm;
	}
	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}
}
