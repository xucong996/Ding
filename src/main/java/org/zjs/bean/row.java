package org.zjs.bean;

public class row {
	private String pageid;
	private String time;
	private String cpName;
	public String getPageid() {
		return pageid;
	}
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	@Override
	public String toString() {
		return "row [pageid=" + pageid + ", time=" + time + ", cpName=" + cpName + "]";
	}
	
	
}
