package in.ocpjava.chapter.ten;

/**
 * 
 * @author Manish Bhadani
 * Sample of Multi Threading
 * Nothing is guarantted except this "Each Thread will start, and each thread will run to completion"
 */
public class MultithreadingExample {
	public static void main(String[] args) {
		Runnable job = () -> {
			for(int x=0;x<400;x++)
				System.out.println("Run by "+Thread.currentThread().getName()+" x is "+x);
		};
		
		Thread fred = new Thread(job); fred.setName("Fred");
		Thread lucy = new Thread(job); lucy.setName("Lucy");
		Thread money = new Thread(job); money.setName("Money");
		
		fred.start();lucy.start();money.start();
	}
}
