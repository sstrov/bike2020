package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsThumbVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsThumbSn;		// 게시판_썸네일_일련번호
	private Integer bbsEstbsSn;		// 게시판_설정_일련번호
	private String  thumbNm;		// 썸네일_명
	private Integer widthSize;		// 가로_사이즈
	private Integer vrticlSize;		// 세로_사이즈
	
	private String[] sc_keyArr;
	
	public Integer getBbsThumbSn() {
		return bbsThumbSn;
	}
	public void setBbsThumbSn(Integer bbsThumbSn) {
		this.bbsThumbSn = bbsThumbSn;
	}
	public Integer getBbsEstbsSn() {
		return bbsEstbsSn;
	}
	public void setBbsEstbsSn(Integer bbsEstbsSn) {
		this.bbsEstbsSn = bbsEstbsSn;
	}
	public String getThumbNm() {
		return thumbNm;
	}
	public void setThumbNm(String thumbNm) {
		this.thumbNm = thumbNm;
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

}
