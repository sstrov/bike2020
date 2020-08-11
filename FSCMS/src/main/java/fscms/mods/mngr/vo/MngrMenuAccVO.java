package fscms.mods.mngr.vo;

import java.io.Serializable;

public class MngrMenuAccVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer menuAccSn;		// 메뉴_연결_일련번호
	private String  menuSn;			// 메뉴_일련번호
	private Integer roleSn;			// 역할_일련번호
	private String  authorRedng;	// 권한_읽기
	private String  authorRegist;	// 권한_등록
	
	public Integer getMenuAccSn() {
		return menuAccSn;
	}
	public void setMenuAccSn(Integer menuAccSn) {
		this.menuAccSn = menuAccSn;
	}
	public String getMenuSn() {
		return menuSn;
	}
	public void setMenuSn(String menuSn) {
		this.menuSn = menuSn;
	}
	public Integer getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(Integer roleSn) {
		this.roleSn = roleSn;
	}
	public String getAuthorRedng() {
		return authorRedng;
	}
	public void setAuthorRedng(String authorRedng) {
		this.authorRedng = authorRedng;
	}
	public String getAuthorRegist() {
		return authorRegist;
	}
	public void setAuthorRegist(String authorRegist) {
		this.authorRegist = authorRegist;
	}

}
