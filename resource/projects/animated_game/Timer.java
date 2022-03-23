public class Timer {
  private final long start, now;
  private transient Thread slave = new Thread();
  
  public Timer(long timeStart) {
    this.start = timeStart;
    now = 0.0L;
  }
  
  public Timer() {
    this.start = System.currentTimeMillis(); 
  }
  
  public void start() {
    slave = new Thread(()-> {
        while(true) {
          if(now == 0.0L) 
             now = System.currentTimeMillis();
          now -= start;
        }
    });
  }
  
  public synchronized String toString() {
    return now + " ms"; 
  }
  
}
