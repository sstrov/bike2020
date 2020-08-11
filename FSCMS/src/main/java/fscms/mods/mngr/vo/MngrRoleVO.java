package fscms.mods.mngr.vo;

import java.io.Serializable;

public class MngrRoleVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer roleSn;			// 역할_일련번호
	private String  roleNm;			// 역할_명
	private Integer roleOrdr;		// 역할_순서
	private String  registDe;		// 등록_일자
	private String  updtDe;			// 수정_일자
	
	private Integer tCount;			// 총개수
	
	public Integer getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(Integer roleSn) {
		this.roleSn = roleSn;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	public Integer getRoleOrdr() {
		return roleOrdr;
	}
	public void setRoleOrdr(Integer roleOrdr) {
		this.roleOrdr = roleOrdr;
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
