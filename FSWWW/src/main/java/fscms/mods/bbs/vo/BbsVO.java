package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsSn;			// 게시판_일련번호
	private Integer bbsEstbsSn;		// 게시판_설정_일련번호
	private Integer bbsCtgrySn;		// 게시판_카테고리_일련번호
	private String  userId;			// 사용자_아이디
	private String  mngrId;			// 관리자_아이디
	private Integer bbsBestSn;		// 게시판_최상위_일련번호
	private Integer bbsUpperSn;		// 게시판_상위_일련번호
	private Integer bbsOrdr;		// 게시판_순서
	private Integer bbsDp;			// 게시판_깊이
	private String  bbsSj;			// 게시판_제목
	private String  bbsCn;			// 게시판_내용
	private String  registerNm;		// 등록자_명
	private String  registPw;		// 등록_비밀번호
	private String  noticeAt;		// 공지_여부
	private String  secretAt;		// 비밀_여부
	private Integer rdcnt;			// 조회수
	private Integer cmCo;			// 댓글_개수
	private String  bbsEtc1;		// 게시판_기타1
	private String  bbsEtc2;		// 게시판_기타2
	private String  bbsEtc3;		// 게시판_기타3
	private String  bbsEtc4;		// 게시판_기타4
	private String  bbsEtc5;		// 게시판_기타5
	private String  bbsEtc6;		// 게시판_기타6
	private String  bbsEtc7;		// 게시판_기타7
	private String  bbsEtc8;		// 게시판_기타8
	private String  bbsEtc9;		// 게시판_기타9
	private String  bbsEtc10;		// 게시판_기타10
	private String  bbsEtc11;		// 게시판_기타11
	private String  bbsEtc12;		// 게시판_기타12
	private String  bbsEtc13;		// 게시판_기타13
	private String  bbsEtc14;		// 게시판_기타14
	private String  bbsEtc15;		// 게시판_기타15
	private String  bbsEtc16;		// 게시판_기타16
	private String  bbsEtc17;		// 게시판_기타17
	private String  bbsEtc18;		// 게시판_기타18
	private String  bbsEtc19;		// 게시판_기타19
	private String  bbsEtc20;		// 게시판_기타20
	private String  bbsSalt;		// 게시판_솔트
	private String  deleteAt;		// 삭제_여부
	private String  registDe;		// 등록_일자
	private String  registIp;		// 등록_아이피
	private String  updtDe;			// 수정_일자
	private String  updtIp;			// 수정_아이피
	
	private String  ctgryNm;
	private String  ctgrySubNm;
	private String  bbsCtgrySubSn;
	private String  upperDeleteAt;
	private String  fileExt;
	
	private Integer bbsRnum;
	private Integer prevBbsSn;
	private Integer nextBbsSn;
	private String  menuSn;
	
	public Integer getBbsSn() {
		return bbsSn;
	}
	public void setBbsSn(Integer bbsSn) {
		this.bbsSn = bbsSn;
	}
	public Integer getBbsEstbsSn() {
		return bbsEstbsSn;
	}
	public void setBbsEstbsSn(Integer bbsEstbsSn) {
		this.bbsEstbsSn = bbsEstbsSn;
	}
	public Integer getBbsCtgrySn() {
		return bbsCtgrySn;
	}
	public void setBbsCtgrySn(Integer bbsCtgrySn) {
		this.bbsCtgrySn = bbsCtgrySn;
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
	public Integer getBbsBestSn() {
		return bbsBestSn;
	}
	public void setBbsBestSn(Integer bbsBestSn) {
		this.bbsBestSn = bbsBestSn;
	}
	public Integer getBbsUpperSn() {
		return bbsUpperSn;
	}
	public void setBbsUpperSn(Integer bbsUpperSn) {
		this.bbsUpperSn = bbsUpperSn;
	}
	public Integer getBbsOrdr() {
		return bbsOrdr;
	}
	public void setBbsOrdr(Integer bbsOrdr) {
		this.bbsOrdr = bbsOrdr;
	}
	public Integer getBbsDp() {
		return bbsDp;
	}
	public void setBbsDp(Integer bbsDp) {
		this.bbsDp = bbsDp;
	}
	public String getBbsSj() {
		return bbsSj;
	}
	public void setBbsSj(String bbsSj) {
		this.bbsSj = bbsSj;
	}
	public String getBbsCn() {
		return bbsCn;
	}
	public void setBbsCn(String bbsCn) {
		this.bbsCn = bbsCn;
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
	public String getNoticeAt() {
		return noticeAt;
	}
	public void setNoticeAt(String noticeAt) {
		this.noticeAt = noticeAt;
	}
	public String getSecretAt() {
		return secretAt;
	}
	public void setSecretAt(String secretAt) {
		this.secretAt = secretAt;
	}
	public Integer getRdcnt() {
		return rdcnt;
	}
	public void setRdcnt(Integer rdcnt) {
		this.rdcnt = rdcnt;
	}
	public Integer getCmCo() {
		return cmCo;
	}
	public void setCmCo(Integer cmCo) {
		this.cmCo = cmCo;
	}
	public String getBbsEtc1() {
		return bbsEtc1;
	}
	public void setBbsEtc1(String bbsEtc1) {
		this.bbsEtc1 = bbsEtc1;
	}
	public String getBbsEtc2() {
		return bbsEtc2;
	}
	public void setBbsEtc2(String bbsEtc2) {
		this.bbsEtc2 = bbsEtc2;
	}
	public String getBbsEtc3() {
		return bbsEtc3;
	}
	public void setBbsEtc3(String bbsEtc3) {
		this.bbsEtc3 = bbsEtc3;
	}
	public String getBbsEtc4() {
		return bbsEtc4;
	}
	public void setBbsEtc4(String bbsEtc4) {
		this.bbsEtc4 = bbsEtc4;
	}
	public String getBbsEtc5() {
		return bbsEtc5;
	}
	public void setBbsEtc5(String bbsEtc5) {
		this.bbsEtc5 = bbsEtc5;
	}
	public String getBbsEtc6() {
		return bbsEtc6;
	}
	public void setBbsEtc6(String bbsEtc6) {
		this.bbsEtc6 = bbsEtc6;
	}
	public String getBbsEtc7() {
		return bbsEtc7;
	}
	public void setBbsEtc7(String bbsEtc7) {
		this.bbsEtc7 = bbsEtc7;
	}
	public String getBbsEtc8() {
		return bbsEtc8;
	}
	public void setBbsEtc8(String bbsEtc8) {
		this.bbsEtc8 = bbsEtc8;
	}
	public String getBbsEtc9() {
		return bbsEtc9;
	}
	public void setBbsEtc9(String bbsEtc9) {
		this.bbsEtc9 = bbsEtc9;
	}
	public String getBbsEtc10() {
		return bbsEtc10;
	}
	public void setBbsEtc10(String bbsEtc10) {
		this.bbsEtc10 = bbsEtc10;
	}
	public String getBbsEtc11() {
		return bbsEtc11;
	}
	public void setBbsEtc11(String bbsEtc11) {
		this.bbsEtc11 = bbsEtc11;
	}
	public String getBbsEtc12() {
		return bbsEtc12;
	}
	public void setBbsEtc12(String bbsEtc12) {
		this.bbsEtc12 = bbsEtc12;
	}
	public String getBbsEtc13() {
		return bbsEtc13;
	}
	public void setBbsEtc13(String bbsEtc13) {
		this.bbsEtc13 = bbsEtc13;
	}
	public String getBbsEtc14() {
		return bbsEtc14;
	}
	public void setBbsEtc14(String bbsEtc14) {
		this.bbsEtc14 = bbsEtc14;
	}
	public String getBbsEtc15() {
		return bbsEtc15;
	}
	public void setBbsEtc15(String bbsEtc15) {
		this.bbsEtc15 = bbsEtc15;
	}
	public String getBbsEtc16() {
		return bbsEtc16;
	}
	public void setBbsEtc16(String bbsEtc16) {
		this.bbsEtc16 = bbsEtc16;
	}
	public String getBbsEtc17() {
		return bbsEtc17;
	}
	public void setBbsEtc17(String bbsEtc17) {
		this.bbsEtc17 = bbsEtc17;
	}
	public String getBbsEtc18() {
		return bbsEtc18;
	}
	public void setBbsEtc18(String bbsEtc18) {
		this.bbsEtc18 = bbsEtc18;
	}
	public String getBbsEtc19() {
		return bbsEtc19;
	}
	public void setBbsEtc19(String bbsEtc19) {
		this.bbsEtc19 = bbsEtc19;
	}
	public String getBbsEtc20() {
		return bbsEtc20;
	}
	public void setBbsEtc20(String bbsEtc20) {
		this.bbsEtc20 = bbsEtc20;
	}
	public String getBbsSalt() {
		return bbsSalt;
	}
	public void setBbsSalt(String bbsSalt) {
		this.bbsSalt = bbsSalt;
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
	public String getCtgryNm() {
		return ctgryNm;
	}
	public void setCtgryNm(String ctgryNm) {
		this.ctgryNm = ctgryNm;
	}
	public String getUpperDeleteAt() {
		return upperDeleteAt;
	}
	public void setUpperDeleteAt(String upperDeleteAt) {
		this.upperDeleteAt = upperDeleteAt;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public Integer getBbsRnum() {
		return bbsRnum;
	}
	public void setBbsRnum(Integer bbsRnum) {
		this.bbsRnum = bbsRnum;
	}
	public Integer getPrevBbsSn() {
		return prevBbsSn;
	}
	public void setPrevBbsSn(Integer prevBbsSn) {
		this.prevBbsSn = prevBbsSn;
	}
	public Integer getNextBbsSn() {
		return nextBbsSn;
	}
	public void setNextBbsSn(Integer nextBbsSn) {
		this.nextBbsSn = nextBbsSn;
	}
	public String getMenuSn() {
		return menuSn;
	}
	public void setMenuSn(String menuSn) {
		this.menuSn = menuSn;
	}
	public String getCtgrySubNm() {
		return ctgrySubNm;
	}
	public void setCtgrySubNm(String ctgrySubNm) {
		this.ctgrySubNm = ctgrySubNm;
	}
	public String getBbsCtgrySubSn() {
		return bbsCtgrySubSn;
	}
	public void setBbsCtgrySubSn(String bbsCtgrySubSn) {
		this.bbsCtgrySubSn = bbsCtgrySubSn;
	}

}
