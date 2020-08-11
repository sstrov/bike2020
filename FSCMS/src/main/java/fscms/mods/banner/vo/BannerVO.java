package fscms.mods.banner.vo;

import java.io.Serializable;

public class BannerVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bannerSn;				// 배너_일련번호
	private Integer bannerEstbsSn;			// 배너_설정_일련번호
	private String  bannerNm;				// 배너_명
	private Integer bannerOrdr;				// 배너_순서
	private String  bannerBgnde;			// 배너_시작일
	private String  bannerEndde;			// 배너_종료일
	private String  bannerItnadr;			// 배너_인터넷주소
	private String  bannerTrgt;				// 배너_타겟
	private String  tagCn;					// 태그_내용
	private String  atchmnflImage;			// 첨부파일_이미지
	private String  atchmnflBcrnImage;		// 첨부파일_배경_이미지
	private String  flpth;					// 파일경로
	private String  classNm;				// 클래스_명
	private String  useAt;					// 사용_여부
	private String  registDe;				// 등록_일자
	private String  updtDe;					// 수정_일자
	
	private Integer tCount;					// 총개수
	private String  mainAt;					// 메인 호출 여부
	
	public Integer getBannerSn() {
		return bannerSn;
	}
	public void setBannerSn(Integer bannerSn) {
		this.bannerSn = bannerSn;
	}
	public Integer getBannerEstbsSn() {
		return bannerEstbsSn;
	}
	public void setBannerEstbsSn(Integer bannerEstbsSn) {
		this.bannerEstbsSn = bannerEstbsSn;
	}
	public String getBannerNm() {
		return bannerNm;
	}
	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
	}
	public Integer getBannerOrdr() {
		return bannerOrdr;
	}
	public void setBannerOrdr(Integer bannerOrdr) {
		this.bannerOrdr = bannerOrdr;
	}
	public String getBannerBgnde() {
		return bannerBgnde;
	}
	public void setBannerBgnde(String bannerBgnde) {
		this.bannerBgnde = bannerBgnde;
	}
	public String getBannerEndde() {
		return bannerEndde;
	}
	public void setBannerEndde(String bannerEndde) {
		this.bannerEndde = bannerEndde;
	}
	public String getBannerItnadr() {
		return bannerItnadr;
	}
	public void setBannerItnadr(String bannerItnadr) {
		this.bannerItnadr = bannerItnadr;
	}
	public String getBannerTrgt() {
		return bannerTrgt;
	}
	public void setBannerTrgt(String bannerTrgt) {
		this.bannerTrgt = bannerTrgt;
	}
	public String getTagCn() {
		return tagCn;
	}
	public void setTagCn(String tagCn) {
		this.tagCn = tagCn;
	}
	public String getAtchmnflImage() {
		return atchmnflImage;
	}
	public void setAtchmnflImage(String atchmnflImage) {
		this.atchmnflImage = atchmnflImage;
	}
	public String getAtchmnflBcrnImage() {
		return atchmnflBcrnImage;
	}
	public void setAtchmnflBcrnImage(String atchmnflBcrnImage) {
		this.atchmnflBcrnImage = atchmnflBcrnImage;
	}
	public String getFlpth() {
		return flpth;
	}
	public void setFlpth(String flpth) {
		this.flpth = flpth;
	}
	public String getClassNm() {
		return classNm;
	}
	public void setClassNm(String classNm) {
		this.classNm = classNm;
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
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	public String getMainAt() {
		return mainAt;
	}
	public void setMainAt(String mainAt) {
		this.mainAt = mainAt;
	}

}
