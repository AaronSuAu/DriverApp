package response;

import java.util.List;

public class JsonResponse {
	private int code;
	private String description;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public JsonResponse(int code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	public JsonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
