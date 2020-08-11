package fscms.cmm.vo;

import java.io.Serializable;
import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public class JsonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String state;
	private String msg;
	private String key;
	
	private List<EgovMap> headList;
	private List<EgovMap> egovList;
	private List<EgovMap> subList;
	private String        analsTyNm;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<EgovMap> getHeadList() {
		return headList;
	}
	public void setHeadList(List<EgovMap> headList) {
		this.headList = headList;
	}
	public List<EgovMap> getEgovList() {
		return egovList;
	}
	public void setEgovList(List<EgovMap> egovList) {
		this.egovList = egovList;
	}
	public List<EgovMap> getSubList() {
		return subList;
	}
	public void setSubList(List<EgovMap> subList) {
		this.subList = subList;
	}
	public String getAnalsTyNm() {
		return analsTyNm;
	}
	public void setAnalsTyNm(String analsTyNm) {
		this.analsTyNm = analsTyNm;
	}

}
