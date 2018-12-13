package org.zjs.pojo;

import org.bson.types.ObjectId;

public class Robe {
	private ObjectId _id;
	private String robeName;
	private String robeId;
	
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getRobeName() {
		return robeName;
	}
	public void setRobeName(String robeName) {
		this.robeName = robeName;
	}
	public String getRobeId() {
		return robeId;
	}
	public void setRobeId(String robeId) {
		this.robeId = robeId;
	}
	@Override
	public String toString() {
		return "Robe [_id=" + _id + ", robeName=" + robeName + ", robeId=" + robeId + "]";
	}
	
	
	
	
	
	
	
}
