package hot.lib.trace;


import java.util.HashMap;

public class TraceTimes {
	
	protected static TraceTimes instance; 

	protected HashMap<String, TraceTime> mTraceMap = new HashMap<String, TraceTime>();
	
	protected TraceTimes() {
	}
	
	public static TraceTimes getInstance(){
		if(instance == null) instance = new TraceTimes();
		return instance;
	}
	
	public void startTrace(String key){
		mTraceMap.put(key, new TraceTime());
	}
	
	public long calcTrace(String key){
		return mTraceMap.get(key).calcTime();
	}
	
	public void printTrace(String key){
		System.out.println("TRACE TIME<"+key+">:"+calcTrace(key)+" msec");
	}

}
