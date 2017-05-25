package com.rj.lk.untils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUntils {
	private URL url;
	private static final String MOTHRD_POST = "post";
	private static final String MOTHRD_GET = "get";

	/**
	 * ?????????
	 * 
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	private InputStream fromURLgetInput(String urlStr) throws Exception {
		InputStream in = null;
		url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		return connection.getInputStream();
	}


	public String downLoadGet(String urlStr) {
		InputStream inputStream = null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.connect();
			/*
			 * 200 500
			 */
			int state = connection.getResponseCode();
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					reader = null;
				}
			}
		}
		return buffer.toString();
	}

	public String downLoadPost(String urlStr, String content) {
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// ????????????
			connection.setDoOutput(true);// ??? URL ??????????
			connection.setDoInput(true);// ??? URL ???????????
			connection.setUseCaches(false);// ???????
			connection.setRequestMethod("POST");// ????URL?????

			// ????????????
			connection.setRequestProperty("Content-Type",
					"application/octet-stream");
			connection.setRequestProperty("Connection", "Keep-Alive");// ????????
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();

			byte[] requestStringBytes = content.getBytes();
			// ?????????????д??????
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();

			int state = connection.getResponseCode();
			// ????????????????Reader???
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					reader = null;
				}
			}
		}
		return buffer.toString();
	}
}
