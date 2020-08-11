package fscms.mods.contest.vo;

import java.io.Serializable;

public class ContestVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer contestSn;		// 공모전_번호
	private Integer contestEstbsSn;	// 공모전_번호
	private String  contestNm;		// 공모전_명
	private String  contestCn;		// 공모전_내용
	private String  useAt;			// 사용여부
	private String  registDe;		// 등록_일자
	private String  updtDe;			// 수정_일자
	private String  registNm;		// 등록자_명
	private String  updtNm;			// 수정자_명
	private String  contestType;	// 공모전_타입(I:img, U:ucc)
	
	private String  oriFile;		// 첨부파일_이미지_원본명
	private String  atchmnflImage;	// 첨부파일_이미지
	private String  flpth;			// 파일경로
	
	private Integer tCount;			// 총개수
	
	public Integer getContestSn() {
		return contestSn;
	}

	public void setContestSn(Integer contestSn) {
		this.contestSn = contestSn;
	}

	public String getContestNm() {
		return contestNm;
	}

	public void setContestNm(String contestNm) {
		this.contestNm = contestNm;
	}

	public String getContestCn() {
		return contestCn;
	}

	public void setContestCn(String contestCn) {
		this.contestCn = contestCn;
	}


	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
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

	public String getContestType() {
		return contestType;
	}

	public void setContestType(String contestType) {
		this.contestType = contestType;
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

	public Integer getContestEstbsSn() {
		return contestEstbsSn;
	}

	public void setContestEstbsSn(Integer contestEstbsSn) {
		this.contestEstbsSn = contestEstbsSn;
	}

	public Integer gettCount() {
		return tCount;
	}

	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}

	public String getOriFile() {
		return oriFile;
	}

	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}


}
