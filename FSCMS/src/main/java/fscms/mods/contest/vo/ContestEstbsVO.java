package fscms.mods.contest.vo;

import java.io.Serializable;

public class ContestEstbsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer contestEstbsSn;		// 공모전_번호
	private String  contestEstbsNm;		// 공모전_명
	private String  contestEstbsCn;		// 공모전_내용
	private String  contestBgnde;		// 공모전_시작일
	private String  contestEndde;		// 공모전_종료일
	private String  registDe;			// 등록_일자
	private String  updtDe;				// 수정_일자
	private String  registNm;			// 등록자_명
	private String  updtNm;				// 수정자_명
	private Integer workCount;			// 잠여자 수
	
	private String  atchmnflImage;		// 첨부파일_이미지
	private String  flpth;				// 파일경로
	
	private Integer tCount;				// 총개수

	public Integer getContestEstbsSn() {
		return contestEstbsSn;
	}

	public void setContestEstbsSn(Integer contestEstbsSn) {
		this.contestEstbsSn = contestEstbsSn;
	}

	public String getContestEstbsNm() {
		return contestEstbsNm;
	}

	public void setContestEstbsNm(String contestEstbsNm) {
		this.contestEstbsNm = contestEstbsNm;
	}

	public String getContestEstbsCn() {
		return contestEstbsCn;
	}

	public void setContestEstbsCn(String contestEstbsCn) {
		this.contestEstbsCn = contestEstbsCn;
	}

	public String getContestBgnde() {
		return contestBgnde;
	}

	public void setContestBgnde(String contestBgnde) {
		this.contestBgnde = contestBgnde;
	}

	public String getContestEndde() {
		return contestEndde;
	}

	public void setContestEndde(String contestEndde) {
		this.contestEndde = contestEndde;
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

	public String getRegistNm() {
		return registNm;
	}

	public void setRegistNm(String registNm) {
		this.registNm = registNm;
	}

	public String getUpdtNm() {
		return updtNm;
	}

	public void setUpdtNm(String updtNm) {
		this.updtNm = updtNm;
	}

	public Integer getWorkCount() {
		return workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}

	public String getAtchmnflImage() {
		return atchmnflImage;
	}

	public void setAtchmnflImage(String atchmnflImage) {
		this.atchmnflImage = atchmnflImage;
	}

	public String getFlpth() {
		return flpth;
	}

	public void setFlpth(String flpth) {
		this.flpth = flpth;
	}

	public Integer gettCount() {
		return tCount;
	}

	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	
}
