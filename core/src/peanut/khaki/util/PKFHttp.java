
package peanut.khaki.util;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus; 


/**
   DESCRIPTION
    <short description of component this file declares/defines>

   PRIVATE CLASSES
    <list of private classes defined - with one-line descriptions>
	

   NOTES
    <other useful comments, qualifications, etc.>
   AUTHOR      (MM/DD/YY)
	Daniel     2013-9-20  - Creation
	
   MODIFIED    (MM/DD/YY)
    Daniel     2013-9-21 
 */
public class PKFHttp{

	private Result questResult = new Result();
	private static PKFHttp instance;
	private AQuery aq;
	private Context content;
	private String url;
	
	private PKFHttp(){}
	
	public static PKFHttp getInstance(){
		if(instance == null){
			instance = new PKFHttp();
			return instance;
		}else{
			return instance;
		}
	}
	
	public void init(Context content, AQuery aq){
		this.content = content;
		this.aq = aq;
	}
	
	public enum RESPONSE_TYPE{
		JSON,
	}
	
	public enum REQUEST_RESULT{
		SUCCESS,
		FAILED
	}
	public static class Result{
		private REQUEST_RESULT code ;
		private String msg;
		
		public Result(){}
		public void setCode(REQUEST_RESULT code){
			this.code = code;
		}
		public void setMsg(String msg){
			this.msg = msg;
		}
		public String getMsg(){
			return this.msg;
		}
		public REQUEST_RESULT getCode(){
			return this.code;
		}
	}

	public static String Post(){
		String result = "";
		return result;
	}
	
	/**
	 * 
	 * @Title: AsynchronousRequest
	 * @Description: TODO
	 * @param  url 
	 * @param  type
	 * @return Object return result
	 * @throws null
	 */
	public Result AsynchronousRequest(String url, RESPONSE_TYPE type){
		this.aq = aq; 
		this.url = url;
		this.content = content;
		
		switch (type) {
		case JSON:
			asyncJson();
			break;

		default:
			break;
		}
		
		return questResult;
	}
	
	private void asyncJson() {

		AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
		final AQuery aq = new AQuery(this.content);
		cb.url(this.url).type(JSONObject.class);
		aq.sync(cb);

		JSONObject json = cb.getResult();
		AjaxStatus status = cb.getStatus();
		if (json != null) {
			Log.d("test", json.toString());
			questResult.setCode(REQUEST_RESULT.SUCCESS);
			questResult.setMsg(json.toString());
		} else {
			Log.d("test", json.toString());
			questResult.setCode(REQUEST_RESULT.FAILED);
			questResult.setMsg("Error:" + status.getCode());
		}
	}
	
}
