package khaki.testcase;

import peanut.khaki.common.Constant;
import peanut.khaki.util.PKFHttp;
import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

import com.androidquery.AQuery;

import junit.framework.TestCase;

/**
   DESCRIPTION
    <short description of component this file declares/defines>
	Test case for PKHttp.java

   PRIVATE CLASSES
    <list of private classes defined - with one-line descriptions>

   NOTES
    <other useful comments, qualifications, etc.>
   AUTHOR      (MM/DD/YY)
	Daniel     2013-9-20  - Creation
	
   MODIFIED    (MM/DD/YY)
    Daniel     2013-9-21 
 */
public class TestHttpUtil extends AndroidTestCase {

	private Context mContent;
	private AQuery mAq;
	
	public TestHttpUtil(){
	}
	@Override    
    protected void setUp() throws Exception {    
		mContent = getContext();
		mAq = new AQuery(mContent);
		System.out.print("init");
    }  

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAsynchronousRequest(){
		PKFHttp.getInstance().init(mContent,  mAq);
		PKFHttp.Result result = PKFHttp.getInstance().AsynchronousRequest(Constant.SERVER_IP+Constant.TEST_SERVER, PKFHttp.RESPONSE_TYPE.JSON);
		Log.d("test",result.getMsg());
	}

}
