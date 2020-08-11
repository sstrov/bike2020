package fscms.mods.user.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userSn;					// 사용자_일련번호
	private Integer roleSn;					// 역할_일련번호
	private String  userId;					// 사용자_아이디
	private String  userPw;					// 사용자_비밀번호
	private String  userNm;					// 사용자_명
	private String  userTelno;				// 사용자_전화번호
	private String  userMbtlnum;			// 사용자_휴대폰번호
	private String  userMbtlnumRecptnAt;	// 사용자_휴대폰번호_수신_여부 (Y:수신, N:수신거부)
	private String  userEmail;				// 사용자_이메일
	private String  userEmailRecptnAt;		// 사용자_이메일_수신_여부 (Y:수신, N:수신거부)
	private String  userZip;				// 사용자_우편번호
	private String  userAdresBass;			// 사용자_주소_기본
	private String  userAdresDetail;		// 사용자_주소_상세
	private String  userRm;					// 사용자_비고
	private String  userSalt;				// 사용자_솔트
	private String  userSttus;				// 사용자_상태 (1:일반, 2:수동탈퇴, 3:2년 재동의 미동의)
	private String  loginDe;				// 로그인_일자
	private Integer lockCo;					// 잠금_개수
	private String  lockDe;					// 잠금_일자
	private String  virtlSn;				// 가상_일련번호
	private String  registDe;				// 등록_일자
	private String  updtDe;					// 수정_일자
	
	
	private String  nickNm;
	private String  noteSn;
	private String  noteSnAdd;
	private String  noteSnList;
	private String  hint;
	private String  hintAnswer;
	private String  expireDe;
	private String  agreeDe;
	private String  dropReason;
	
	
	private Integer tCount;			// 총개수
	
	public Integer getUserSn() {
		return userSn;
	}
	public void setUserSn(Integer userSn) {
		this.userSn = userSn;
	}
	public Integer getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(Integer roleSn) {
		this.roleSn = roleSn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserTelno() {
		return userTelno;
	}
	public void setUserTelno(String userTelno) {
		this.userTelno = userTelno;
	}
	public String getUserMbtlnum() {
		return userMbtlnum;
	}
	public void setUserMbtlnum(String userMbtlnum) {
		this.userMbtlnum = userMbtlnum;
	}
	public String getUserMbtlnumRecptnAt() {
		return userMbtlnumRecptnAt;
	}
	public void setUserMbtlnumRecptnAt(String userMbtlnumRecptnAt) {
		this.userMbtlnumRecptnAt = userMbtlnumRecptnAt;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmailRecptnAt() {
		return userEmailRecptnAt;
	}
	public void setUserEmailRecptnAt(String userEmailRecptnAt) {
		this.userEmailRecptnAt = userEmailRecptnAt;
	}
	public String getUserZip() {
		return userZip;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	public String getUserAdresBass() {
		return userAdresBass;
	}
	public void setUserAdresBass(String userAdresBass) {
		this.userAdresBass = userAdresBass;
	}
	public String getUserAdresDetail() {
		return userAdresDetail;
	}
	public void setUserAdresDetail(String userAdresDetail) {
		this.userAdresDetail = userAdresDetail;
	}
	public String getUserRm() {
		return userRm;
	}
	public void setUserRm(String userRm) {
		this.userRm = userRm;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public String getUserSttus() {
		return userSttus;
	}
	public void setUserSttus(String userSttus) {
		this.userSttus = userSttus;
	}
	public String getLoginDe() {
		return loginDe;
	}
	public void setLoginDe(String loginDe) {
		this.loginDe = loginDe;
	}
	public Integer getLockCo() {
		return lockCo;
	}
	public void setLockCo(Integer lockCo) {
		this.lockCo = lockCo;
	}
	public String getLockDe() {
		return lockDe;
	}
	public void setLockDe(String lockDe) {
		this.lockDe = lockDe;
	}
	public String getVirtlSn() {
		return virtlSn;
	}
	public void setVirtlSn(String virtlSn) {
		this.virtlSn = virtlSn;
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
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	public String getNickNm() {
		return nickNm;
	}
	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}
	public String getNoteSn() {
		return noteSn;
	}
	public void setNoteSn(String noteSn) {
		this.noteSn = noteSn;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getHintAnswer() {
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}
	public String getExpireDe() {
		return expireDe;
	}
	public void setExpireDe(String expireDe) {
		this.expireDe = expireDe;
	}
	public String getNoteSnAdd() {
		return noteSnAdd;
	}
	public void setNoteSnAdd(String noteSnAdd) {
		this.noteSnAdd = noteSnAdd;
	}
	public String getNoteSnList() {
		return noteSnList;
	}
	public void setNoteSnList(String noteSnList) {
		this.noteSnList = noteSnList;
	}
	public String getDropReason() {
		return dropReason;
	}
	public void setDropReason(String dropReason) {
		this.dropReason = dropReason;
	}
	public String getAgreeDe() {
		return agreeDe;
	}
	public void setAgreeDe(String agreeDe) {
		this.agreeDe = agreeDe;
	}
	
	
}
