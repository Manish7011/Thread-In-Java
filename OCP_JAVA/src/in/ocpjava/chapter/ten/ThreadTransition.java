package in.ocpjava.chapter.ten;

public class ThreadTransition {
	public static void main(String [] args) throws InterruptedException {
		Runnable job = () -> {
			// STATE : RUNNING
			System.out.println("Thread is in running state");
			
			try {
				// STATE : SLEEPING
				// Thread is in Sleeping mode for next 5 sec after executing sleep method 
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		/* STATE : NEW
		*  Thread is in new state
		*/
		Thread worker = new Thread(job);
		
		/*	STATE : RUNNABLE -> RUNNING
		*	Thread will go in runnable state once start method executes
		*/
		
		worker.start();
		worker.join();
		/*	STATE : DEAD
		 	Worker thread has completed it's execution but object is alive
		*/
		
		//worker.start(); // It will cause IllegalThreadStateException
		
	}
}
