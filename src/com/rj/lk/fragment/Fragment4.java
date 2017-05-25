package com.rj.lk.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rj.lk.R;
import com.rj.lk.activity.*;
import com.rj.lk.domain.User;
import com.rj.lk.untils.ContantData;

public class Fragment4 extends Fragment implements OnClickListener {
    private User user;
    TextView etVaild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm4, container, false);
        //设置按钮点击事件
        RelativeLayout rlAlter = (RelativeLayout) view.findViewById(R.id.my_layout_alter);
        rlAlter.setOnClickListener(this);
        Button btnOut = (Button) view.findViewById(R.id.button_my_out);
        btnOut.setOnClickListener(this);
        RelativeLayout btnAbout = (RelativeLayout) view.findViewById(R.id.about_me);
        btnAbout.setOnClickListener(this);
        RelativeLayout rlIdCard = (RelativeLayout) view.findViewById(R.id.my_layout_idCard);
        rlIdCard.setOnClickListener(this);

        etVaild = (TextView) view.findViewById(R.id.ed_f4_vaild);
        user = ContantData.getUser();
        if (user == null) {
            //登入
        }
        if (user.getValid()) {
            etVaild.setText(R.string.text_alter_weyanz);
        } else {
            etVaild.setText(R.string.text_alter_yanz);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lk","123");
        Log.d("lk",ContantData.getUser().getValid()+"");

        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if (user.getValid()) {
                        etVaild.setText(R.string.text_alter_weyanz);
                    } else {
                        etVaild.setText(R.string.text_alter_yanz);
                    }
                    break;
            }
        }

        ;
    };

    @Override
    public void onClick(final View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.my_layout_alter:
                intent = new Intent(view.getContext().getApplicationContext(), AlterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_my_out:
                AlertDialog.Builder builder = new Builder(this.getActivity());
                builder.setTitle(R.string.titlt_out);
                builder.setMessage(R.string.message_out);
                builder.setPositiveButton(R.string.button_exit, new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(view.getContext().getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        MainActivity parentActivity = (MainActivity) getActivity();
                        parentActivity.finish();
                    }
                });
                builder.setNegativeButton(R.string.button_cancel, new NegListener());
                builder.show();
                break;
            case R.id.about_me:
                intent = new Intent(view.getContext().getApplicationContext(), ProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.my_layout_idCard:
                if (!user.getValid()) {
                    intent = new Intent(view.getContext().getApplicationContext(), AlterIdCardActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    //按钮监听
    class PosListener implements android.content.DialogInterface.OnClickListener {
        View view;

        PosListener(View view) {
            this.view = view;
        }

        public void onClick(DialogInterface dialog, int which) {
        }

    }

    class NegListener implements android.content.DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
        }

    }

}
