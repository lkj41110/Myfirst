package com.rj.lk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
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
 * Created by luokai on 2017/5/22.
 */
public class End2Activity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_bicycle);
        Order order = ContantData.getOrder();
        if (order == null) {
            Toast.makeText(End2Activity.this, "完成订单", Toast.LENGTH_SHORT).show();
            return ;
        }
        TextView textView = (TextView) findViewById(R.id.tv_end2_money);
        textView.setText(order.getCost()+"");
        Button button = (Button) findViewById(R.id.button_cost);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cost:
                costBicycle();
        }
    }

    private void costBicycle(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUntils httpUntils = new HttpUntils();
                    String url = Constant.URL_COST_BICYCLE + "?orderId=" +ContantData.getOrder().getId()+"&userId="+ContantData.getUser().getId();
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
                    String data=person.getString("data");
                    if(data.equals("true")){
                        Toast.makeText(End2Activity.this, R.string.toast_cost_yes,
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(End2Activity.this,
                                MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(End2Activity.this, "系统出现错误",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(End2Activity.this, R.string.toast_login_wrong,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };


}
