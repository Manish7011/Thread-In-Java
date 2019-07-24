package in.ocpjava.chapter.ten;

public class DaemonThread extends Thread{
	@Override
	public void run() {
		if(Thread.currentThread().isDaemon()) {
			System.out.println("1. I am daemon Thread:"+Thread.currentThread().getName()+" with priority: "+Thread.currentThread().getPriority());
			for(int i=0;i<1000000;i++);
			/*
				If JVM finds running daemon thread, 
				it terminates the thread and after that shutdown itself. 
				JVM does not care whether Daemon thread is running or not.
				
				It is an utmost low priority thread
			*/
			System.out.println("2. I am daemon Thread:"+Thread.currentThread().getName()+" with priority: "+Thread.currentThread().getPriority());
		}else {
			System.out.println("1. I am user Thread:"+Thread.currentThread().getName()+" with priority: "+Thread.currentThread().getPriority());
		}
	}
	
	public static void main(String [] a) {
		DaemonThread d1 = new DaemonThread();
		
		Thread t1 = new Thread(d1);
		Thread t2 = new Thread(d1);
		
		t1.setDaemon(true);
		
		t2.start();
		t1.start();
		
		
	}
}