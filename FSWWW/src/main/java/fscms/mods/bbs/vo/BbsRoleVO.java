package fscms.mods.bbs.vo;

import java.io.Serializable;

public class BbsRoleVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bbsRoleSn;		// 게시판_역할_일련번호
	private Integer bbsEstbsSn;		// 게시판_설정_일련번호
	private Integer roleSn;			// 역할_일련번호
	private String  roleTy;			// 역할_타입
	
	public Integer getBbsRoleSn() {
		return bbsRoleSn;
	}
	public void setBbsRoleSn(Integer bbsRoleSn) {
		this.bbsRoleSn = bbsRoleSn;
	}
	public Integer getBbsEstbsSn() {
		return bbsEstbsSn;
	}
	public void setBbsEstbsSn(Integer bbsEstbsSn) {
		this.bbsEstbsSn = bbsEstbsSn;
	}
	public Integer getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(Integer roleSn) {
		this.roleSn = roleSn;
	}
	public String getRoleTy() {
		return roleTy;
	}
	public void setRoleTy(String roleTy) {
		this.roleTy = roleTy;
	}

}
