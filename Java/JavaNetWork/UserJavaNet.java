package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class UserJavaNet {

	private static final String ADDRESS = "http://baidu.com";
	private static final String URL_ADDRESS = "http://fanyi.youdao.com/openapi.do?keyfrom=testuse&key=1422434379&type=data&doctype=json&version=1.1&q=good";
	private static final String URL_ADD = "http://fanyi.youdao.com/openapi.do";

	public static void main(String[] args) {
		useGetMethod();
		usePostMethod();
		useHttpClientGet();
		useHttpClientPost();
	}

	private static void useHttpClientPost() {
		try {
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(URL_ADD);
			List<BasicNameValuePair> parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("keyfrom", "testuse"));
			parameters.add(new BasicNameValuePair("key", "1422434379"));
			parameters.add(new BasicNameValuePair("type", "data"));
			parameters.add(new BasicNameValuePair("doctype", "json"));
			parameters.add(new BasicNameValuePair("version", "1.1"));
			parameters.add(new BasicNameValuePair("q", "good"));
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			System.out.println(content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void useHttpClientGet() {
		try {
			HttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(URL_ADDRESS);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			System.out.println(content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void usePostMethod() {
		try {
			URL url = new URL(URL_ADD);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			outputStream
					.write("keyfrom=testuse&key=1422434379&type=data&doctype=json&version=1.1&q=good"
							.getBytes("UTF-8"));
			outputStream.close();
			InputStream in = connection.getInputStream();
			InputStreamReader inr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(inr);
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			System.out.println(buffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void useGetMethod() {
		try {
			URL url = new URL(ADDRESS);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			InputStreamReader inr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(inr);
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			System.out.println(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
