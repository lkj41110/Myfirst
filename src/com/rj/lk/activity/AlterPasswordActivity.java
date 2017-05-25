package com.rj.lk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.rj.lk.R;

public class AlterPasswordActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alter_password);

		// ?????
		ImageView ivAlterBack = (ImageView) findViewById(R.id.iv_alter_password_back);
		ivAlterBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// ??????
		TextView tvOk = (TextView) findViewById(R.id.tv_alter_password_ok);
		tvOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	/*Handler handler = new Handler() {

	};

	private void alterPassword(final String id, final String password) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpUntils httpUntils = new HttpUntils();
				String result = httpUntils
						.downLoadGet(Constant.URL_ALTERPASSWORD + "?id=" + id
								+ "&password=" + password);
				Message msg = handler.obtainMessage();
				msg.obj = result;
				msg.sendToTarget();
			}
		}).start();
	}*/

}
