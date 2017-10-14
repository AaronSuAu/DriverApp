package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpPostDemo {
	private String url;
	private String header;
	private List<NameValuePair> urlParameters;
	private HttpClient client;
	private HttpPost post;
	private String jsonString;

	public HttpPostDemo(String url, String header, String jsonString) {
		super();
		this.url = url;
		this.header = header;
		this.jsonString = jsonString;
		StringEntity entity;
		try {
			entity = new StringEntity(jsonString);
			client = HttpClientBuilder.create().build();
			post = new HttpPost(url);
			post.setHeader("Authorization", header);
			post.setHeader("Content-type", "application/json");
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String sendPostRequest() {
		System.out.println(post.toString());
		System.out.println("Authorization: " + header + ", Content-type: application/json");
		System.out.println(jsonString);
		HttpResponse response;
		try {
			response = client.execute(post);
			return HttpResponseUtil.getResponse(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
