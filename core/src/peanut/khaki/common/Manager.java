package peanut.khaki.common;

import com.androidquery.AQuery;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Manager extends Service{

	private final IBinder binder = new MyBinder();
	private AQuery aq = new AQuery(Manager.this);
	
	public class MyBinder extends Binder{
		public void show() {
		}

		public Manager getService() {
			return Manager.this;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return binder;
	}
}
