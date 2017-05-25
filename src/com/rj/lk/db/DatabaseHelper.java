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

		// ��������
		ContentValues values = new ContentValues();
		values.put("_id", "18758114722");
		values.put("name", "Сǿ");
		values.put("gender", "male");
		values.put("city", "����");
		values.put("password", "123456");
		values.put("work", "�㽭�ƾ���ѧ");
		values.put("introduce", "1:��ã�����һ����������еĴ�ѧ�����Դ����������Ͱ����������������ġ���ʷ����������ʳ�����Ұ���������ԭ����ϲ�������ѣ�������Ǣ�����֣��������޻���̸����ϲ�����裬�����ֺ������ķ���Ƚϸ���Ȥ�����ǰ����������ɣ���������Ҳ�Ƚ�ϲ����ֹ���żٵ�ʱ��ͻ�ȥ��䣬�Գ�С�԰��������������Ҹ����ء�");
		values.put("way", "��· 1-2");
		values.put("title", "��ת����");
		values.put("age", "20");
		values.put("email", "aa@qq.com");
		values.put("imghead", "guider18758114722.jpg");
		values.put("imgback", "list_e1.jpg");
		db.insert("guider", null, values);
		// ��������
		values.clear();
		values = new ContentValues();
		values.put("_id", "18758114723");
		values.put("name", "С��");
		values.put("gender", "male");
		values.put("city", "�Ͼ�");
		values.put("password", "123456");
		values.put("work", "�㽭�ƾ���ѧ");
		values.put("introduce", "2:��ã�����һ����������еĴ�ѧ�����Դ����������Ͱ����������������ġ���ʷ����������ʳ�����Ұ���������ԭ����ϲ�������ѣ�������Ǣ�����֣��������޻���̸����ϲ�����裬�����ֺ������ķ���Ƚϸ���Ȥ�����ǰ����������ɣ���������Ҳ�Ƚ�ϲ����ֹ���żٵ�ʱ��ͻ�ȥ��䣬�Գ�С�԰��������������Ҹ����ء�");
		values.put("way", "��·");
		values.put("title", "�ú���");
		values.put("age", "20");
		values.put("email", "aa@qq.com");
		values.put("imghead", "guider18758114723.jpg");
		values.put("imgback", "list_e2.jpg");
		db.insert("guider", null, values);
		// ��������
		values.clear();
		values = new ContentValues();
		values.put("_id", "18758114744");
		values.put("name", "С��");
		values.put("gender", "male");
		values.put("city", "����");
		values.put("password", "123456");
		values.put("work", "���ֱ���רҵ�����ڶ�");
		values.put("introduce", "3:��ã�����һ����������еĴ�ѧ�����Դ����������Ͱ����������������ġ���ʷ����������ʳ�����Ұ���������ԭ����ϲ�������ѣ�������Ǣ�����֣��������޻���̸����ϲ�����裬�����ֺ������ķ���Ƚϸ���Ȥ�����ǰ����������ɣ���������Ҳ�Ƚ�ϲ����ֹ���żٵ�ʱ��ͻ�ȥ��䣬�Գ�С�԰��������������Ҹ����ء�");
		values.put("way", "��·");
		values.put("title", "��ӭ��������");
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
