package org.zjs.pojo;

import org.bson.types.ObjectId;

public class CpRobe {
	private ObjectId _id;
	private String company;
	private String robeId;
	
	public CpRobe() {}

	public CpRobe(String company, String robeId) {
		super();
		this.company = company;
		this.robeId = robeId;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRobeId() {
		return robeId;
	}
	public void setRobeId(String robeId) {
		this.robeId = robeId;
	}
	@Override
	public String toString() {
		return "CpRobe [_id=" + _id + ", company=" + company + ", robeId=" + robeId + "]";
	}
	
	
	
	
	
	
}
