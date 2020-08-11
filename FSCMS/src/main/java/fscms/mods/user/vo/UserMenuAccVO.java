package fscms.mods.user.vo;

import java.io.Serializable;

public class UserMenuAccVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer menuAccSn;	// 메뉴_연결_일련번호
	private String  menuSn;		// 메뉴_일련번호
	private Integer roleSn;		// 역할_일련번호
	
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

}
