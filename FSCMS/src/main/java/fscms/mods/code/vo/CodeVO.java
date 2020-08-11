package fscms.mods.code.vo;

import java.io.Serializable;

public class CodeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  codeId;		// 코드_아이디
	private String  codeClId;	// 코드_분류_아이디
	private String  codeNm;		// 코드_명
	private String  codeDc;		// 코드_설명
	private String  useAt;		// 사용_여부
	private String  registDe;	// 등록_일자
	private String  updtDe;		// 수정_일자
	
	private Integer tCount;
	private String  codeClNm;
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeClId() {
		return codeClId;
	}
	public void setCodeClId(String codeClId) {
		this.codeClId = codeClId;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getCodeDc() {
		return codeDc;
	}
	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
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
	public String getCodeClNm() {
		return codeClNm;
	}
	public void setCodeClNm(String codeClNm) {
		this.codeClNm = codeClNm;
	}

}
