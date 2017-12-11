package zystudio.demo.multigate2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerConnector {

	// 那个中转服务器的地址
	private static final String SERVER_URL = "http://sogou.com";
	// 所有组装在一起的
	private List<Request> mRequestList;

	private HashMap<String, String> mBaseHeader;
    
	private  OnResponseListener  mResponseListener;

	private void addHeader(String key, String value) {

	}

	public void addRequest(Request seg) {
		if (mRequestList == null) {
			mRequestList = new ArrayList<Request>();
		}
		mRequestList.add(seg);
	}

	public void connect() {
//        InputStream in;
//        byte[]  data;
//        //下载数据伪代码
//        while(in.read()){
//        	in.read(data);
//            
//        }
	}

	public interface OnResponseListener {
		public void onNewResponse(Response rep);
	}


}
