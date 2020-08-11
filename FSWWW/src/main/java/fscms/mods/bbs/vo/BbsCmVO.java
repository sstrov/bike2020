package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsCmVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsCmSn;		// 게시판_댓글_일련번호
	private Integer bbsSn;			// 게시판_일련번호
	private String  userId;			// 사용자_아이디
	private String  mngrId;			// 관리자_아이디
	private Integer bbsCmBestSn;	// 게시판_댓글_최상위_일련번호
	private Integer bbsCmUpperSn;	// 게시판_댓글_상위_일련번호
	private Integer bbsCmOrdr;		// 게시판_댓글_순서
	private Integer bbsCmDp;		// 게시판_댓글_깊이
	private String  bbsCmCn;		// 게시판_댓글_내용
	private String  registerNm;		// 등록자_명
	private String  registPw;		// 등록_비밀번호
	private Integer answerCo;		// 답글_개수
	private String  deleteAt;		// 삭제_여부
	private String  registDe;		// 등록_일자
	private String  registIp;		// 등록_아이피
	private String  updtDe;			// 수정_일자
	private String  updtIp;			// 수정_아이피
	
	private Integer tCount;
	
	public Integer getBbsCmSn() {
		return bbsCmSn;
	}
	public void setBbsCmSn(Integer bbsCmSn) {
		this.bbsCmSn = bbsCmSn;
	}
	public Integer getBbsSn() {
		return bbsSn;
	}
	public void setBbsSn(Integer bbsSn) {
		this.bbsSn = bbsSn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public Integer getBbsCmBestSn() {
		return bbsCmBestSn;
	}
	public void setBbsCmBestSn(Integer bbsCmBestSn) {
		this.bbsCmBestSn = bbsCmBestSn;
	}
	public Integer getBbsCmUpperSn() {
		return bbsCmUpperSn;
	}
	public void setBbsCmUpperSn(Integer bbsCmUpperSn) {
		this.bbsCmUpperSn = bbsCmUpperSn;
	}
	public Integer getBbsCmOrdr() {
		return bbsCmOrdr;
	}
	public void setBbsCmOrdr(Integer bbsCmOrdr) {
		this.bbsCmOrdr = bbsCmOrdr;
	}
	public Integer getBbsCmDp() {
		return bbsCmDp;
	}
	public void setBbsCmDp(Integer bbsCmDp) {
		this.bbsCmDp = bbsCmDp;
	}
	public String getBbsCmCn() {
		return bbsCmCn;
	}
	public void setBbsCmCn(String bbsCmCn) {
		this.bbsCmCn = bbsCmCn;
	}
	public String getRegisterNm() {
		return registerNm;
	}
	public void setRegisterNm(String registerNm) {
		this.registerNm = registerNm;
	}
	public String getRegistPw() {
		return registPw;
	}
	public void setRegistPw(String registPw) {
		this.registPw = registPw;
	}
	public Integer getAnswerCo() {
		return answerCo;
	}
	public void setAnswerCo(Integer answerCo) {
		this.answerCo = answerCo;
	}
	public String getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	public String getRegistIp() {
		return registIp;
	}
	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}
	public String getUpdtDe() {
		return updtDe;
	}
	public void setUpdtDe(String updtDe) {
		this.updtDe = updtDe;
	}
	public String getUpdtIp() {
		return updtIp;
	}
	public void setUpdtIp(String updtIp) {
		this.updtIp = updtIp;
	}
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}

}
