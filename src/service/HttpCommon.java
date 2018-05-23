package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import model.Result;
class CustomResp{
	private String resultJSON;
	private String objectJSON;
	public CustomResp(String resultJSON ,String objectJSON) {
		this.resultJSON = resultJSON;
		this.objectJSON = objectJSON;
	}
	public String getResultJSON() {
		return resultJSON;
	}
	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}
	public String getObjectJSON() {
		return objectJSON;
	}
	public void setObjectJSON(String objectJSON) {
		this.objectJSON = objectJSON;
	}
	
}
public class HttpCommon {
	private static final String url = "http://localhost:8080"; 
	private static String sessionID = null;
	public CustomResp doHttp(String path ,String method, String data) {
		path = url + path;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
//			conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
//            conn.setRequestProperty("accept","application/json");
            if(method.equals("POST") && data != null) {
            	conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
            	
            	OutputStream os = conn.getOutputStream();
            	os.write(data.getBytes());
            	os.flush();
            	os.close();
            }
            conn.connect();
			if(sessionID == null) {
				
			}
			
			int code = conn.getResponseCode();
			if(code == 200) {
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				CustomResp cr = new CustomResp(br.readLine(), br.readLine());
				br.close();
				return cr;
			}else {
				System.out.println(code);
				return new CustomResp(new Gson().toJson(new Result("无法连接到服务器(HTTP响应码:"+code+")")), null);
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
	public static void main(String[] args) {
		String json = "{\"uid\":0,\"type\":\"ADMIN\",\"username\":\"fujie\",\"password\":\"7c32841c18f635e5eed4bcf07afec757\"}";
		CustomResp cr = new HttpCommon().doHttp("/login","POST",json);
		System.out.println(cr.getResultJSON());
		System.out.println(cr.getObjectJSON());
	}
}
