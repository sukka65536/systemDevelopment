package jp.ac.isc.cloud;

public class Member {
	private String id;
	private String name;
	private String picture;
	
	public Member(String id, String name, String picture) {
		this.id = id;
		this.name = name;
		this.picture = picture;
	}
	
	// setter
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	// getter
	public String getId() { return id; }
	public String getName() { return name; }
	public String getPicture() { return picture; }
}
