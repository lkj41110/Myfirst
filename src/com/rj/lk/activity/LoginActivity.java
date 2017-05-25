package com.rj.lk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LoginActivity extends Activity implements OnClickListener {
    private Button btnEnter;
    private Button btnRegister;
    private Button btnWXregister;
    private EditText etNumber;
    private EditText etPassWord;
    private CheckBox cbRemember;

    private SharedPreferences pre;
    private Editor editor;
    private String username;
    private String password;

    public static final String LOGIN_OK = "true";
    public static final String LOGIN_NOTOK = "false";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pre = PreferenceManager.getDefaultSharedPreferences(this);
        init();
        Boolean isRemember = pre.getBoolean("cbRemember", false);
        if (isRemember) {
            String name = pre.getString("etUserName", "");
            String password = pre.getString("etPassWord", "");
            etNumber.setText(name);
            etPassWord.setText(password);
            cbRemember.setChecked(true);
        }
    }

    /**
     * 初始化信息
     */
    private void init() {
        btnEnter = (Button) findViewById(R.id.button_enter);
        btnRegister = (Button) findViewById(R.id.button_register);
        btnWXregister = (Button) findViewById(R.id.button_WXregister);
        etNumber = (EditText) findViewById(R.id.et_username);
        etPassWord = (EditText) findViewById(R.id.et_password);
        cbRemember = (CheckBox) findViewById(R.id.cb_remember);

        btnEnter.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnWXregister.setOnClickListener(this);

    }

    @Override
    protected void onStop() {
        saveInfo();
        super.onStop();
    }

    /**
     * 点击
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button_enter:
                username = etNumber.getText().toString().trim();
                password = etPassWord.getText().toString().trim();
                isOk(username, password);
                break;
            case R.id.button_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_WXregister:
//			btnWXregister.offsetTopAndBottom(10);  
//			btnWXregister.offsetLeftAndRight(10);
                break;
        }
    }

    /**
     * 保存信息
     */
    private void saveInfo() {
        editor = pre.edit();
        if (cbRemember.isChecked()) {
            editor.putBoolean("cbRemember", true);
            editor.putString("etUserName", etNumber.getText().toString());
            editor.putString("etPassWord", etPassWord.getText().toString());
        } else {
            editor.clear();
        }
        editor.commit();
    }

    private void saveId() {
        editor = pre.edit();
        editor.putString("id", username);
        editor.commit();
    }


    private void isOk(final String username, final String password) {
        new Thread(new Runnable() {
            public void run() {
                HttpUntils httpUntils = new HttpUntils();
                String result = httpUntils.downLoadGet(Constant.URL_LOGIN + "?phone="
                        + username + "&password=" + password);
                Message msg = handler.obtainMessage();
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = (String) msg.obj;
            try {
                JSONTokener jsonParser = new JSONTokener(result);
                JSONObject person = (JSONObject) jsonParser.nextValue();
                String isOk = person.getString("success");
                if (isOk.equals(LOGIN_OK)) {
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    saveId();
                    //保存用户信息
                    ContantData.formatJson(result);
                    startActivity(intent);
                    finish();
                } else {
                    String message = person.getString("error");
                    Toast.makeText(LoginActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
