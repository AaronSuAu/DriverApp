package http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpPutDemo {
	private String url;
	private String header;
	private List<NameValuePair> urlParameters;
	private HttpClient client;
	private HttpPut put;
	private String jsonString;

	public HttpPutDemo(String url, String header, String jsonString) {
		super();
		this.url = url;
		this.header = header;
		this.jsonString = jsonString;
		StringEntity entity;
		try {
			entity = new StringEntity(jsonString);
			client = HttpClientBuilder.create().build();
			put = new HttpPut(url);
			put.setHeader("Authorization", header);
			put.setHeader("Content-type", "application/json");
			put.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String sendPutRequest() {
		System.out.println(put.toString());
		System.out.println("Authorization: " + header + ", Content-type: application/json");
		System.out.println(jsonString);
		HttpResponse response;
		try {
			response = client.execute(put);
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
