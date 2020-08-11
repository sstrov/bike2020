package fscms.mods.atchmnfl.vo;

import java.io.Serializable;

public class AtchmnflManageVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer atchmnflManageSn;		// 첨부파일_관리_일련번호
	private Integer atchmnflSn;				// 첨부파일_일련번호
	private String  registDt;				// 등록_일자
	
	private String  atchmnflNm;
	private String  atchmnflRealNm;
	private String  atchmnflSize;
	private String  atchmnflTy;
	private String  atchmnflExtsn;
	private String  flpth;
	
	private Integer tCount;
	
	public Integer getAtchmnflManageSn() {
		return atchmnflManageSn;
	}
	public void setAtchmnflManageSn(Integer atchmnflManageSn) {
		this.atchmnflManageSn = atchmnflManageSn;
	}
	public Integer getAtchmnflSn() {
		return atchmnflSn;
	}
	public void setAtchmnflSn(Integer atchmnflSn) {
		this.atchmnflSn = atchmnflSn;
	}
	public String getRegistDt() {
		return registDt;
	}
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}
	public String getAtchmnflNm() {
		return atchmnflNm;
	}
	public void setAtchmnflNm(String atchmnflNm) {
		this.atchmnflNm = atchmnflNm;
	}
	public String getAtchmnflRealNm() {
		return atchmnflRealNm;
	}
	public void setAtchmnflRealNm(String atchmnflRealNm) {
		this.atchmnflRealNm = atchmnflRealNm;
	}
	public String getAtchmnflSize() {
		return atchmnflSize;
	}
	public void setAtchmnflSize(String atchmnflSize) {
		this.atchmnflSize = atchmnflSize;
	}
	public String getAtchmnflTy() {
		return atchmnflTy;
	}
	public void setAtchmnflTy(String atchmnflTy) {
		this.atchmnflTy = atchmnflTy;
	}
	public String getAtchmnflExtsn() {
		return atchmnflExtsn;
	}
	public void setAtchmnflExtsn(String atchmnflExtsn) {
		this.atchmnflExtsn = atchmnflExtsn;
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
