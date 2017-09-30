package http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpDeleteDemo {
	private String header;
	private String url;
	private HttpClient client;
	private HttpDelete request;

	public HttpDeleteDemo(String header, String url) {
		this.header = header;
		this.url = url;
		client = HttpClientBuilder.create().build();
		request = new HttpDelete(url);

		// add request header
		request.addHeader("Authorization", header);
	}

	public String sendDeleteRequest() {
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
