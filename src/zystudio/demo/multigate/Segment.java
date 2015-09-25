package zystudio.demo.multigate;

import java.util.HashMap;

import android.text.TextUtils;

public class Segment {

	//这个我要使用httpclient中的那个用法了
	private String method="Get";
    
    //非打包请求时,所使用的那个URL
	private String mUrl;
    
	private HashMap<String,String>  headers;
    //这个body可能有多种样式,比如很大的数据,所以后期可以写个bodyWrapper这样的,或者抽象一下接口getByte[] 最好, 因为可能为外储中的
	private byte[] body;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public byte[] getBody() {
		return body;
	}
    
	public void setBody(byte[] body) {
		this.body = body;
	}

	public void addHeader(String key,String value){
		if(TextUtils.isEmpty(key)||TextUtils.isEmpty(value)){
			return ;
		}
		if(headers==null){
			headers=new HashMap<String,String>();
		}
		headers.put(key, value);
	}



}
