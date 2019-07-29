package in.ocpjava.chapter.ten;
/**
 * Thread has method setPriority(int i)
 * 
 *
 */
public class PriorityThread {
	public static void main(String[] args) throws InterruptedException{
		Runnable job = () -> {
			System.out.println("I am "+Thread.currentThread().getName()+" and my priority is "+Thread.currentThread().getPriority()+".");
		};
		
		Thread martha = new Thread(job); martha.setName("Martha"); martha.setPriority(7);
		Thread joy = new Thread(job); joy.setName("Joy"); joy.setPriority(5);
		
		joy.start();
		martha.start();
		joy.join(); martha.join();
		System.out.println("Completing my execusion "+Thread.currentThread().getName());
	}
	
}
