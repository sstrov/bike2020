package fscms.cmm.vo;

import java.io.Serializable;

public class RoleVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String powR;	// 읽기_권한
	private String powW;	// 쓰기_권한
	
	public String getPowR() {
		return powR;
	}
	public void setPowR(String powR) {
		this.powR = powR;
	}
	public String getPowW() {
		return powW;
	}
	public void setPowW(String powW) {
		this.powW = powW;
	}

}
