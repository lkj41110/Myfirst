package com.rj.lk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rj.lk.R;
import com.rj.lk.fragment.Fragment1;
import com.rj.lk.fragment.Fragment2;
import com.rj.lk.fragment.Fragment3;
import com.rj.lk.fragment.Fragment4;

public class MainActivity extends FragmentActivity implements
		android.view.View.OnClickListener {

	private RelativeLayout main_layout;
	private RelativeLayout my_layout;
	private RelativeLayout recommend_layout;
	private RelativeLayout service_layout;
	private TextView main_text;
	private TextView my_text;
	private TextView recommend_text;
	private TextView service_text;
	private ImageView main_image;
	private ImageView my_image;
	private ImageView recommend_image;
	private ImageView service_image;

	private Fragment1 fragment1;
	private Fragment2 fragment2;
	private Fragment3 fragment3;
	private Fragment4 fragment4;
	private int TEXT_COLOR1;
	private int TEXT_COLOR2;
	private int MENU_COLOR1;
	private int MENU_COLOR2;

	FragmentManager fManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fManager = getSupportFragmentManager();
		FragmentTransaction transaction = fManager.beginTransaction();
		transaction.replace(R.id.content, new Fragment1());
		transaction.commit();
		//初始化
		initViews();
		main_layout.setBackgroundColor(MENU_COLOR2);
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
		case R.id.menu_auother:
			intent = new Intent(MainActivity.this,AuotherActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_object:
			intent = new Intent(MainActivity.this,ProjectActivity.class);
			startActivity(intent);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	/**
	 * 初始化资源
	 */
	private void initViews() {
		// 资源定位
		TEXT_COLOR1 = getResources().getColor(R.color.theme_text_1);
		TEXT_COLOR2 = getResources().getColor(R.color.theme_text_2);
		MENU_COLOR1 = getResources().getColor(R.color.theme_down_1);
		MENU_COLOR2 = getResources().getColor(R.color.theme_down_2);

		//菜单图片
		main_layout = (RelativeLayout) findViewById(R.id.main_layout);
		recommend_layout = (RelativeLayout) findViewById(R.id.recommend_layout);
		service_layout = (RelativeLayout) findViewById(R.id.service_layout);
		my_layout = (RelativeLayout) findViewById(R.id.my_layout);
		main_text = (TextView) findViewById(R.id.main_text);
		my_text = (TextView) findViewById(R.id.my_text);
		recommend_text = (TextView) findViewById(R.id.recommend_text);
		service_text = (TextView) findViewById(R.id.service_text);
		main_image = (ImageView) findViewById(R.id.main_image);
		my_image = (ImageView) findViewById(R.id.my_image);
		recommend_image = (ImageView) findViewById(R.id.recommend_image);
		service_image = (ImageView) findViewById(R.id.service_image);

		//
		clearDown();

		// 点击事件
		main_layout.setOnClickListener(this);
		recommend_layout.setOnClickListener(this);
		service_layout.setOnClickListener(this);
		my_layout.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 按钮设置
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.main_layout:
			setChoiceItem(0);
			break;
		case R.id.recommend_layout:
			setChoiceItem(1);
			break;
		case R.id.service_layout:
			setChoiceItem(2);
			break;
		case R.id.my_layout:
			setChoiceItem(3);
			break;
		}
	}

	/**
	 * 菜单栏设置Fragment
	 * 
	 */
	private void setChoiceItem(int i) {
		FragmentTransaction transaction = fManager.beginTransaction();
		clearDown();
		hideFragment(transaction);
		switch (i) {
		case 0:
			main_text.setTextColor(TEXT_COLOR2);
			main_layout.setBackgroundColor(MENU_COLOR2);
			main_image.setImageResource(R.drawable.ic_main_white);
			if (fragment1 == null) {
				fragment1 = new Fragment1();
				transaction.add(R.id.content, fragment1);
			} else {
				transaction.show(fragment1);
			}
			break;

		case 1:
			recommend_text.setTextColor(TEXT_COLOR2);
			recommend_layout.setBackgroundColor(MENU_COLOR2);
			if (fragment2 == null) {
				fragment2 = new Fragment2();
				transaction.add(R.id.content, fragment2);
			} else {
				transaction.show(fragment2);
			}
			break;
		case 2:
			service_text.setTextColor(TEXT_COLOR2);
			service_layout.setBackgroundColor(MENU_COLOR2);
			if (fragment3 == null) {
				fragment3 = new Fragment3();
				transaction.add(R.id.content, fragment3);
			} else {
				transaction.show(fragment3);
			}
			break;
		case 3:
			my_text.setTextColor(TEXT_COLOR2);
			my_layout.setBackgroundColor(MENU_COLOR2);
			my_image.setImageResource(R.drawable.ic_my_white);
			if (fragment4 == null) {
				fragment4 = new Fragment4();
				transaction.add(R.id.content, fragment4);
			} else {
				transaction.show(fragment4);
			}
			break;
		}
		transaction.commit();

	}
	/**
	 * clean
	 */
	private void clearDown() {
		main_text.setTextColor(TEXT_COLOR1);
		my_text.setTextColor(TEXT_COLOR1);
		recommend_text.setTextColor(TEXT_COLOR1);
		service_text.setTextColor(TEXT_COLOR1);
		main_layout.setBackgroundColor(MENU_COLOR1);
		my_layout.setBackgroundColor(MENU_COLOR1);
		recommend_layout.setBackgroundColor(MENU_COLOR1);
		service_layout.setBackgroundColor(MENU_COLOR1);

		main_image.setImageResource(R.drawable.ic_main_white);
		my_image.setImageResource(R.drawable.ic_my_white);
		recommend_image.setImageResource(R.drawable.ic_recommend_white);
		service_image.setImageResource(R.drawable.ic_favorite_white);
	}

	/**
	 * 隐藏菜单栏
	 * 
	 * @param transaction
	 */
	private void hideFragment(FragmentTransaction transaction) {
		if (fragment1 != null) {
			transaction.hide(fragment1);
		}
		if (fragment2 != null) {
			transaction.hide(fragment2);
		}
		if (fragment3 != null) {
			transaction.hide(fragment3);
		}
		if (fragment4 != null) {
			transaction.hide(fragment4);
		}
	}

}
