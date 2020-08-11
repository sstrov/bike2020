package fscms.mods.mngr.vo;

import java.io.Serializable;
import java.util.List;

public class MngrMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  menuSn;			// 메뉴_일련번호
	private Integer accSn;			// 연결_일련번호
	private String  menuBestSn;		// 메뉴_최상위_일련번호
	private String  menuUpperSn;	// 메뉴_상위_일련번호
	private Integer menuOrdr;		// 메뉴_순서
	private Integer menuDp;			// 메뉴_깊이
	private String  menuNm;			// 메뉴_명
	private String  menuCnncTy;		// 메뉴_연결_타입
	private String  menuItnadr;		// 메뉴_인터넷주소
	private String  menuItnadrTy;	// 메뉴_인터넷주소_타입
	private String  menuTrgt;		// 메뉴_타겟
	private String  menuParamtr;	// 메뉴_파라미터
	private String  actvtyAt;		// 활성_여부
	private String  useAt;			// 사용_여부
	private String  menuLink;		// 메뉴_링크
	private String  registDe;		// 등록_일자
	private String  updtDe;			// 수정_일자
	
	private List<MngrMenuVO> childList;		// 하위 목록
	private String           authorRedng;	// 권한_읽기
	private String           authorRegist;	// 권한_등록 (쓰기권한)
	
	private String  accNm;
	private Integer sc_roleSn;		// 권한_일련번호 (검색시)
	private Integer menuDpOver;		// 입력한_깊이보다 깊은 데이터 조회 (검색시)
	
	public String getMenuSn() {
		return menuSn;
	}
	public void setMenuSn(String menuSn) {
		this.menuSn = menuSn;
	}
	public Integer getAccSn() {
		return accSn;
	}
	public void setAccSn(Integer accSn) {
		this.accSn = accSn;
	}
	public String getMenuBestSn() {
		return menuBestSn;
	}
	public void setMenuBestSn(String menuBestSn) {
		this.menuBestSn = menuBestSn;
	}
	public String getMenuUpperSn() {
		return menuUpperSn;
	}
	public void setMenuUpperSn(String menuUpperSn) {
		this.menuUpperSn = menuUpperSn;
	}
	public Integer getMenuOrdr() {
		return menuOrdr;
	}
	public void setMenuOrdr(Integer menuOrdr) {
		this.menuOrdr = menuOrdr;
	}
	public Integer getMenuDp() {
		return menuDp;
	}
	public void setMenuDp(Integer menuDp) {
		this.menuDp = menuDp;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuCnncTy() {
		return menuCnncTy;
	}
	public void setMenuCnncTy(String menuCnncTy) {
		this.menuCnncTy = menuCnncTy;
	}
	public String getMenuItnadr() {
		return menuItnadr;
	}
	public void setMenuItnadr(String menuItnadr) {
		this.menuItnadr = menuItnadr;
	}
	public String getMenuItnadrTy() {
		return menuItnadrTy;
	}
	public void setMenuItnadrTy(String menuItnadrTy) {
		this.menuItnadrTy = menuItnadrTy;
	}
	public String getMenuTrgt() {
		return menuTrgt;
	}
	public void setMenuTrgt(String menuTrgt) {
		this.menuTrgt = menuTrgt;
	}
	public String getMenuParamtr() {
		return menuParamtr;
	}
	public void setMenuParamtr(String menuParamtr) {
		this.menuParamtr = menuParamtr;
	}
	public String getActvtyAt() {
		return actvtyAt;
	}
	public void setActvtyAt(String actvtyAt) {
		this.actvtyAt = actvtyAt;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
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
	public List<MngrMenuVO> getChildList() {
		return childList;
	}
	public void setChildList(List<MngrMenuVO> childList) {
		this.childList = childList;
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
	public String getAccNm() {
		return accNm;
	}
	public void setAccNm(String accNm) {
		this.accNm = accNm;
	}
	public Integer getSc_roleSn() {
		return sc_roleSn;
	}
	public void setSc_roleSn(Integer sc_roleSn) {
		this.sc_roleSn = sc_roleSn;
	}
	public Integer getMenuDpOver() {
		return menuDpOver;
	}
	public void setMenuDpOver(Integer menuDpOver) {
		this.menuDpOver = menuDpOver;
	}

}
