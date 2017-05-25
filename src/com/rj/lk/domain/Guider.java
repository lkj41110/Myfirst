package com.rj.lk.domain;

public class Guider {
	private String _id; // 标号，和电话相同
	private String name; // 姓名
	private String gender;
	private String password;
	private String city;
	private String email;
	private String work; // 工作
	private String introduce; // 介绍
	private String way;// 旅游方式
	private String title;
	private String age;

	@Override
	public String toString() {
		return "Guider [_id=" + _id + ", name=" + name + ", gender=" + gender
				+ ", password=" + password + ", city=" + city + ", email="
				+ email + ", work=" + work + ", introduce=" + introduce
				+ ", way=" + way + ", title=" + title + ", age=" + age + "]";
	}
	public Guider(){};
	public Guider(String _id, String name, String gender, String password,
			String city, String email, String work, String introduce,
			String way, String title, String age) {
		super();
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.city = city;
		this.email = email;
		this.work = work;
		this.introduce = introduce;
		this.way = way;
		this.title = title;
		this.age = age;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
