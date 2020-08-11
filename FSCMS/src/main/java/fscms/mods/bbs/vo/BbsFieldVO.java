package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsFieldVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsFieldSn;				// 게시판_필드_일련번호
	private Integer bbsEstbsSn;				// 게시판_설정_일련번호
	private String  bbsFieldCode;			// 게시판_필드_코드
	private String  bbsFieldNm;				// 게시판_필드_명
	private Integer listOrdr;				// 목록_순서
	private Integer viewOrdr;				// 뷰_순서
	private String  fieldTy;				// 필드_타입
	private String  fieldTyActvtyAt;		// 필드_타입_활성_여부
	private String  fieldTyText;			// 필드_타입_텍스트
	private String  fieldTyTextActvtyAt;	// 필드_타입_텍스트_활성_여부
	private String  fieldUseAt;				// 필드_사용_여부
	private String  fieldUseActvtyAt;		// 필드_사용_활성_여부
	private String  fieldEssntlAt;			// 필드_필수_여부
	private String  fieldEssntlActvtyAt;	// 필드_필수_활성_여부
	private String  fieldSearchAt;			// 필드_검색_여부
	private String  fieldSearchActvtyAt;	// 필드_검색_활성_여부
	private String  fieldListAt;			// 필드_목록_여부
	private String  fieldListActvtyAt;		// 필드_목록_활성_여부
	private Integer fieldListSize;			// 필드_목록_사이즈
	private String  fieldListStyle;			// 필드_목록_스타일
	private String  fieldViewAt;			// 필드_뷰_여부
	private String  fieldViewActvtyAt;		// 필드_뷰_활성_여부
	
	private String[] sc_keyArr;
	private String   orderBy;
	
	public Integer getBbsFieldSn() {
		return bbsFieldSn;
	}
	public void setBbsFieldSn(Integer bbsFieldSn) {
		this.bbsFieldSn = bbsFieldSn;
	}
	public Integer getBbsEstbsSn() {
		return bbsEstbsSn;
	}
	public void setBbsEstbsSn(Integer bbsEstbsSn) {
		this.bbsEstbsSn = bbsEstbsSn;
	}
	public String getBbsFieldCode() {
		return bbsFieldCode;
	}
	public void setBbsFieldCode(String bbsFieldCode) {
		this.bbsFieldCode = bbsFieldCode;
	}
	public String getBbsFieldNm() {
		return bbsFieldNm;
	}
	public void setBbsFieldNm(String bbsFieldNm) {
		this.bbsFieldNm = bbsFieldNm;
	}
	public Integer getListOrdr() {
		return listOrdr;
	}
	public void setListOrdr(Integer listOrdr) {
		this.listOrdr = listOrdr;
	}
	public Integer getViewOrdr() {
		return viewOrdr;
	}
	public void setViewOrdr(Integer viewOrdr) {
		this.viewOrdr = viewOrdr;
	}
	public String getFieldTy() {
		return fieldTy;
	}
	public void setFieldTy(String fieldTy) {
		this.fieldTy = fieldTy;
	}
	public String getFieldTyActvtyAt() {
		return fieldTyActvtyAt;
	}
	public void setFieldTyActvtyAt(String fieldTyActvtyAt) {
		this.fieldTyActvtyAt = fieldTyActvtyAt;
	}
	public String getFieldTyText() {
		return fieldTyText;
	}
	public void setFieldTyText(String fieldTyText) {
		this.fieldTyText = fieldTyText;
	}
	public String getFieldTyTextActvtyAt() {
		return fieldTyTextActvtyAt;
	}
	public void setFieldTyTextActvtyAt(String fieldTyTextActvtyAt) {
		this.fieldTyTextActvtyAt = fieldTyTextActvtyAt;
	}
	public String getFieldUseAt() {
		return fieldUseAt;
	}
	public void setFieldUseAt(String fieldUseAt) {
		this.fieldUseAt = fieldUseAt;
	}
	public String getFieldUseActvtyAt() {
		return fieldUseActvtyAt;
	}
	public void setFieldUseActvtyAt(String fieldUseActvtyAt) {
		this.fieldUseActvtyAt = fieldUseActvtyAt;
	}
	public String getFieldEssntlAt() {
		return fieldEssntlAt;
	}
	public void setFieldEssntlAt(String fieldEssntlAt) {
		this.fieldEssntlAt = fieldEssntlAt;
	}
	public String getFieldEssntlActvtyAt() {
		return fieldEssntlActvtyAt;
	}
	public void setFieldEssntlActvtyAt(String fieldEssntlActvtyAt) {
		this.fieldEssntlActvtyAt = fieldEssntlActvtyAt;
	}
	public String getFieldSearchAt() {
		return fieldSearchAt;
	}
	public void setFieldSearchAt(String fieldSearchAt) {
		this.fieldSearchAt = fieldSearchAt;
	}
	public String getFieldSearchActvtyAt() {
		return fieldSearchActvtyAt;
	}
	public void setFieldSearchActvtyAt(String fieldSearchActvtyAt) {
		this.fieldSearchActvtyAt = fieldSearchActvtyAt;
	}
	public String getFieldListAt() {
		return fieldListAt;
	}
	public void setFieldListAt(String fieldListAt) {
		this.fieldListAt = fieldListAt;
	}
	public String getFieldListActvtyAt() {
		return fieldListActvtyAt;
	}
	public void setFieldListActvtyAt(String fieldListActvtyAt) {
		this.fieldListActvtyAt = fieldListActvtyAt;
	}
	public Integer getFieldListSize() {
		return fieldListSize;
	}
	public void setFieldListSize(Integer fieldListSize) {
		this.fieldListSize = fieldListSize;
	}
	public String getFieldListStyle() {
		return fieldListStyle;
	}
	public void setFieldListStyle(String fieldListStyle) {
		this.fieldListStyle = fieldListStyle;
	}
	public String getFieldViewAt() {
		return fieldViewAt;
	}
	public void setFieldViewAt(String fieldViewAt) {
		this.fieldViewAt = fieldViewAt;
	}
	public String getFieldViewActvtyAt() {
		return fieldViewActvtyAt;
	}
	public void setFieldViewActvtyAt(String fieldViewActvtyAt) {
		this.fieldViewActvtyAt = fieldViewActvtyAt;
	}
	public String[] getSc_keyArr() {
		if(sc_keyArr == null) {
			return null;
		}
		String[] newArr = new String[sc_keyArr.length];
		System.arraycopy(sc_keyArr, 0, newArr, 0, sc_keyArr.length);
		return newArr;
	}
	public void setSc_keyArr(String[] sc_keyArr) {
		String[] newArr = new String[sc_keyArr.length];
		System.arraycopy(sc_keyArr, 0, newArr, 0, sc_keyArr.length);
		this.sc_keyArr = newArr;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
