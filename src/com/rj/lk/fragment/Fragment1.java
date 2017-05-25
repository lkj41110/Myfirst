package com.rj.lk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.activity.Map2Activity;
import com.rj.lk.activity.MapActivity;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import com.rj.lk.untils.JSONHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;
import java.util.Map;

public class Fragment1 extends Fragment implements OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm1, container, false);
        //定位
//        Button button2 = (Button) view.findViewById(R.id.button_map);
//        button2.setOnClickListener(this);
        //地图
        Button buttonmap = (Button) view.findViewById(R.id.button_map2);
        buttonmap.setOnClickListener(this);

        //初始化信息
        getData();

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button_map:
                intent = new Intent(view.getContext().getApplicationContext(), MapActivity.class);
                startActivity(intent);
                break;
            case R.id.button_map2:
                intent = new Intent(view.getContext().getApplicationContext(), Map2Activity.class);
                startActivity(intent);
                break;

        }
    }

    private void getData(){
        new Thread(new Runnable() {
            public void run() {
                HttpUntils httpUntils = new HttpUntils();
                String result = httpUntils.downLoadGet(Constant.URL_GET_ACTIVITY);
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
                if(isOk.equals("true")){
                    JSONObject data = person.getJSONObject("data");
                    JSONArray act = data.getJSONArray("activity");
                    List list = (List<Map<String, Object>>) JSONHelper.jsonToList(act);
                    ContantData.setList(list);
                    JSONArray bis = data.getJSONArray("list");
                    List list2 = (List<Map<String, Object>>) JSONHelper.jsonToList(bis);
                    ContantData.setBicycles(list2);
                }
            } catch (Exception e) {

            }
        }
    };

}
