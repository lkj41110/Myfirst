package com.rj.lk.text;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;

import com.rj.lk.db.DatabaseHelper;
import com.rj.lk.untils.HttpUntils;

public class TextCase extends AndroidTestCase {

	public void Httptexst(){
		HttpUntils httpUntils = new HttpUntils();
		/*String result = httpUntils.downLoadGet(LoginActivity.URL_LOGIN + "?id="
				+ "1" + "&password=" + "1");
		System.out.println(result);*/
		JSONObject object = new JSONObject();
			try {
				object.put("name", "aa");
				object.put("gender", "male");
				object.put("id", "18758114721");
				object.put("password", "123456");
				object.put("email", "aaa");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		String string=httpUntils.downLoadPost("http://10.0.2.2:8080/MyFirstWorkService/servlet/AddUserServlet", object.toString());
		System.out.println(string);
	}
	public static final String URL_ALTER = "http://10.0.2.2:8080/MyFirstWorkService/servlet/AlterServlet"; // 链接服务器的url
	private String id="11";
	public void find(){
		
		HttpUntils httpUntils = new HttpUntils();
		String urlStr = URL_ALTER + "?id=" + id;
		String jsonUser = httpUntils.downLoadGet(urlStr);
		System.out.println(jsonUser);
	}
	public void sqltest(){
		DatabaseHelper databaseHelper = new DatabaseHelper(getContext(),"waner");
		
	}
	
}
