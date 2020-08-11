package fscms.mods.conectr.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConectrStatsMenuSearchVO extends ConectrStatsMenuVO {

	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
	
	private String  sc_wDateS = sdf.format(now).substring(0, 7) + "-01";
	private String  sc_wDateE = sdf.format(now);
	
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
