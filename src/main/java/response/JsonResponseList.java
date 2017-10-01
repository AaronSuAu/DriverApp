package response;

import java.util.List;


public class JsonResponseList<T> extends JsonResponse{

	private List<T> list;

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public JsonResponseList(int code, String description, List<T> list) {
		super(code, description);
		this.list = list;
	}
	public JsonResponseList() {
		super();
	}
	
	
}
