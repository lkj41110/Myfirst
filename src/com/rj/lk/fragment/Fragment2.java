package com.rj.lk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.rj.lk.R;
import com.rj.lk.activity.GuiderActivity;
import com.rj.lk.untils.ContantData;

import java.util.List;
import java.util.Map;

public class Fragment2 extends Fragment {

	ListView listView;
	List<Map<String, Object>> list = null;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_exclusive, container, false);
        listView = (ListView) view.findViewById(R.id.lv_exclusive);
        list = ContantData.getList();
        SimpleAdapter adapter = new SimpleAdapter(view.getContext().getApplicationContext(), list,
                R.layout.listview_exclusive, new String[] { "title1","createTime"}, new int[] {
                R.id.tv_ex_title, R.id.tv_ex_date });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());
		listView = (ListView) view.findViewById(R.id.listView_recommend);
		return view;
	}
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent(view.getContext().getApplicationContext(),GuiderActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }

    }



	/*
	class MyOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Map map = list.get(position);
			Intent intent = new Intent(getActivity(),
					RecommendMoreActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("recommend_id", (Integer) map.get("recommend_id"));
			bundle.putString("zan", (String) map.get("zan"));
			bundle.putString("here", (String) map.get("here"));
			bundle.putInt("imghead", (Integer) map.get("imghead"));
			bundle.putInt("imgbeask", (Integer) map.get("imgbeask"));
			bundle.putString("describe2", (String) map.get("describe2"));
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	// json����ת��Ϊlist
	private List jsonArray2List(String jsonOrder) {
		JSONArray jsonArray;
		list = new ArrayList<Map<String, Object>>();
		try {
			jsonArray = new JSONArray(jsonOrder);
			for (int i = 0; i < jsonArray.length(); i++) {
				// ��ȡ����
				Recommed recommed = new Recommed();
				JSONObject object = jsonArray.getJSONObject(i);

				recommed.setRecommend_id(object.getInt("recommend_id"));
				recommed.setDescribe1(object.getString("describe1"));
				recommed.setDescribe2(object.getString("describe2"));
				recommed.setHere(object.getString("here"));
				recommed.setImgbeask(object.getString("imgbeask"));
				recommed.setImghead(object.getString("imghead"));
				recommed.setZannumber(object.getString("zannumber"));

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("describe1", recommed.getDescribe1());
				map.put("here", recommed.getHere());
				map.put("zan", recommed.getZannumber());
				map.put("describe2", recommed.getDescribe2());
				map.put("recommend_id", recommed.getRecommend_id());
				// ��ͷ��
				Class<com.rj.lk.R.drawable> cls = R.drawable.class;
				try {
					int imghead = cls.getDeclaredField(
							getName(recommed.getImghead())).getInt(null);
					map.put("imghead", imghead);
				} catch (Exception e) {
					e.printStackTrace();
					map.put("imghead", R.drawable.ic_launcher);
				}

				try {
					int imgbeask = cls.getDeclaredField(
							getName(recommed.getImgbeask())).getInt(null);
					map.put("imgbeask", imgbeask);
				} catch (Exception e) {
					e.printStackTrace();
					map.put("imgbeask", R.drawable.ic_launcher);
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getName(String name) {
		int index = name.indexOf(".");
		return name.substring(0, index);
	}
*/
}
