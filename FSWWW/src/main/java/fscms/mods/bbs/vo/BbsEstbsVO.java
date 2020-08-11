package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsEstbsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsEstbsSn;				// 게시판_설정_일련번호
	private Integer sknSnDp1;				// 스킨_일련번호_깊이1
	private Integer sknSnDp2;				// 스킨_일련번호_깊이2
	private String  bbsEstbsNm;				// 게시판_설정_명
	private Integer sjLtLmtt = 160;			// 제목_길이_제한
	private Integer listIndictCo = 10;		// 목록_표시_개수
	private String  ctgryAt = "N";			// 카테고리_여부
	private String  atchAt = "N";			// 첨부_여부
	private Integer atchSize = 2;			// 첨부_사이즈
	private String  thumbAt = "N";			// 썸네일_여부
	private String  answerAt = "N";			// 답글_여부
	private String  noticeAt = "N";			// 공지_여부
	private String  secretAt = "N";			// 비밀_여부
	private String  secretAtAlways = "N";	// 비밀_여부_항상
	private String  rdcntDplctAt = "Y";		// 조회수_중복_여부
	private String  registerNmTy = "NM";	// 등록자_명_타입
	private Integer indictDayNw = 3;		// 표시_일_새글
	private String  hderTag;				// 헤더_태그
	private String  hderCntntsAt = "N";		// 헤더_콘텐츠_여부
	private String  footTag;				// 푸터_태그
	private String  footCntntsAt = "N";		// 푸터_콘텐츠_여부
	private String  authorList = "M";		// 권한_목록
	private String  authorRedng = "M";		// 권한_읽기
	private String  authorRegist = "M";		// 권한_등록
	private String  authorAnswer = "M";		// 권한_답글
	private String  authorCm = "M";			// 권한_댓글
	private String  cmAt = "N";				// 댓글_여부
	private String  cmAnswerAt = "N";		// 댓글_답글_여부
	private String  cmLtLmttAt = "N";		// 댓글_길이_제한_여부
	private Integer cmLtLmttSize = 200;		// 댓글_길이_제한_사이즈
	private String  cmNmchangeAt = "Y";		// 댓글_명의변경_여부
	private String  cmPgngAt = "N";			// 댓글_페이징_여부
	private Integer cmListIndictCo = 10;	// 댓글_목록_표시_개수
	private String  cmSort = "DESC";		// 댓글_정렬
	private String  registDe;				// 등록_일자
	private String  updtDe;					// 수정_일자
	
	private Integer tCount;
	private String  sknSnDp1Code;
	private String  sknSnDp1Nm;
	private String  sknSnDp2Code;
	private String  sknSnDp2Nm;
	private String  cmmnViewAt;
	
	public Integer getBbsEstbsSn() {
		return bbsEstbsSn;
	}
	public void setBbsEstbsSn(Integer bbsEstbsSn) {
		this.bbsEstbsSn = bbsEstbsSn;
	}
	public Integer getSknSnDp1() {
		return sknSnDp1;
	}
	public void setSknSnDp1(Integer sknSnDp1) {
		this.sknSnDp1 = sknSnDp1;
	}
	public Integer getSknSnDp2() {
		return sknSnDp2;
	}
	public void setSknSnDp2(Integer sknSnDp2) {
		this.sknSnDp2 = sknSnDp2;
	}
	public String getBbsEstbsNm() {
		return bbsEstbsNm;
	}
	public void setBbsEstbsNm(String bbsEstbsNm) {
		this.bbsEstbsNm = bbsEstbsNm;
	}
	public Integer getSjLtLmtt() {
		return sjLtLmtt;
	}
	public void setSjLtLmtt(Integer sjLtLmtt) {
		this.sjLtLmtt = sjLtLmtt;
	}
	public Integer getListIndictCo() {
		return listIndictCo;
	}
	public void setListIndictCo(Integer listIndictCo) {
		this.listIndictCo = listIndictCo;
	}
	public String getCtgryAt() {
		return ctgryAt;
	}
	public void setCtgryAt(String ctgryAt) {
		this.ctgryAt = ctgryAt;
	}
	public String getAtchAt() {
		return atchAt;
	}
	public void setAtchAt(String atchAt) {
		this.atchAt = atchAt;
	}
	public Integer getAtchSize() {
		return atchSize;
	}
	public void setAtchSize(Integer atchSize) {
		this.atchSize = atchSize;
	}
	public String getThumbAt() {
		return thumbAt;
	}
	public void setThumbAt(String thumbAt) {
		this.thumbAt = thumbAt;
	}
	public String getAnswerAt() {
		return answerAt;
	}
	public void setAnswerAt(String answerAt) {
		this.answerAt = answerAt;
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
	public String getSecretAtAlways() {
		return secretAtAlways;
	}
	public void setSecretAtAlways(String secretAtAlways) {
		this.secretAtAlways = secretAtAlways;
	}
	public String getRdcntDplctAt() {
		return rdcntDplctAt;
	}
	public void setRdcntDplctAt(String rdcntDplctAt) {
		this.rdcntDplctAt = rdcntDplctAt;
	}
	public String getRegisterNmTy() {
		return registerNmTy;
	}
	public void setRegisterNmTy(String registerNmTy) {
		this.registerNmTy = registerNmTy;
	}
	public Integer getIndictDayNw() {
		return indictDayNw;
	}
	public void setIndictDayNw(Integer indictDayNw) {
		this.indictDayNw = indictDayNw;
	}
	public String getHderTag() {
		return hderTag;
	}
	public void setHderTag(String hderTag) {
		this.hderTag = hderTag;
	}
	public String getHderCntntsAt() {
		return hderCntntsAt;
	}
	public void setHderCntntsAt(String hderCntntsAt) {
		this.hderCntntsAt = hderCntntsAt;
	}
	public String getFootTag() {
		return footTag;
	}
	public void setFootTag(String footTag) {
		this.footTag = footTag;
	}
	public String getFootCntntsAt() {
		return footCntntsAt;
	}
	public void setFootCntntsAt(String footCntntsAt) {
		this.footCntntsAt = footCntntsAt;
	}
	public String getAuthorList() {
		return authorList;
	}
	public void setAuthorList(String authorList) {
		this.authorList = authorList;
	}
	public String getAuthorRedng() {
		return authorRedng;
	}
	public void setAuthorRedng(String authorRedng) {
		this.authorRedng = authorRedng;
	}
	public String getAuthorRegist() {
		return authorRegist;
	}
	public void setAuthorRegist(String authorRegist) {
		this.authorRegist = authorRegist;
	}
	public String getAuthorAnswer() {
		return authorAnswer;
	}
	public void setAuthorAnswer(String authorAnswer) {
		this.authorAnswer = authorAnswer;
	}
	public String getAuthorCm() {
		return authorCm;
	}
	public void setAuthorCm(String authorCm) {
		this.authorCm = authorCm;
	}
	public String getCmAt() {
		return cmAt;
	}
	public void setCmAt(String cmAt) {
		this.cmAt = cmAt;
	}
	public String getCmAnswerAt() {
		return cmAnswerAt;
	}
	public void setCmAnswerAt(String cmAnswerAt) {
		this.cmAnswerAt = cmAnswerAt;
	}
	public String getCmLtLmttAt() {
		return cmLtLmttAt;
	}
	public void setCmLtLmttAt(String cmLtLmttAt) {
		this.cmLtLmttAt = cmLtLmttAt;
	}
	public Integer getCmLtLmttSize() {
		return cmLtLmttSize;
	}
	public void setCmLtLmttSize(Integer cmLtLmttSize) {
		this.cmLtLmttSize = cmLtLmttSize;
	}
	public String getCmNmchangeAt() {
		return cmNmchangeAt;
	}
	public void setCmNmchangeAt(String cmNmchangeAt) {
		this.cmNmchangeAt = cmNmchangeAt;
	}
	public String getCmPgngAt() {
		return cmPgngAt;
	}
	public void setCmPgngAt(String cmPgngAt) {
		this.cmPgngAt = cmPgngAt;
	}
	public Integer getCmListIndictCo() {
		return cmListIndictCo;
	}
	public void setCmListIndictCo(Integer cmListIndictCo) {
		this.cmListIndictCo = cmListIndictCo;
	}
	public String getCmSort() {
		return cmSort;
	}
	public void setCmSort(String cmSort) {
		this.cmSort = cmSort;
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
	public String getSknSnDp1Code() {
		return sknSnDp1Code;
	}
	public void setSknSnDp1Code(String sknSnDp1Code) {
		this.sknSnDp1Code = sknSnDp1Code;
	}
	public String getSknSnDp1Nm() {
		return sknSnDp1Nm;
	}
	public void setSknSnDp1Nm(String sknSnDp1Nm) {
		this.sknSnDp1Nm = sknSnDp1Nm;
	}
	public String getSknSnDp2Code() {
		return sknSnDp2Code;
	}
	public void setSknSnDp2Code(String sknSnDp2Code) {
		this.sknSnDp2Code = sknSnDp2Code;
	}
	public String getSknSnDp2Nm() {
		return sknSnDp2Nm;
	}
	public void setSknSnDp2Nm(String sknSnDp2Nm) {
		this.sknSnDp2Nm = sknSnDp2Nm;
	}
	public String getCmmnViewAt() {
		return cmmnViewAt;
	}
	public void setCmmnViewAt(String cmmnViewAt) {
		this.cmmnViewAt = cmmnViewAt;
	}

}
