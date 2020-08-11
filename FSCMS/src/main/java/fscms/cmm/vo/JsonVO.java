package fscms.cmm.vo;

import java.io.Serializable;

public class JsonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String state;
	private String msg;
	private String key;
	
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

}
