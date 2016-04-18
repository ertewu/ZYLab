package zystudio.demo.multigate2;

import java.util.HashMap;

public class Request {

	private int Id;

	private String mUrl;

	private HashMap<String, String> headers;
	// 这个body可能有多种样式,比如很大的数据,所以后期可以写个bodyWrapper这样的,或者抽象一下接口getByte[] 最好,
	// 因为可能为外储中的
	private byte[] body;

	private Response mResponse;


}
