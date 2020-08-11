package fscms.mods.conectr.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConectrStatsSearchVO extends ConectrStatsVO {

	private static final long serialVersionUID = 1L;
	
	private String sc_year;
	private String sc_month;
	private String sc_date;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
	
	private String  sc_wDateS = sdf.format(now).substring(0, 7) + "-01";
	private String  sc_wDateE = sdf.format(now);
	
	public String getSc_year() {
		return sc_year;
	}
	public void setSc_year(String sc_year) {
		this.sc_year = sc_year;
	}
	public String getSc_month() {
		return sc_month;
	}
	public void setSc_month(String sc_month) {
		this.sc_month = sc_month;
	}
	public String getSc_date() {
		return sc_date;
	}
	public void setSc_date(String sc_date) {
		this.sc_date = sc_date;
	}
	public String getSc_wDateS() {
		return sc_wDateS;
	}
	public void setSc_wDateS(String sc_wDateS) {
		this.sc_wDateS = sc_wDateS;
	}
	public String getSc_wDateE() {
		return sc_wDateE;
	}
	public void setSc_wDateE(String sc_wDateE) {
		this.sc_wDateE = sc_wDateE;
	}

}
