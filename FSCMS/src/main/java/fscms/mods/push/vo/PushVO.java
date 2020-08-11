package fscms.mods.push.vo;

import java.io.Serializable;

public class PushVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer pushSn;
	private String  pushSj;
	private String  pushCn;
	private String  pushType;
	private String  registId;
	private String  registDe;
	private String  updtDe;
	
	private Integer tCount;			// 총개수
	
	public Integer getPushSn() {
		return pushSn;
	}
	public void setPushSn(Integer pushSn) {
		this.pushSn = pushSn;
	}
	public String getPushSj() {
		return pushSj;
	}
	public void setPushSj(String pushSj) {
		this.pushSj = pushSj;
	}
	public String getPushCn() {
		return pushCn;
	}
	public void setPushCn(String pushCn) {
		this.pushCn = pushCn;
	}
	public String getPushType() {
		return pushType;
	}
	public void setPushType(String pushType) {
		this.pushType = pushType;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
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
	public Integer gettCount() {
		return tCount;
	}
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	
}
