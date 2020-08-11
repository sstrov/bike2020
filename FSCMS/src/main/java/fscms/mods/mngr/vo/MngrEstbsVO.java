package fscms.mods.mngr.vo;

import java.io.Serializable;

public class MngrEstbsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ipIntrcpAt;		// 아이피_차단_여부

	public String getIpIntrcpAt() {
		return ipIntrcpAt;
	}
	public void setIpIntrcpAt(String ipIntrcpAt) {
		this.ipIntrcpAt = ipIntrcpAt;
	}

}
