package org.zjs.pojo;

import org.bson.types.ObjectId;

public class Page {
	private ObjectId _id;
	private String page;
	private String url;
	
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Page [_id=" + _id + ", page=" + page + ", url=" + url + "]";
	}
	
	
}
