package fscms.mods.code.vo;

import java.io.Serializable;

public class CodeClVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  codeClId;	// 코드_분류_아이디
	private String  codeClNm;	// 코드_분류_명
	private String  codeClDc;	// 코드_분류_설명
	private String  useAt;		// 사용_여부
	private String  registDe;	// 등록_일자
	private String  updtDe;		// 수정_일자
	
	private Integer tCount;

	public String getCodeClId() {
		return codeClId;
	}
	public void setCodeClId(String codeClId) {
		this.codeClId = codeClId;
	}
	public String getCodeClNm() {
		return codeClNm;
	}
	public void setCodeClNm(String codeClNm) {
		this.codeClNm = codeClNm;
	}
	public String getCodeClDc() {
		return codeClDc;
	}
	public void setCodeClDc(String codeClDc) {
		this.codeClDc = codeClDc;
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

}
