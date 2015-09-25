package zystudio.demo.multigate;

import java.util.ArrayList;
import java.util.List;

public class ServerConnector {

	// 那个中转服务器的地址
	private static final String SERVER_URL = "http://sogou.com";
	// 所有组装在一起的
	private List<Segment> mSegments;

	private List<Segment> mResponseSegment;

	public void addSegment(Segment seg) {
		if (mSegments == null) {
			mSegments = new ArrayList<Segment>();
		}
		mSegments.add(seg);

	}

	public void connect() {

	}

	public interface OnSegmementResponseListener {
		public void onNewSegementSegment(Segment seg);
	}

	public interface OnRequestErrorSegment {
		public void onRequestError(String url);
	}

}
