package com.rj.lk.enums;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 定义异常时，需要先确定异常所属模块。
 * 前两位为所属模块
 * @author lk
 */
public enum ResultEnum {
	// 数据库想操作异常
	DB_INSERT_RESULT_ERROR(99990001, "db insert error"),
	DB_UPDATE_RESULT_ERROR(99990002, "db update error"),
	DB_SELECTONE_IS_NULL(99990003,"db select return null"),

	// 系统异常
	INNER_ERROR(00001, "系统错误"), 

	// 用户相关异常
	INVALID_USER(01001, "无效用户"),
	INVALID_USER_IS_NULL(01002,"用户没有登陆"),
	INVALID_USER_PASSWORD_WRONG(01003,"用户名密码错误"),
	INVALID_USER_NOT_LIMITS(01004,"用户没有权限"),
	
	// 车辆相关异常
	BICYLCE_INVALID(02001,"无效车辆"),
	BICYLCE_NOT_GOOD(02002,"车辆已经损坏");
	
	

	private int state;

	private String msg;

	ResultEnum(int state, String msg) {
		this.state = state;
		this.msg = msg;
	}

	public int getState() {
		return state;
	}

	public String getMsg() {
		return msg;
	}

	public static ResultEnum stateOf(int index) {
		for (ResultEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
