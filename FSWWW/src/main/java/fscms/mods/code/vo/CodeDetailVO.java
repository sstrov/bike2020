package fscms.mods.code.vo;

import java.io.Serializable;

public class CodeDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codeDetailSn;		// 코드_상세_일련번호
	private String  codeId;				// 코드_아이디
	private String  codeDetailId;		// 코드_상세_아이디
	private String  codeDetailNm;		// 코드_상세_명
	private String  codeDetailDc;		// 코드_상세_설명
	private Integer codeDetailOrdr;		// 코드_상세_순서
	private String  deleteAt;			// 삭제_여부
	private String  registDe;			// 등록_일자
	private String  updtDe;				// 수정_일자
	
	private String[] keyArr;
	
	@SuppressWarnings("unused")
	private String  nm;		// 단축 명
	@SuppressWarnings("unused")
	private String  cd;		// 단축 코드
	
	public Integer getCodeDetailSn() {
		return codeDetailSn;
	}
	public void setCodeDetailSn(Integer codeDetailSn) {
		this.codeDetailSn = codeDetailSn;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeDetailId() {
		return codeDetailId;
	}
	public void setCodeDetailId(String codeDetailId) {
		this.codeDetailId = codeDetailId;
	}
	public String getCodeDetailNm() {
		return codeDetailNm;
	}
	public void setCodeDetailNm(String codeDetailNm) {
		this.codeDetailNm = codeDetailNm;
	}
	public String getCodeDetailDc() {
		return codeDetailDc;
	}
	public void setCodeDetailDc(String codeDetailDc) {
		this.codeDetailDc = codeDetailDc;
	}
	public Integer getCodeDetailOrdr() {
		return codeDetailOrdr;
	}
	public void setCodeDetailOrdr(Integer codeDetailOrdr) {
		this.codeDetailOrdr = codeDetailOrdr;
	}
	public String getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
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
	public String[] getKeyArr() {
		if(keyArr == null) {
			return null;
		}
		String[] newArr = new String[keyArr.length];
		System.arraycopy(keyArr, 0, newArr, 0, keyArr.length);
		return newArr;
	}
	public void setKeyArr(String[] keyArr) {
		String[] newArr = new String[keyArr.length];
		System.arraycopy(keyArr, 0, newArr, 0, keyArr.length);
		this.keyArr = newArr;
	}
	public String getNm() {
		return codeDetailNm;
	}
	public String getCd() {
		return codeDetailId;
	}

}
