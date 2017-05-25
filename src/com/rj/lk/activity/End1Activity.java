package com.rj.lk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.domain.Order;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by luokai on 2017/5/1.
 */
public class End1Activity extends Activity implements View.OnClickListener {

    private Chronometer timer;
    private String money;
    private String orderId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end1_bicycle);
        // 获得计时器对象
        timer = (Chronometer) this.findViewById(R.id.chronometer);
        //长按计时器时，出现上下文菜单
        this.registerForContextMenu(timer);
        // 将计时器清零
        timer.setBase(SystemClock.elapsedRealtime());
        //开始计时
        timer.start();

        TextView endBtn = (TextView) this.findViewById(R.id.tv_use_end);
        endBtn.setOnClickListener(this);
        TextView password = (TextView) this.findViewById(R.id.tv_use_password);
        password.setText(ContantData.getPassword());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_use_end:
                int temp0 = Integer.parseInt(timer.getText().toString().split(":")[0]);
                int temp1 = Integer.parseInt(timer.getText().toString().split(":")[1]);
                int temp = temp0 * 60 + temp1;
                endBicycle();
                break;
        }
    }

    //修改姓名按钮
    private void endBicycle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUntils httpUntils = new HttpUntils();
                    String url = Constant.URL_END_BICYCLE + "?userId=" + ContantData.getUser().getId() + "&bicycleId=" + ContantData.getBicycleId() + "&x="
                            + ContantData.getAddressX() + "&y="+ContantData.getAddressY()+"&orderId="+ContantData.getOrderId();
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
                super.handleMessage(msg);String result = (String) msg.obj;
                JSONTokener jsonParser = new JSONTokener(result);
                JSONObject person = (JSONObject) jsonParser.nextValue();
                String isOk = person.getString("success");
                if (isOk.equals("true")) {

                    JSONObject data=person.getJSONObject("data");
                    Order order=new Order();
                    order.setId(data.getInt("id"));
                    order.setCost(data.getDouble("cost"));

                    Intent intent = new Intent(End1Activity.this,
                            End2Activity.class);
                    Toast.makeText(End1Activity.this, R.string.text_end_yes, Toast.LENGTH_SHORT).show();
                    ContantData.setOrder(order);
                    finish();
                    startActivity(intent);
                } else {
                    String message = person.getString("error");
                    Toast.makeText(End1Activity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(End1Activity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
