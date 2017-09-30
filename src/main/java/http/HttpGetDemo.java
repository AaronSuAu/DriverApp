package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpGetDemo {
	private String header;
	private String url;
	private HttpClient client;
	private HttpGet request;

	public HttpGetDemo(String header, String url) {
		this.header = header;
		this.url = url;
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		// add request header
		request.addHeader("Authorization", header);
	}

	public String sendGetRequest() {
		System.out.println("Request: " + request.toString());
		System.out.println("Header: Authorizaiton = " + header);

		try {
			HttpResponse response = client.execute(request);
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
