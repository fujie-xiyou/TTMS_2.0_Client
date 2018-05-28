package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import model.CustomResp;
import model.Result;

public class HttpCommon {
	public static String url = "http://localhost:8080";
	private static String cookie = null;

	public static void setCookie(String cookie) {
		HttpCommon.cookie = cookie;
	}
	public CustomResp doHttp(String path) {
		return doHttp(path,"GET",null);
	}
	public CustomResp doHttp(String path, Object object) {
		return doHttp(path,"POST",object);
	}
	public CustomResp doHttp(String path, String method, Object object) {
		String data = new Gson().toJson(object);
		path = url + path;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// conn.setRequestProperty("Connection", "Keep-Alive");
			// conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// conn.setRequestProperty("accept","application/json");
			if (cookie != null) {
				conn.setRequestProperty("Cookie", cookie);
			}
			try {
				if (method.equals("POST") && data != null) {
					conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
					OutputStream os = conn.getOutputStream();
					os.write(data.getBytes());
					os.flush();
					os.close();
				}

				conn.connect();
			} catch (ConnectException e) {
				return new CustomResp(new Result("连接服务器失败"), null);
			}

			if (cookie == null) {
				// 获取Cookie
				cookie = conn.getHeaderField("Set-Cookie");
				if (cookie != null)
					cookie = cookie.split(";\40")[0];
				// System.out.println(cookie);
			}
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				CustomResp cr = new CustomResp(br.readLine(), br.readLine());
				br.close();
				return cr;
			} else {
				// System.out.println(code);
				return new CustomResp(new Gson().toJson(new Result("服务器响应异常(HTTP响应码:" + code + ")")), null);
			}
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	// 测试用
	public static void main(String[] args) {
		String json = "{\"uid\":0,\"type\":\"ADMIN\",\"username\":\"fujie\",\"password\":\"7c32841c18f635e5eed4bcf07afec757\"}";
		cookie = "JSESSIONID=76DABB69998ACC48522BDBD3F9894A16";
		CustomResp cr = new HttpCommon().doHttp("/test", "POST", json);
		System.out.println(cr.getResultJSON());
		System.out.println(cr.getObjectJSON());
	}
}
