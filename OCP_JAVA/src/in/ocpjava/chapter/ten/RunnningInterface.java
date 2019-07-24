package in.ocpjava.chapter.ten;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnningInterface {
	public static void main(String [] args) throws InterruptedException{
		Animal animal = new Animal();
		Thread t1 = new Thread(animal);
		Thread t2 = new Thread(animal);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(Animal.getCount());
	}
}

class Animal implements Runnable{
	static private AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public void run() {
		for(int i=0;i<10000;i++) {
				count.addAndGet(1);
		}
	}
	public static int getCount() {
		return count.get();
	}
}
