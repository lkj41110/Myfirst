package com.rj.lk.domain;

public class Recommed {
	private int recommend_id;
	private String user_id;
	private String here;
	private String zannumber;
	private String imghead;
	private String imgbeask;
	private String describe1;
	private String describe2;
	
	public int getRecommend_id() {
		return recommend_id;
	}
	public void setRecommend_id(int recommend_id) {
		this.recommend_id = recommend_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getHere() {
		return here;
	}
	public void setHere(String here) {
		this.here = here;
	}
	public String getZannumber() {
		return zannumber;
	}
	public void setZannumber(String zannumber) {
		this.zannumber = zannumber;
	}
	public String getImghead() {
		return imghead;
	}
	public void setImghead(String imghead) {
		this.imghead = imghead;
	}
	public String getImgbeask() {
		return imgbeask;
	}
	public void setImgbeask(String imgbeask) {
		this.imgbeask = imgbeask;
	}
	public String getDescribe1() {
		return describe1;
	}
	public void setDescribe1(String describe1) {
		this.describe1 = describe1;
	}
	public String getDescribe2() {
		return describe2;
	}
	public void setDescribe2(String describe2) {
		this.describe2 = describe2;
	}
	@Override
	public String toString() {
		return "Recommed [recommend_id=" + recommend_id + ", user_id="
				+ user_id + ", here=" + here + ", zannumber=" + zannumber
				+ ", imghead=" + imghead + ", imgbeask=" + imgbeask
				+ ", describe1=" + describe1 + ", describe2=" + describe2 + "]";
	}
}
