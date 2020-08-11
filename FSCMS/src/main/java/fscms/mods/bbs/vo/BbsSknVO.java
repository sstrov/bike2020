package fscms.mods.bbs.vo;

import java.io.Serializable;
import java.util.List;

public class BbsSknVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsSknSn;			// 게시판_스킨_일련번호
	private Integer bbsSknBestSn;		// 게시판_스킨_최상위_일련번호
	private Integer bbsSknUpperSn;		// 게시판_스킨_상위_일련번호
	private Integer bbsSknOrdr;			// 게시판_스킨_순서
	private Integer bbsSknDp;			// 게시판_스킨_깊이
	private String  bbsSknNm;			// 게시판_스킨_명
	private String  bbsSknCode;			// 게시판_스킨_코드
	private String  cmmnViewAt;			// 공통_뷰_여부
	private String  atchmnflImage;		// 첨부파일_이미지
	private String  flpth;				// 파일경로
	private String  useAt;				// 사용_여부
	private String  registDe;			// 등록_일자
	private String  updtDe;				// 수정_일자
	
	private List<BbsSknVO> childList;	// 하위 목록
	private String upperCode;			// 상위_코드
	
	public Integer getBbsSknSn() {
		return bbsSknSn;
	}
	public void setBbsSknSn(Integer bbsSknSn) {
		this.bbsSknSn = bbsSknSn;
	}
	public Integer getBbsSknBestSn() {
		return bbsSknBestSn;
	}
	public void setBbsSknBestSn(Integer bbsSknBestSn) {
		this.bbsSknBestSn = bbsSknBestSn;
	}
	public Integer getBbsSknUpperSn() {
		return bbsSknUpperSn;
	}
	public void setBbsSknUpperSn(Integer bbsSknUpperSn) {
		this.bbsSknUpperSn = bbsSknUpperSn;
	}
	public Integer getBbsSknOrdr() {
		return bbsSknOrdr;
	}
	public void setBbsSknOrdr(Integer bbsSknOrdr) {
		this.bbsSknOrdr = bbsSknOrdr;
	}
	public Integer getBbsSknDp() {
		return bbsSknDp;
	}
	public void setBbsSknDp(Integer bbsSknDp) {
		this.bbsSknDp = bbsSknDp;
	}
	public String getBbsSknNm() {
		return bbsSknNm;
	}
	public void setBbsSknNm(String bbsSknNm) {
		this.bbsSknNm = bbsSknNm;
	}
	public String getBbsSknCode() {
		return bbsSknCode;
	}
	public void setBbsSknCode(String bbsSknCode) {
		this.bbsSknCode = bbsSknCode;
	}
	public String getCmmnViewAt() {
		return cmmnViewAt;
	}
	public void setCmmnViewAt(String cmmnViewAt) {
		this.cmmnViewAt = cmmnViewAt;
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
	public List<BbsSknVO> getChildList() {
		return childList;
	}
	public void setChildList(List<BbsSknVO> childList) {
		this.childList = childList;
	}
	public String getUpperCode() {
		return upperCode;
	}
	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}

}
