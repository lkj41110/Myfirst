package com.rj.lk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;
import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import org.json.JSONObject;
import org.json.JSONTokener;
import qr_codescan.MipcaActivityCapture;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luokai on 2017/5/1.
 */
public class UseActivity extends Activity implements View.OnClickListener {

    EditText editId;
    boolean flag = false;
    String bicycleId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_bicycle);
        init();
    }

    private void init() {
        ImageView ivAlterBack = (ImageView) findViewById(R.id.iv_use_back);
        ivAlterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tvOk = (TextView) findViewById(R.id.tv_use_ok);
        tvOk.setOnClickListener(this);
        editId = (EditText) findViewById(R.id.edit_use_name);

        RelativeLayout saoMiao = (RelativeLayout) findViewById(R.id.my_layout_use_saomiao);
        saoMiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UseActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    bicycleId = bundle.getString("result");
                    Double addressX = ContantData.getAddressX();
                    Double addressY = ContantData.getAddressY();
                    getBicyclePassword(bicycleId, addressX, addressY);
                    //跳转
                    getActivity();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_use_ok:
                bicycleId = editId.getText().toString();
                Double addressX = ContantData.getAddressX();
                Double addressY = ContantData.getAddressY();
                getBicyclePassword(bicycleId, addressX, addressY);
                //跳转
                getActivity();
                break;
        }
    }

    //延迟跳转
    private void getActivity() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                //跳转到计时页面
                if (flag) {
                    Intent intent = new Intent(UseActivity.this, End1Activity.class);
                    finish();
                    startActivity(intent);
                }
            }
        };
        timer.schedule(task, 1500);

    }

    //开始计时
    private void getBicyclePassword(final String bicycleId, final Double x, final Double y) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUntils httpUntils = new HttpUntils();
                    String url = Constant.URL_USE_BICYCLE + "?userId=" + ContantData.getUser().getId() + "&bicycleId="
                            + bicycleId + "&x=" + x + "&y=" + y;
                    String result = httpUntils.downLoadGet(url);
                    Message msg = hander.obtainMessage();
                    msg.obj = result;
                    msg.sendToTarget();
                } catch (Exception e) {

                }
            }
        }).start();
    }

    //返回信息
    Handler hander = new Handler() {
        public void handleMessage(Message msg) {
            try {
                super.handleMessage(msg);
                String result = (String) msg.obj;
                JSONTokener jsonParser = new JSONTokener(result);
                JSONObject person = (JSONObject) jsonParser.nextValue();
                String isOk = person.getString("success");
                if (isOk.equals("true")) {
                    JSONObject data = person.getJSONObject("data");
                    JSONObject bicycle = data.getJSONObject("bicycle");
                    String password = bicycle.getString("password");
                    ContantData.setBicycleId(bicycleId);
                    ContantData.setPassword(password);

                    JSONObject order = data.getJSONObject("order");
                    String orderId = order.getString("id");
                    ContantData.setOrderId(orderId);
                    flag = true;
                } else {
                    String message = person.getString("error");
                    Toast.makeText(UseActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(UseActivity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
