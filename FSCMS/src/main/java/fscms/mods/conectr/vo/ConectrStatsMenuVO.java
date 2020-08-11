package fscms.mods.conectr.vo;

import java.io.Serializable;

public class ConectrStatsMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer conectrStatsMenuSn;	// 접속자_통계_메뉴_일련번호
	private String  menuSn;				// 메뉴_일련번호
	private Integer statsYear;			// 통계_년도
	private Integer statsMonth;			// 통계_월
	private Integer statsDate;			// 통계_일
	private Integer statsHour;			// 통계_시
	private Integer statsMin;			// 통계_분
	private Integer statsSec;			// 통계_초
	private String  statsWeek;			// 통계_주
	private String  statsOpersysm;		// 통계_운영체제
	private String  statsLocale;		// 통계_국가언어
	private String  statsRef;			// 통계_리퍼러
	private String  statsBrwsrNm;		// 통계_브라우저_명
	private String  statsBrwsrVer;		// 통계_브라우저_버전
	private String  statsPltfom;		// 통계_플랫폼
	private String  registIp;			// 등록_아이피
	private String  registDe;			// 등록_일자
	
	private Integer tCount;
	private Integer tPer;
	
	public Integer getConectrStatsMenuSn() {
		return conectrStatsMenuSn;
	}
	public void setConectrStatsMenuSn(Integer conectrStatsMenuSn) {
		this.conectrStatsMenuSn = conectrStatsMenuSn;
	}
	public String getMenuSn() {
		return menuSn;
	}
	public void setMenuSn(String menuSn) {
		this.menuSn = menuSn;
	}
	public Integer getStatsYear() {
		return statsYear;
	}
	public void setStatsYear(Integer statsYear) {
		this.statsYear = statsYear;
	}
	public Integer getStatsMonth() {
		return statsMonth;
	}
	public void setStatsMonth(Integer statsMonth) {
		this.statsMonth = statsMonth;
	}
	public Integer getStatsDate() {
		return statsDate;
	}
	public void setStatsDate(Integer statsDate) {
		this.statsDate = statsDate;
	}
	public Integer getStatsHour() {
		return statsHour;
	}
	public void setStatsHour(Integer statsHour) {
		this.statsHour = statsHour;
	}
	public Integer getStatsMin() {
		return statsMin;
	}
	public void setStatsMin(Integer statsMin) {
		this.statsMin = statsMin;
	}
	public Integer getStatsSec() {
		return statsSec;
	}
	public void setStatsSec(Integer statsSec) {
		this.statsSec = statsSec;
	}
	public String getStatsWeek() {
		return statsWeek;
	}
	public void setStatsWeek(String statsWeek) {
		this.statsWeek = statsWeek;
	}
	public String getStatsOpersysm() {
		return statsOpersysm;
	}
	public void setStatsOpersysm(String statsOpersysm) {
		this.statsOpersysm = statsOpersysm;
	}
	public String getStatsLocale() {
		return statsLocale;
	}
	public void setStatsLocale(String statsLocale) {
		this.statsLocale = statsLocale;
	}
	public String getStatsRef() {
		return statsRef;
	}
	public void setStatsRef(String statsRef) {
		this.statsRef = statsRef;
	}
	public String getStatsBrwsrNm() {
		return statsBrwsrNm;
	}
	public void setStatsBrwsrNm(String statsBrwsrNm) {
		this.statsBrwsrNm = statsBrwsrNm;
	}
	public String getStatsBrwsrVer() {
		return statsBrwsrVer;
	}
	public void setStatsBrwsrVer(String statsBrwsrVer) {
		this.statsBrwsrVer = statsBrwsrVer;
	}
	public String getStatsPltfom() {
		return statsPltfom;
	}
	public void setStatsPltfom(String statsPltfom) {
		this.statsPltfom = statsPltfom;
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
	public Integer gettPer() {
		return tPer;
	}
	public void settPer(Integer tPer) {
		this.tPer = tPer;
	}

}
