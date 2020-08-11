package fscms.mods.road.vo;

import java.io.Serializable;

public class RoadVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 1.그랜드슬램	G00
	 * 2.국토종주 		C00
	 * 3.4대강 		400
	 * 4.구간별		
	 * roadCd			authCode
	 * 001 아라		 
	 * 002 남한강  		R05
	 * 003 낙동강 			R04
	 * 004 새재 			S01
	 * 005 금강 			R02
	 * 006 한강 			R01
	 * 007 영산강 			R03
	 * 008 북한강 			R06
	 * 009 섬진강 			R07
	 * 010 오천 			O01
	 * 011 동해안(강원) 	D01
	 * 012 제주 			J01
	 * 013 동해안(경북)		D02
	 */
	private Integer roadSn;
	private String roadCd;
	private String roadNm;
	private String roadOrdr;
	private String useAt;
	
	private Integer cdSn;
	private String authCd;
	private String authNm;
	private String authType;
	private Integer authOrdr;
	
	private String sc;
	private String sw;
	private String sub;
	private String checked;
	private Integer userSn;
	private String noteSn;
	private String disabled;
	
	public Integer getRoadSn() {
		return roadSn;
	}
	public void setRoadSn(Integer roadSn) {
		this.roadSn = roadSn;
	}
	public String getRoadCd() {
		return roadCd;
	}
	public void setRoadCd(String roadCd) {
		this.roadCd = roadCd;
	}
	public String getRoadNm() {
		return roadNm;
	}
	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getRoadOrdr() {
		return roadOrdr;
	}
	public void setRoadOrdr(String roadOrdr) {
		this.roadOrdr = roadOrdr;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public Integer getCdSn() {
		return cdSn;
	}
	public void setCdSn(Integer cdSn) {
		this.cdSn = cdSn;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public String getAuthNm() {
		return authNm;
	}
	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}
	public Integer getAuthOrdr() {
		return authOrdr;
	}
	public void setAuthOrdr(Integer authOrdr) {
		this.authOrdr = authOrdr;
	}
	public String getSw() {
		return sw;
	}
	public void setSw(String sw) {
		this.sw = sw;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getNoteSn() {
		return noteSn;
	}
	public void setNoteSn(String noteSn) {
		this.noteSn = noteSn;
	}
	public Integer getUserSn() {
		return userSn;
	}
	public void setUserSn(Integer userSn) {
		this.userSn = userSn;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
}
