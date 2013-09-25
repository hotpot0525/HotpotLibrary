package hot.lib.trace.android;

import hot.lib.trace.TraceTimes;
import android.util.Log;


public class AndroidTraceTimes extends TraceTimes{

	private AndroidTraceTimes() {
	}
	
	public static TraceTimes getInstance(){
		if(instance == null) instance = new AndroidTraceTimes();
		return instance;		
	}

	@Override
	public void printTrace(String key) {
		Log.e("TRACE TIME","<"+key+">:"+mTraceMap.get(key).calcTime()+" msec");
	}


}
