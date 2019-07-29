package in.ocpjava.chapter.ten;

/*
 * Sleep is best way to help all thread get a chance to run!
 * */
public class SleepMethod {
	Runnable job = () -> {
		int N = 100;
		for(int i=1;i<=N;i++) {
			if(i%10 == 0) {
				System.out.println("I: "+i);
			}
			System.out.println("Thread is run by "+Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public static void main(String [] args) throws InterruptedException{
		SleepMethod sm = new SleepMethod();
		Thread joy = new Thread(sm.job); joy.setName("Joy");
		Thread monica = new Thread(sm.job); monica.setName("Monica");
		Thread linda = new Thread(sm.job); linda.setName("Linda");
		
		joy.start();monica.start();linda.start();
		joy.join();monica.join();linda.join();
	}
}
