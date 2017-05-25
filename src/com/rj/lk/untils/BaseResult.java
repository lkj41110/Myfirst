package com.rj.lk.untils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;

/**
 * @author lk
 *         <p>
 *         ajax 请求的返回类型封装JSON结果
 *         注解是为空的字段不显示
 */
public class BaseResult<T> implements Serializable {


	private static final long serialVersionUID = -4185151304730685014L;

	private boolean success;

	private T data;

	private String res;

	private String error;

	public BaseResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public BaseResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	/**
	 * 传入json数据返回BaseResult对象
	 *
	 * @param json
	 */
	public BaseResult(String json) {
		Boolean isOk = false;
		try {
			JSONTokener jsonParser = new JSONTokener(json);
			JSONObject person = (JSONObject) jsonParser.nextValue();
			isOk = person.getBoolean("success");
			if (isOk) {
				this.res = person.getString("data");
			} else {
				this.error = person.getString("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.error = "解析异常";
		}
		this.success = isOk;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	@Override
	public String toString() {
		return "BaseResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}

}
