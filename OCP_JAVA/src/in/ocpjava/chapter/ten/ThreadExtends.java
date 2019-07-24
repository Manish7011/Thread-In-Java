package in.ocpjava.chapter.ten;

public class ThreadExtends extends Thread{
	@Override
	public void run() {
		// New call stack execution start from here when start() method called on thread instance
		
		System.out.println("Running on new call stack by thread:"+Thread.currentThread().getName());
	}
	
	public static void main(String [] args) throws InterruptedException{
		//Thread t1 = new ThreadExtends();
		ThreadExtends t1 = new ThreadExtends();
		t1.setName("Thread-001");
		t1.start();
		t1.join();
		
		System.out.println("Thread execution has been completed.");
	}
}
