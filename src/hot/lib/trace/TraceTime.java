package hot.lib.trace;


public class TraceTime {

	private long beforeTime;

	public TraceTime() {
		set();
	}
	
	public void set(){
		beforeTime = System.currentTimeMillis();
	}
	
	public void reset(){
		set();
	}
	
	public long calcTime(){
		long delta = System.currentTimeMillis() - beforeTime;
		return delta;
	}

}
