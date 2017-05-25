package com.rj.lk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public DatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}

	public DatabaseHelper(Context context, String name) {
		super(context, name, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table guider(_id int primary key,"
				+ "name varchar(20) not null," + "gender varchar(4) not null,"
				+ "city varchar(20)," + "password varchar(20) not null,"
				+ "email varchar(40) not null," + "work varchar(20),"
				+ "introduce text not null," + "way varchar(20),"
				+ "title nvarchar(20) not null,"
				+ "age varchar(8) not null,imghead varchar(50),imgback varchar(50))");

		// 插入数据
		ContentValues values = new ContentValues();
		values.put("_id", "18758114722");
		values.put("name", "小强");
		values.put("gender", "male");
		values.put("city", "北京");
		values.put("password", "123456");
		values.put("work", "浙江财经大学");
		values.put("introduce", "1:你好，我是一名在这个都市的大学生，自从来了这里后就爱上了这里，这里的人文、历史、环境、美食就是我爱上这个理的原因。我喜欢交朋友，用心融洽的那种，跟朋友无话不谈。我喜欢唱歌，对音乐和艺术的方面比较感兴趣，算是半个文艺青年吧，哈哈。我也比较喜欢逛街购物，放假的时候就回去逛逛，吃吃小吃啊，真是满满的幸福感呢。");
		values.put("way", "走路 1-2");
		values.put("title", "玩转地球");
		values.put("age", "20");
		values.put("email", "aa@qq.com");
		values.put("imghead", "guider18758114722.jpg");
		values.put("imgback", "list_e1.jpg");
		db.insert("guider", null, values);
		// 插入数据
		values.clear();
		values = new ContentValues();
		values.put("_id", "18758114723");
		values.put("name", "小张");
		values.put("gender", "male");
		values.put("city", "南京");
		values.put("password", "123456");
		values.put("work", "浙江财经大学");
		values.put("introduce", "2:你好，我是一名在这个都市的大学生，自从来了这里后就爱上了这里，这里的人文、历史、环境、美食就是我爱上这个理的原因。我喜欢交朋友，用心融洽的那种，跟朋友无话不谈。我喜欢唱歌，对音乐和艺术的方面比较感兴趣，算是半个文艺青年吧，哈哈。我也比较喜欢逛街购物，放假的时候就回去逛逛，吃吃小吃啊，真是满满的幸福感呢。");
		values.put("way", "走路");
		values.put("title", "好好玩");
		values.put("age", "20");
		values.put("email", "aa@qq.com");
		values.put("imghead", "guider18758114723.jpg");
		values.put("imgback", "list_e2.jpg");
		db.insert("guider", null, values);
		// 插入数据
		values.clear();
		values = new ContentValues();
		values.put("_id", "18758114744");
		values.put("name", "小华");
		values.put("gender", "male");
		values.put("city", "杭州");
		values.put("password", "123456");
		values.put("work", "音乐表演专业本科在读");
		values.put("introduce", "3:你好，我是一名在这个都市的大学生，自从来了这里后就爱上了这里，这里的人文、历史、环境、美食就是我爱上这个理的原因。我喜欢交朋友，用心融洽的那种，跟朋友无话不谈。我喜欢唱歌，对音乐和艺术的方面比较感兴趣，算是半个文艺青年吧，哈哈。我也比较喜欢逛街购物，放假的时候就回去逛逛，吃吃小吃啊，真是满满的幸福感呢。");
		values.put("way", "走路");
		values.put("title", "欢迎来到杭州");
		values.put("age", "20");
		values.put("email", "aa@qq.com");
		values.put("imghead", "guider18758114744.jpg");
		values.put("imgback", "list_e3.jpg");
		db.insert("guider", null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
