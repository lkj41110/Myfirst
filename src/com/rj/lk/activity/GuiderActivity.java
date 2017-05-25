package com.rj.lk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.rj.lk.R;
import com.rj.lk.untils.ContantData;

import java.util.Map;

public class GuiderActivity extends Activity {
    Integer position = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider);

        //返回键按钮
        ImageView imgBack = (ImageView) findViewById(R.id.iv_guider_back);
        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取信息
//		initInformation();
        initDate();
    }

    /**
     *
     */
    private void initDate() {
        Intent intent = getIntent();
        //传递信息不为空
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            position = bundle.getInt("position");
            Map<String, Object> map = ContantData.getList().get(position);
            String title = (String) map.get("title1");
            String content = (String) map.get("content1");
            String date = (String) map.get("createTime");

            TextView tvContent = (TextView) findViewById(R.id.tv_activity_detail_content);
            TextView tvTitle = (TextView) findViewById(R.id.tv_activity_detail_title);
            tvContent.setText(content);
            tvTitle.setText(title);
        }
    }

    /**
     * 获取导游的信息
     */
    /*private void initInformation() {
        Intent intent = getIntent();
        //传递信息不为空
        if (intent != null) {

        }

    }*/


    private String getName(String name) {
        int index = name.indexOf(".");
        return name.substring(0, index);
    }
}
