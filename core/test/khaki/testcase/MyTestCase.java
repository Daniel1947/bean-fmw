package khaki.testcase;

import android.test.AndroidTestCase;
import android.util.Log;

public class MyTestCase extends AndroidTestCase{

	private int i1;    
    private int i2;    
    static final String LOG_TAG = "MathTest";    
        
    @Override    
    protected void setUp() throws Exception {    
        i1 = 2;    
        i2 = 3;    
    }    
        
    public void testAdd() {    
        assertTrue("testAdd failed", ((i1 + i2) == 5));    
    }    
        
	public void testDec() {    
        assertTrue("testDec failed", ((i2 - i1) == 1));   
    }   
	public void testTest() {    
    }  
    @Override    
    protected void tearDown() throws Exception {    
        super.tearDown();    
    }    
    
    @Override    
    public void testAndroidTestCaseSetupProperly() {    
        super.testAndroidTestCaseSetupProperly();    
    }    
}
