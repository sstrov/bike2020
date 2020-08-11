package fscms.mods.banner.vo;

import java.io.Serializable;

public class BannerEstbsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bannerEstbsSn;		// 배너_설정_일련번호
	private String  bannerEstbsNm;		// 배너_설정_명
	private Integer widthSize;			// 가로_사이즈
	private Integer vrticlSize;			// 세로_사이즈
	private String  bannerEstbsDc;		// 배너_설정_설명
	private Integer uploadLmttCpcty;	// 업로드_제한_용량
	private String  optnTagAt;			// 옵션_태그_여부
	private String  optnImageAt;		// 옵션_이미지_여부
	private String  optnBcrnImageAt;	// 옵션_배경_이미지_여부
	private String  optnClassAt;		// 옵션_클래스_여부
	private String  registDe;			// 등록_일자
	private String  updtDe;				// 수정_일자
	
	private Integer tCount;				// 총개수
	
	public Integer getBannerEstbsSn() {
		return bannerEstbsSn;
	}
	public void setBannerEstbsSn(Integer bannerEstbsSn) {
		this.bannerEstbsSn = bannerEstbsSn;
	}
	public String getBannerEstbsNm() {
		return bannerEstbsNm;
	}
	public void setBannerEstbsNm(String bannerEstbsNm) {
		this.bannerEstbsNm = bannerEstbsNm;
	}
	public Integer getWidthSize() {
		return widthSize;
	}
	public void setWidthSize(Integer widthSize) {
		this.widthSize = widthSize;
	}
	public Integer getVrticlSize() {
		return vrticlSize;
	}
	public void setVrticlSize(Integer vrticlSize) {
		this.vrticlSize = vrticlSize;
	}
	public String getBannerEstbsDc() {
		return bannerEstbsDc;
	}
	public void setBannerEstbsDc(String bannerEstbsDc) {
		this.bannerEstbsDc = bannerEstbsDc;
	}
	public Integer getUploadLmttCpcty() {
		return uploadLmttCpcty;
	}
	public void setUploadLmttCpcty(Integer uploadLmttCpcty) {
		this.uploadLmttCpcty = uploadLmttCpcty;
	}
	public String getOptnTagAt() {
		return optnTagAt;
	}
	public void setOptnTagAt(String optnTagAt) {
		this.optnTagAt = optnTagAt;
	}
	public String getOptnImageAt() {
		return optnImageAt;
	}
	public void setOptnImageAt(String optnImageAt) {
		this.optnImageAt = optnImageAt;
	}
	public String getOptnBcrnImageAt() {
		return optnBcrnImageAt;
	}
	public void setOptnBcrnImageAt(String optnBcrnImageAt) {
		this.optnBcrnImageAt = optnBcrnImageAt;
	}
	public String getOptnClassAt() {
		return optnClassAt;
	}
	public void setOptnClassAt(String optnClassAt) {
		this.optnClassAt = optnClassAt;
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

}
