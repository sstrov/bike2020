package fscms.mods.site.vo;

import java.io.Serializable;

public class SiteVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  siteNm;				// 사이트_명
	private Integer roleSn;				// 역할_일련번호 (사용자_역할 연결 : 기본 역할 지정)
	private String  openDe;				// 오픈_일자
	private String  dplctLoginAt;		// 중복_로그인_여부 (Y:중복 불가, N:중복 가능)
	private Integer loginFailrLockCo;	// 로그인_실패_잠금_개수 (0일때는 무제한)
	private String  actvtyAt;			// 활성_여부
	private String  snsUseAt;			// SNS_사용_여부
	private String  registDe;			// 등록_일자
	private String  updtDe;				// 수정_일자
	
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public Integer getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(Integer roleSn) {
		this.roleSn = roleSn;
	}
	public String getOpenDe() {
		return openDe;
	}
	public void setOpenDe(String openDe) {
		this.openDe = openDe;
	}
	public String getDplctLoginAt() {
		return dplctLoginAt;
	}
	public void setDplctLoginAt(String dplctLoginAt) {
		this.dplctLoginAt = dplctLoginAt;
	}
	public Integer getLoginFailrLockCo() {
		return loginFailrLockCo;
	}
	public void setLoginFailrLockCo(Integer loginFailrLockCo) {
		this.loginFailrLockCo = loginFailrLockCo;
	}
	public String getActvtyAt() {
		return actvtyAt;
	}
	public void setActvtyAt(String actvtyAt) {
		this.actvtyAt = actvtyAt;
	}
	public String getSnsUseAt() {
		return snsUseAt;
	}
	public void setSnsUseAt(String snsUseAt) {
		this.snsUseAt = snsUseAt;
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

}
