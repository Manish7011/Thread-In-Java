package in.ocpjava.chapter.ten;

public class ThreadNames implements Runnable{
	@Override
	public void run() {
		// currentThread() is static method which returns refence of executing thread
		System.out.println("I am "+Thread.currentThread().getName()+" with ID "+Thread.currentThread().getId());
	}
	
	public static void main(String[] args) throws InterruptedException{
		// New State - Start
		Runnable job = new ThreadNames();
		Thread mars = new Thread(job);
		Thread moon = new Thread(job);
		
		System.out.println(Thread.currentThread().getName());	// That's right main thread already has a name--main
		// Even if you don't explicit a name, it still has a name
		mars.setName("Mars");
		moon.setName("Moon");
		// New State - Ends
		mars.start();
		moon.start();
		
		mars.join();
		moon.join();
		
		System.out.println("Everything is done");
		
		
	}
}
