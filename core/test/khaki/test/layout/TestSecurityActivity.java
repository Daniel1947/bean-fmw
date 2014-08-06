package khaki.test.layout;


import com.androidquery.AQuery;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import khaki.test.util.SecurityProcessor;
import khaki.testcase.MyTestSuite;
import peanut.khaki.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.test.AndroidTestRunner;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class TestSecurityActivity extends Activity {


	private Thread testRunnerThread;

	private static final int SHOW_RESULT = 0;
	private static final int ERROR_FIND = 1;
	private String strCode = "";
	private String strDecode = "";
	
	private AQuery mAq = new AQuery(TestSecurityActivity.this);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_security_activity);
		
		mAq.id(R.id.btnCode).text("加密").clicked(this, "clickCode");
		mAq.id(R.id.btnDecode).text("解密").clicked(this, "clickDecode");
	}
	
	public void clickCode(View button){
		String str = mAq.id(R.id.etUser).getText().toString();
		str="测试";
		if(str == null || str.length() == 0){
			return;
		}
		try {
			strCode = SecurityProcessor.encrypt(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mAq.id(R.id.etCode).text(strCode);
		Log.d("code","code:"+strCode);
	}
	public void clickDecode(View button){
		try {
			strDecode = SecurityProcessor.decrypt(strCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mAq.id(R.id.etDecode).text(strDecode);
		Log.d("decode","decode:"+strDecode);
	}
	
	public void LeftMenuClicked(View button){
        //update the text
		Intent intent = new Intent(TestSecurityActivity.this, MenuLeftOverlayActivity.class);
		startActivity(intent);
	}
	public void GreenDaoClicked(View button){
		Intent intent = new Intent(TestSecurityActivity.this, GreenDaoActivity.class);
		startActivity(intent);
	}

	private void showMessage(String message) {
		hander.sendMessage(hander.obtainMessage(ERROR_FIND, message));
	}

	private void showResult(String text) {
		hander.sendMessage(hander.obtainMessage(SHOW_RESULT, text));
	}

	private synchronized void startTest() {
		if (testRunnerThread != null && testRunnerThread.isAlive()) {
			testRunnerThread = null;
		}
		if (testRunnerThread == null) {
			testRunnerThread = new Thread(new TestRunner(this));
			testRunnerThread.start();
		} else {
			Toast.makeText(this, "Test is still running", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public Handler hander = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			default:
				break;
			}
		}
	};

	class TestRunner implements Runnable, TestListener {

		private Activity parentActivity;
		private int testCount;
		private int errorCount;
		private int failureCount;

		public TestRunner(Activity parentActivity) {
			this.parentActivity = parentActivity;
		}

		@Override
		public void run() {
			testCount = 0;
			errorCount = 0;
			failureCount = 0;

			MyTestSuite suite = new MyTestSuite();
			AndroidTestRunner testRunner = new AndroidTestRunner();
			testRunner.setTest(suite);
			testRunner.addTestListener(this);
			testRunner.setContext(parentActivity);
			testRunner.runTest();
		}

		public void addError(Test test, Throwable t) {
			errorCount++;
			showMessage(t.getMessage() + "\n");
		}

		public void addFailure(Test test, AssertionFailedError t) {
			failureCount++;
			showMessage(t.getMessage() + "\n");
		}

		public void endTest(Test test) {
			showResult(getResult());
		}

		public void startTest(Test test) {
			testCount++;
		}

		private String getResult() {
			int successCount = testCount - failureCount - errorCount;
			return "Test:" + testCount + " Success:" + successCount
					+ " Failed:" + failureCount + " Error:" + errorCount;
		}

	}

}