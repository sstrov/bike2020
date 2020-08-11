package fscms.mods.edu.vo;

import java.io.Serializable;

public class EduVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer eduSn;
	private String  eduHost;
	private String  eduNm;
	private String  eduTarget;
	private String  eduDay;
	private String  eduPlace;
	private String  eduTel;
	private String  registDe;
	private String  registId;
	private String  updtDe;
	private Integer  hostOrdr;
	
	private Integer tCount;

	public Integer getEduSn() {
		return eduSn;
	}

	public void setEduSn(Integer eduSn) {
		this.eduSn = eduSn;
	}

	public String getEduHost() {
		return eduHost;
	}

	public void setEduHost(String eduHost) {
		this.eduHost = eduHost;
	}

	public String getEduNm() {
		return eduNm;
	}

	public void setEduNm(String eduNm) {
		this.eduNm = eduNm;
	}

	public String getEduTarget() {
		return eduTarget;
	}

	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}

	public String getEduDay() {
		return eduDay;
	}

	public void setEduDay(String eduDay) {
		this.eduDay = eduDay;
	}

	public String getEduTel() {
		return eduTel;
	}

	public void setEduTel(String eduTel) {
		this.eduTel = eduTel;
	}

	public String getRegistDe() {
		return registDe;
	}

	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
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

	public String getEduPlace() {
		return eduPlace;
	}

	public void setEduPlace(String eduPlace) {
		this.eduPlace = eduPlace;
	}

	public Integer getHostOrdr() {
		return hostOrdr;
	}

	public void setHostOrdr(Integer hostOrdr) {
		this.hostOrdr = hostOrdr;
	}

}
