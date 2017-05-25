package com.rj.lk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.rj.lk.untils.UserInfoUtil;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class AlterIdCardActivity extends Activity {
    private EditText tvIdCard;
    private EditText tvName;
    private Integer id;
    private String IdCard;
    private String name;
    private boolean flag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_idcard);
        init();
    }

    //初始化信息
    private void init() {
        ImageView ivAlterBack = (ImageView) findViewById(R.id.iv_alter_card_back);
        ivAlterBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取用户信息
        User user = ContantData.getUser();
        id = user.getId();

        TextView tvOk = (TextView) findViewById(R.id.tv_alter_card_ok);
        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IdCard = tvIdCard.getText().toString();
                name = tvName.getText().toString();
                if (UserInfoUtil.isValidatedAllIdcard(IdCard)) {
                    //验证id
                    validIdCard(id, IdCard, name);
                } else {
                    Toast.makeText(AlterIdCardActivity.this, R.string.text_id_card_no,
                            Toast.LENGTH_SHORT).show();
                }
                getHomeActivity();
            }
        });


        tvIdCard = (EditText) findViewById(R.id.edit_id_card);
        tvName = (EditText) findViewById(R.id.edit_id_name);
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
    private void validIdCard(final Integer userId, final String idCrad, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUntils httpUntils = new HttpUntils();
                    String url = Constant.URL_RVALID_IDCARD + "?userId=" +
                            id + "&name=" + URLEncoder.encode(name, "UTF-8") +
                            "&idCard=" + IdCard;
                    Log.d("url", url);
                    String result = httpUntils.downLoadGet(url);
                    Message msg = hander.obtainMessage();
                    msg.obj = result;
                    msg.sendToTarget();
                } catch (Exception e) {

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
                    Toast.makeText(AlterIdCardActivity.this, R.string.text_id_card_yes, Toast.LENGTH_SHORT).show();
                    ContantData.getUser().setName(name);
                    ContantData.getUser().setValid(true);
                } else {
                    String message = person.getString("error");
                    Toast.makeText(AlterIdCardActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(AlterIdCardActivity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
