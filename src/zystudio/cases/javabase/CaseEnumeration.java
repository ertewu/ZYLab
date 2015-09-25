package zystudio.cases.javabase;

import java.util.Enumeration;

public class CaseEnumeration {

	public static void work() {

	}

	public static class MyBean {

	}

	public static class MyEnumerator implements Enumeration<MyBean> {

		@Override
		public boolean hasMoreElements() {
			return false;
		}

		@Override
		public MyBean nextElement() {
			return null;
		}

	}

}
