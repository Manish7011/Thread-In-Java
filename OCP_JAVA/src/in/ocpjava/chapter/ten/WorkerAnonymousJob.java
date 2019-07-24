package in.ocpjava.chapter.ten;

public class WorkerAnonymousJob {
	public static void main(String [] args) throws InterruptedException{
		Thread t = new Thread(() -> {
			// Write you job code here
			System.out.println("I am anonymous job.");
		});
		
		t.start();
		t.join();
	}
}
