package com.rj.lk.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.rj.lk.Constant;
import com.rj.lk.R;
import com.rj.lk.domain.User;
import com.rj.lk.untils.BaseResult;
import com.rj.lk.untils.ContantData;
import com.rj.lk.untils.HttpUntils;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AlterActivity extends Activity {
    String sex = "";
    TextView etGender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alter);
        init();
    }

    private void init() {
        //返回按钮
        ImageView ivAlterBack = (ImageView) findViewById(R.id.iv_alter_back);
        ivAlterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //修改名字
        RelativeLayout rlName = (RelativeLayout) findViewById(R.id.my_layout_alter_name);
        rlName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlterActivity.this, AlterNameActivity.class);
                startActivity(intent);
            }
        });
        //修改密码
        RelativeLayout rlPassword = (RelativeLayout) findViewById(R.id.my_layout_alter_password);
        rlPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlterActivity.this, AlterPasswordActivity.class);
                startActivity(intent);
            }
        });

        //修改性别
        RelativeLayout rlSex = (RelativeLayout) findViewById(R.id.my_layout_alter_sex);
        rlSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AlterActivity.this, R.style.MyDialog);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle(R.string.text_alter_xing);
                String nan=getText(R.string.text_alter_nan).toString();
                String nv=getText(R.string.text_alter_nv).toString();

                //    指定下拉列表的显示数据
                final String[] sexs = {nan, nv};
                //    设置一个下拉的列表选择项
                builder.setItems(sexs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = ContantData.getUser();
                        updateSex(user.getId(), which + "");
                        sex = which + "";
                    }
                });
                AlertDialog r_dialog = builder.create();
                r_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                r_dialog.show();
            }
        });
    }


    //填入数据
    @Override
    protected void onStart() {
        super.onStart();
        //获取数据
        if (ContantData.getUser() == null) {
            //新登入
        }
        User user = ContantData.getUser();
        //重新获取数据
        getInfo(user.getId());
        TextView etName = (TextView) findViewById(R.id.ed_alter_name);
        etGender = (TextView) findViewById(R.id.ed_alter_gender);
        TextView etPhone = (TextView) findViewById(R.id.ed_alter_phone);
        etName.setText(user.getName());
        if (user.getSex().equals("0")) {
            etGender.setText(R.string.text_alter_nan);
        } else {
            etGender.setText(R.string.text_alter_nv);
        }
        etPhone.setText(user.getPhone());
    }

    //获取数据
    private void getInfo(final Integer id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUntils httpUntils = new HttpUntils();
                String result = httpUntils.downLoadGet(Constant.URL_ALTER + "?userId=" + id);
                Message msg = hander.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                msg.sendToTarget();
            }
        }).start();
    }

    //修改性别
    private void updateSex(final Integer id, final String sex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUntils httpUntils = new HttpUntils();
                String url = Constant.URL_ALTER_SEX + "?userId=" + id +
                        "&sex=" + sex;
                String result = httpUntils.downLoadGet(url);
                Log.d("lk", url);
                Message msg = hander.obtainMessage();
                msg.what = 2;
                msg.obj = result;
                msg.sendToTarget();
            }
        }).start();
    }


    Handler hander = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;

            //验证
            BaseResult baseResult = new BaseResult(result);
            switch (msg.what) {
                case 1:
                    if (baseResult.isSuccess()) {
                        ContantData.formatJson(result);
                    } else {
                        Toast.makeText(AlterActivity.this, baseResult.getError(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    try {
                        JSONTokener jsonParser = new JSONTokener(result);
                        JSONObject person = (JSONObject) jsonParser.nextValue();
                        String isOk = person.getString("success");
                        if (isOk.equals("true")) {
                            Toast.makeText(AlterActivity.this, R.string.text_alter_yes, Toast.LENGTH_SHORT).show();
                            ContantData.getUser().setSex(sex);
                            if (sex.equals("1")) {
                                etGender.setText(R.string.text_alter_nv);
                            } else {
                                etGender.setText(R.string.text_alter_nan);
                            }
                        } else {
                            String message = person.getString("error");
                            Toast.makeText(AlterActivity.this, message,
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(AlterActivity.this, R.string.toast_login_wrong,
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };
}
