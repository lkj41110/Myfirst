package com.rj.lk.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.untils.HttpUntils;

public class RegisterActivity extends Activity {
	private EditText etName;
	private EditText etPassWord;
	private EditText etRePassWord;
	private EditText etPone;
	private EditText etEmail;
	public static final String Register_OK = "1";
	public static final String Register_NOTOK = "0";
	public static final String Register_EXIST = "2";

	String name, password, repassword, phone, gender, email;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Button btnEnter = (Button) findViewById(R.id.button_register_enter);
		btnEnter.setOnClickListener(new MyOnClickListener());
		ImageView imgBack = (ImageView) findViewById(R.id.button_register_back);
		imgBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		

		etName = (EditText) findViewById(R.id.ed_register_name);
		etPassWord = (EditText) findViewById(R.id.ed_register_password);
		etRePassWord = (EditText) findViewById(R.id.ed_register_repassword);
		etPone = (EditText) findViewById(R.id.ed_register_phone);
		etEmail = (EditText) findViewById(R.id.ed_register_email);

	}

	class MyOnClickListener implements OnClickListener {
		public void onClick(View v) {
			// �ж������Ƿ�����
			if (checkIsOk()) {
				JSONObject object = new JSONObject();
				try {
					object.put("name", name);
					object.put("gender", gender);
					object.put("id", phone);
					object.put("password", password);
					object.put("email", email);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(object.toString());
				newThreadToHttp(object.toString());
			}
		}
	};

	//���ܵ�ע����Ϣ��Handler
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			String result = (String) msg.obj;
			if(result.equals(Register_OK)){
				Toast.makeText(getApplicationContext(), "ע��ɹ�", Toast.LENGTH_LONG).show();
				//��תҳ��
				new Handler().postDelayed(runnable, 2000);
			}else if(result.equals(Register_NOTOK)){
				Toast.makeText(getApplicationContext(), "ע��ʧ��", Toast.LENGTH_LONG).show();
			}else if(result.equals(Register_EXIST)){
				Toast.makeText(getApplicationContext(), "�ֻ��Ѿ�ע���", Toast.LENGTH_LONG).show();
			}
		}

	};
	Runnable runnable=new Runnable() {
		
		@Override
		public void run() {
			Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
		}
	};
		

	//�����µ��̷߳��ʷ�����
	private void newThreadToHttp(final String json) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpUntils httpUntils = new HttpUntils();
				String result = httpUntils
						.downLoadPost(
								Constant.URL_REGISTER,
								json);
				Message msg = handler.obtainMessage();
				msg.obj = result;
				msg.sendToTarget();
			}
		}).start();
	}

	//����֤
	private boolean checkIsOk() {
		name = etName.getText().toString().trim();
		password = etPassWord.getText().toString();
		repassword = etRePassWord.getText().toString();
		phone = etPone.getText().toString().trim();
		gender = "male";
		email = etEmail.getText().toString().trim();
		//��֤����
		if (name == null || name.equals("")){
			Toast.makeText(getApplicationContext(), "�û���Ϊ��", Toast.LENGTH_LONG).show();
			return false;
		}
		//��֤����
		if(password == null || password.equals(""))
		{
			Toast.makeText(getApplicationContext(), "����Ϊ��", Toast.LENGTH_LONG).show();
			return false;
		}else if(!password.equals(repassword)){
			Toast.makeText(getApplicationContext(), "�������벻��ͬ", Toast.LENGTH_LONG).show();
			return false;
		}
		//��֤�ֻ�����
		//��֤����
		return true;
	}
}
