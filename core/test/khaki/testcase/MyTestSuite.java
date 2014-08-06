package khaki.testcase;

import junit.framework.TestSuite;

public class MyTestSuite extends TestSuite {

	public MyTestSuite() {
		addTestSuite(MyTestCase.class);
		addTestSuite(TestHttpUtil.class);
	}
}