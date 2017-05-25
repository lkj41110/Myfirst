package com.rj.lk.activity;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rj.lk.R;
import com.rj.lk.untils.CircleImageView;

public class RecommendMoreActivity extends Activity {
	private int imghead;
	private int imgbeask;
	private String zan;
	private String here;
	private String describe2;

	private TextView tvHere;
	private TextView tvZan;
	private TextView tvDescribe2;
	private CircleImageView imgViewhead;
	private ImageView imgViewback;
	private Boolean isZan = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend_more);

		tvHere = (TextView) findViewById(R.id.tv_recommendMore_here);
		tvDescribe2 = (TextView) findViewById(R.id.tv_recommendMore_introduce2);
		tvZan = (TextView) findViewById(R.id.tv_recommendMore_zan);
		imgViewback = (ImageView) findViewById(R.id.img_recommendMore_imgbeask);
		imgViewhead = (CircleImageView) findViewById(R.id.circleImage_recommendMore_head);
		init();

		LinearLayout clickZan = (LinearLayout) findViewById(R.id.ll_recommendMore_clickzan);
		clickZan.setOnClickListener(new ClickZanOnClickListener());
	}
	class ClickZanOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			int temp;
			if(!isZan){
				temp = Integer.parseInt(zan);
				temp++;
				zan=temp+"";
				tvZan.setTextColor(getResources().getColor(R.color.blue));
				isZan=!isZan;
			}else{
				temp = Integer.parseInt(zan);
				temp--;
				zan=temp+"";
				tvZan.setTextColor(getResources().getColor(R.color.black));
				isZan=!isZan;
			}
			tvZan.setText(zan+"赞");
		}
	}

	// 初始化信息
	private void init() {
		// 获取信息
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			imghead = bundle.getInt("imghead");
			imgbeask = bundle.getInt("imgbeask");
			zan = bundle.getString("zan");
			here = bundle.getString("here");
			describe2 = bundle.getString("describe2");
		} else {
			imghead = R.drawable.ic_launcher;
			imgbeask = R.drawable.ic_launcher;
			zan = "";
			here = "";
			describe2 = "";
		}
		tvHere.setText(here);
		tvZan.setText(zan + "赞");
		tvDescribe2.setText(describe2);
		imgViewback.setImageResource(imgbeask);
		imgViewhead.setImageResource(imghead);
	}

}
