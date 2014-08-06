package khaki.test.layout;


import com.androidquery.AQuery;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import khaki.testcase.MyTestSuite;
import peanut.khaki.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.test.AndroidTestRunner;
import android.view.View;
import android.widget.Toast;

public class TestMainActivity extends Activity {


	private Thread testRunnerThread;

	private static final int SHOW_RESULT = 0;
	private static final int ERROR_FIND = 1;
	
	private AQuery mAq = new AQuery(TestMainActivity.this);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main_activity);
		
		mAq.id(R.id.btn_1).text("Left Menu").clicked(this,"LeftMenuClicked");
		mAq.id(R.id.btn_2).text("Green Dao").clicked(this, "GreenDaoClicked");
	}
	public void LeftMenuClicked(View button){
        //update the text
		Intent intent = new Intent(TestMainActivity.this, MenuLeftOverlayActivity.class);
		startActivity(intent);
	}
	public void GreenDaoClicked(View button){
		Intent intent = new Intent(TestMainActivity.this, GreenDaoActivity.class);
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