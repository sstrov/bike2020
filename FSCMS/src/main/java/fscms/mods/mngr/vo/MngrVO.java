package fscms.mods.mngr.vo;

import java.io.Serializable;

public class MngrVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer mngrSn;			// 관리자_일련번호
	private Integer roleSn;			// 역할_일련번호
	private Integer accSn;			// 연결_일련번호
	private String  mngrId;			// 관리자_아이디
	private String  mngrPw;			// 관리자_비밀번호
	private String  mngrNm;			// 관리자_명
	private Integer lockCo;			// 잠금_개수
	private String  lockDe;			// 잠금_일자
	private String  mngrSalt;		// 관리자_솔트
	private String  mngrSsoAt;		// 관리자_SSO연동_여부
	private String  registDe;		// 등록_일자
	private String  updtDe;			// 수정_일자
	
	private Integer tCount;			// 총개수
	
	public Integer getMngrSn() {
		return mngrSn;
	}
	public void setMngrSn(Integer mngrSn) {
		this.mngrSn = mngrSn;
	}
	public Integer getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(Integer roleSn) {
		this.roleSn = roleSn;
	}
	public Integer getAccSn() {
		return accSn;
	}
	public void setAccSn(Integer accSn) {
		this.accSn = accSn;
	}
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public String getMngrPw() {
		return mngrPw;
	}
	public void setMngrPw(String mngrPw) {
		this.mngrPw = mngrPw;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public Integer getLockCo() {
		return lockCo;
	}
	public void setLockCo(Integer lockCo) {
		this.lockCo = lockCo;
	}
	public String getLockDe() {
		return lockDe;
	}
	public void setLockDe(String lockDe) {
		this.lockDe = lockDe;
	}
	public String getMngrSalt() {
		return mngrSalt;
	}
	public void setMngrSalt(String mngrSalt) {
		this.mngrSalt = mngrSalt;
	}
	public String getMngrSsoAt() {
		return mngrSsoAt;
	}
	public void setMngrSsoAt(String mngrSsoAt) {
		this.mngrSsoAt = mngrSsoAt;
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
