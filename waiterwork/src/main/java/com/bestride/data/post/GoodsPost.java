package com.bestride.data.post;


public class GoodsPost {

	private String posid;
	private String sessionId;
	private String searchValue;
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public GoodsPost(String posid, String sessionId, String searchValue) {
		super();
		this.posid = posid;
		this.sessionId = sessionId;
		this.searchValue = searchValue;
	}
	
}
