package fscms.mods.popup.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class PopupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer popupSn;		// 팝업_일련번호
	private String  popupNm;		// 팝업_명
	private String  popupCn;		// 팝업_내용
	private String  popupTy;		// 팝업_타입
	private Integer widthLc;		// 가로_위치
	private Integer vrticlLc;		// 세로_위치
	private Integer widthSize;		// 가로_사이즈
	private Integer vrticlSize;		// 세로_사이즈
	private String  popupBgnde;		// 팝업_시작일
	private String  popupEndde;		// 팝업_종료일
	private String  atmcSizeAt;		// 자동_사이즈_여부
	private String  useAt;			// 사용_여부
	private String  registDe;		// 등록_일자
	private String  updtDe;			// 수정_일자
	
	private Integer tCount;			// 총개수
	private String  mainAt;			// 메인 호출 여부
	
	public Integer getPopupSn() {
		return popupSn;
	}
	public void setPopupSn(Integer popupSn) {
		this.popupSn = popupSn;
	}
	public String getPopupNm() {
		return popupNm;
	}
	public void setPopupNm(String popupNm) {
		this.popupNm = popupNm;
	}
	public String getPopupCn() {
		return popupCn;
	}
	public void setPopupCn(String popupCn) {
		this.popupCn = popupCn;
	}
	public String getPopupTy() {
		return popupTy;
	}
	public void setPopupTy(String popupTy) {
		this.popupTy = popupTy;
	}
	public Integer getWidthLc() {
		return widthLc;
	}
	public void setWidthLc(Integer widthLc) {
		this.widthLc = widthLc;
	}
	public Integer getVrticlLc() {
		return vrticlLc;
	}
	public void setVrticlLc(Integer vrticlLc) {
		this.vrticlLc = vrticlLc;
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
	public String getPopupBgnde() {
		if(StringUtils.isNotEmpty(popupBgnde)) {
			return popupBgnde.substring(0, 16);
		}
		return popupBgnde;
	}
	public void setPopupBgnde(String popupBgnde) {
		this.popupBgnde = popupBgnde;
	}
	public String getPopupEndde() {
		if(StringUtils.isNotEmpty(popupEndde)) {
			return popupEndde.substring(0, 16);
		}
		return popupEndde;
	}
	public void setPopupEndde(String popupEndde) {
		this.popupEndde = popupEndde;
	}
	public String getAtmcSizeAt() {
		return atmcSizeAt;
	}
	public void setAtmcSizeAt(String atmcSizeAt) {
		this.atmcSizeAt = atmcSizeAt;
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
