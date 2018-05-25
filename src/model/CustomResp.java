package model;
import com.google.gson.Gson;

public class CustomResp{
	private String resultJSON;
	private String objectJSON;
	public CustomResp(String resultJSON ,String objectJSON) {
		this.resultJSON = resultJSON;
		this.objectJSON = objectJSON;
	}
	public CustomResp(){

	}
	@Override
	public String toString(){
		return this.resultJSON+"\n"+objectJSON;
	}

	public String getResultJSON() {
		return resultJSON;
	}
	public void setResultJSON(Result result) {
		this.resultJSON = new Gson().toJson(result);
	}
	public String getObjectJSON() {
		return objectJSON;
	}
	public void setObjectJSON(Object object) {
		this.objectJSON = new Gson().toJson(object);
	}
	
}
