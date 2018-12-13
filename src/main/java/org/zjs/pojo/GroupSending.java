package org.zjs.pojo;

import org.bson.types.ObjectId;

public class GroupSending {
	private ObjectId _id;
	private String company;
	private String robeId;
	private String URL;
	
	public GroupSending() {}

	public GroupSending(String company, String robeId, String uRL) {
		super();
		
		this.company = company;
		this.robeId = robeId;
		URL = uRL;
	}


	public String getRobeId() {
		return robeId;
	}

	public void setRobeId(String robeId) {
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


	public String getURL() {
		return URL;
	}


	public void setURL(String uRL) {
		URL = uRL;
	}


	@Override
	public String toString() {
		return "GroupSending [_id=" + _id + ", company=" + company + ", robeId=" + robeId + ", URL=" + URL + "]";
	}
	
	
	

	
	
}
