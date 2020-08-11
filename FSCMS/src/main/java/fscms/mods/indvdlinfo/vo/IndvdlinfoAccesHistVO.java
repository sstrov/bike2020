package fscms.mods.indvdlinfo.vo;

import java.io.Serializable;

public class IndvdlinfoAccesHistVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer accesHistSn;	// 접근_이력_일련번호
	private Integer dataChghstSn;	// 데이터_변경이력_일련번호
	private String  userId;			// 사용자_아이디
	private String  mngrId;			// 관리자_아이디
	private String  accesHistCn;	// 접근_이력_내용
	private String  registPltfom;	// 등록_플랫폼
	private String  registBrwsr;	// 등록_브라우저
	private String  registIp;		// 등록_아이디
	private String  registDe;		// 등록_일자
	
	private Integer tCount;			// 총개수
	
	
	public Integer getAccesHistSn() {
		return accesHistSn;
	}
	public void setAccesHistSn(Integer accesHistSn) {
		this.accesHistSn = accesHistSn;
	}
	public Integer getDataChghstSn() {
		return dataChghstSn;
	}
	public void setDataChghstSn(Integer dataChghstSn) {
		this.dataChghstSn = dataChghstSn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public String getAccesHistCn() {
		return accesHistCn;
	}
	public void setAccesHistCn(String accesHistCn) {
		this.accesHistCn = accesHistCn;
	}
	public String getRegistPltfom() {
		return registPltfom;
	}
	public void setRegistPltfom(String registPltfom) {
		this.registPltfom = registPltfom;
	}
	public String getRegistBrwsr() {
		return registBrwsr;
	}
	public void setRegistBrwsr(String registBrwsr) {
		this.registBrwsr = registBrwsr;
	}
	public String getRegistIp() {
		return registIp;
	}
	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}

}
