package in.ocpjava.chapter.ten;

public class RunnableFunctionInterface{
	static int count = 0;
	public static void main(String [] args) throws InterruptedException{
		/*
		 Implement function runnable interface through lambda
		 */
		Runnable run1 = () -> {
			for(int i=0;i<1000;i++) {
				synchronized(Class.class) {
					count++;
				}
			}
		};
		
		Thread t1 = new Thread(run1);
		Thread t2 = new Thread(run1);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(count);
	}
}
