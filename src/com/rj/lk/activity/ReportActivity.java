package com.rj.lk.activity;

import android.app.Activity;
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
import com.rj.lk.domain.User;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ReportActivity extends Activity implements OnClickListener {

    private int type = 0;
    private int id;
    private String x, y;
    public boolean flag = false;
    EditText tvBicycleId;
    Button[] buttons;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        init();
    }

    //按钮等资源加载
    private void init() {

        User user = ContantData.getUser();
        id = user.getId();
        tvBicycleId = (EditText) findViewById(R.id.edit_report_bicycleId);

        ImageView btnBack = (ImageView) findViewById(R.id.iv_order_back);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttons = new Button[9];
        buttons[1] = (Button) findViewById(R.id.button_report_type1);
        buttons[2] = (Button) findViewById(R.id.button_report_type2);
        buttons[3] = (Button) findViewById(R.id.button_report_type3);
        buttons[4] = (Button) findViewById(R.id.button_report_type4);
        buttons[5] = (Button) findViewById(R.id.button_report_type5);
        buttons[6] = (Button) findViewById(R.id.button_report_type6);
        buttons[7] = (Button) findViewById(R.id.button_report_type7);
        buttons[8] = (Button) findViewById(R.id.button_report_type8);
        for (int i = 1; i < 9; i++) {
            buttons[i].setOnClickListener(this);
        }

        Button submit = (Button) findViewById(R.id.button_report_submit);
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String bicycleId = tvBicycleId.getText().toString();
                submit(id, bicycleId);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_report_type1:
                type = 1;
                break;
            case R.id.button_report_type2:
                type = 2;
                break;
            case R.id.button_report_type3:
                type = 3;
                break;
            case R.id.button_report_type4:
                type = 4;
                break;
            case R.id.button_report_type5:
                type = 5;
                break;
            case R.id.button_report_type6:
                type = 6;
                break;
            case R.id.button_report_type7:
                type = 7;
                break;
            case R.id.button_report_type8:
                type = 8;
                break;
        }
        for (int i = 1; i < 9; i++) {
            if (i == type) {
                buttons[i].setActivated(true);
            } else {
                buttons[i].setActivated(false);
            }
        }
    }

    //提交
    private void submit(final int userId, final String bicycleId) {
        if (type == 0) {
            Toast.makeText(ReportActivity.this, R.string.toast_report_wrong1,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (bicycleId == null || "".equals(bicycleId)) {
            Toast.makeText(ReportActivity.this, R.string.toast_report_wrong2,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUntils httpUntils = new HttpUntils();
                    Double x=ContantData.getAddressX();
                    Double y=ContantData.getAddressY();
                    if(x==null){
                        x=30.31714;
                    }
                    if(y==null){
                        y=120.389066;
                    }
                    String result = httpUntils.downLoadGet(Constant.URL_REPORT_SUBMIT + "?userId=" + userId +
                            "&bicycleId=" + bicycleId + "&status=" + type + "&x=" + ContantData.getAddressX() +
                            "&y=" + ContantData.getAddressY());
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
                    Toast.makeText(ReportActivity.this, R.string.toast_report_success,
                            Toast.LENGTH_SHORT).show();
                    flag = true;
                    finish();
                } else {
                    String message = person.getString("error");
                    Toast.makeText(ReportActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(ReportActivity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
