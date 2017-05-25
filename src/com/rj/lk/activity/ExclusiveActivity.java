package com.rj.lk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.rj.lk.R;

import java.util.ArrayList;
import java.util.Map;

public class ExclusiveActivity extends Activity {

	ListView listView;
	private ArrayList<Map<String, Object>> list = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exclusive);
		listView = (ListView) findViewById(R.id.lv_exclusive);
		list = new ArrayList<Map<String, Object>>();

	}


}
