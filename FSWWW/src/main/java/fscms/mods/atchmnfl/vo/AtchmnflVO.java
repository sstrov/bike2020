package fscms.mods.atchmnfl.vo;

import java.io.Serializable;

public class AtchmnflVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer atchmnflSn;			// 첨부파일_일련번호
	private String  accTy;				// 연결_타입
	private String  accSn;				// 연결_일련번호
	private Integer atchmnflOrdr;		// 첨부파일_순서
	private String  atchmnflNm;			// 첨부파일_명
	private String  atchmnflRealNm;		// 첨부파일_실제_명
	private Integer atchmnflSize;		// 첨부파일_사이즈
	private String  atchmnflTy;			// 첨부파일_타입
	private String  atchmnflExtsn;		// 첨부파일_확장자
	private String  atchmnflRm;			// 첨부파일_비고
	private String  flpth;				// 파일경로
	private String  reprsntThumbAt;		// 대표_썸네일_여부
	private String  registDe;			// 등록_일자
	
	private Integer[] notKey;
	
	public Integer getAtchmnflSn() {
		return atchmnflSn;
	}
	public void setAtchmnflSn(Integer atchmnflSn) {
		this.atchmnflSn = atchmnflSn;
	}
	public String getAccTy() {
		return accTy;
	}
	public void setAccTy(String accTy) {
		this.accTy = accTy;
	}
	public String getAccSn() {
		return accSn;
	}
	public void setAccSn(String accSn) {
		this.accSn = accSn;
	}
	public Integer getAtchmnflOrdr() {
		return atchmnflOrdr;
	}
	public void setAtchmnflOrdr(Integer atchmnflOrdr) {
		this.atchmnflOrdr = atchmnflOrdr;
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
	public Integer getAtchmnflSize() {
		return atchmnflSize;
	}
	public void setAtchmnflSize(Integer atchmnflSize) {
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
	public String getAtchmnflRm() {
		return atchmnflRm;
	}
	public void setAtchmnflRm(String atchmnflRm) {
		this.atchmnflRm = atchmnflRm;
	}
	public String getFlpth() {
		return flpth;
	}
	public void setFlpth(String flpth) {
		this.flpth = flpth;
	}
	public String getReprsntThumbAt() {
		return reprsntThumbAt;
	}
	public void setReprsntThumbAt(String reprsntThumbAt) {
		this.reprsntThumbAt = reprsntThumbAt;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	public Integer[] getNotKey() {
		if(notKey == null) {
			return null;
		}
		Integer[] newArr = new Integer[notKey.length];
		System.arraycopy(notKey, 0, newArr, 0, notKey.length);
		return newArr;
	}
	public void setNotKey(Integer[] notKey) {
		Integer[] newArr = new Integer[notKey.length];
		System.arraycopy(notKey, 0, newArr, 0, notKey.length);
		this.notKey = newArr;
	}

}
