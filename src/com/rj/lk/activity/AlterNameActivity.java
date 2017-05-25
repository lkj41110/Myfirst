package com.rj.lk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.domain.User;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class AlterNameActivity extends Activity {
    private EditText tvName;
    private Integer id;
    private String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_name);
        init();
    }

    //初始化信息
    private void init() {
        ImageView ivAlterBack = (ImageView) findViewById(R.id.iv_alter_back);
        ivAlterBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        User user = ContantData.getUser();
        id = user.getId();
        name = user.getName();

        TextView tvOk = (TextView) findViewById(R.id.tv_alter_name_ok);
        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = tvName.getText().toString();
                alterName(id, name);
                getHomeActivity();
            }
        });

        tvName = (EditText) findViewById(R.id.edit_alter_name);
        tvName.setText(name);
    }

    //延迟跳转
    private void getHomeActivity() {
        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                finish();
            }
        };
        timer.schedule(task, 2500);
    }

    //修改姓名按钮
    private void alterName(final int id, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUntils httpUntils = new HttpUntils();
                    String result = httpUntils.downLoadGet(Constant.URL_ALTERNAME + "?userId=" + id + "&name=" + URLEncoder.encode(name, "UTF-8"));
                    Message msg = hander.obtainMessage();
                    msg.obj = result;
                    msg.sendToTarget();
                }catch (Exception e){

                }
            }
        }).start();
    }

    //交互
    Handler hander = new Handler() {
        public void handleMessage(Message msg) {
            try {
                super.handleMessage(msg);
                String result = (String) msg.obj;
                JSONTokener jsonParser = new JSONTokener(result);
                JSONObject person = (JSONObject) jsonParser.nextValue();
                String isOk = person.getString("success");
                if (isOk.equals("true")) {
                    Toast.makeText(AlterNameActivity.this, R.string.text_alter_name_yes, Toast.LENGTH_SHORT).show();
                    ContantData.getUser().setName(name);
                } else {
                    String message=person.getString("error");
                    Toast.makeText(AlterNameActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(AlterNameActivity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
