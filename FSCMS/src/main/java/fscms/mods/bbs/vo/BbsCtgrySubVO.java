package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsCtgrySubVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsCtgrySubSn;		// 게시판_카테고리_일련번호
	private Integer bbsCtgrySn;		// 게시판_설정_일련번호
	private String  ctgrySubNm;		// 카테고리_명
	
	private String[] sc_keyArr;
	
	public Integer getBbsCtgrySn() {
		return bbsCtgrySn;
	}
	public void setBbsCtgrySn(Integer bbsCtgrySn) {
		this.bbsCtgrySn = bbsCtgrySn;
	}
	public Integer getBbsCtgrySubSn() {
		return bbsCtgrySubSn;
	}
	public void setBbsCtgrySubSn(Integer bbsCtgrySubSn) {
		this.bbsCtgrySubSn = bbsCtgrySubSn;
	}
	public String getCtgrySubNm() {
		return ctgrySubNm;
	}
	public void setCtgrySubNm(String ctgrySubNm) {
		this.ctgrySubNm = ctgrySubNm;
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
