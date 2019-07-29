package in.ocpjava.chapter.ten;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * Matrix Multiplication using Multi threading
 * @author Manish Bhadani
 * Single Thread: 
 * 	Time calculation: 15X15 -> 0.678612 ml
 *					500X500 -> 508.843988 ml
 *					1000X1000 -> 4829.55257 ml
 * Multithreading(3 Threads With 3 Cores):
 * Time Calculation: 15X15 -> 1.551026 ml
 * 					500X500 -> 296.793561 ml
 * 					1000X1000 -> 4081.079596
 */
public class MatrixMultiplication {
	public static final int N1 = 1000;
	public static final int M1 = 1000;
	public static final int N2 = 1000;
	public static final int M2 = 1000;
	static int one[][] = new int[N1][M1];
	static int two[][] = new int[N2][M2];
	static int ans[][] = new int[N1][M2];
	static AtomicInteger auto = null;
	/**
	 * Static block
	 */
	static {
		auto = new AtomicInteger();
		for(int x=0;x<N1;x++) {
			for(int y=0;y<M1;y++) {
				one[x][y] = (int) (Math.random()*1000);
			}
		}
		for(int x=0;x<N2;x++) {
			for(int y=0;y<M2;y++) {
				two[x][y] = (int) (Math.random()*1000);
			}
		}
	}
	/**
	 *Job
	 */
	Runnable run = () -> {
		int i=-1;
		i = auto.get();
		while(i < N1) {
			for(int j=0;j<M2;j++) {
				// M1 == N2
				for(int k=0;k<M1;k++) {
					ans[i][j] += one[i][k]*two[k][j];
				}
			}
		auto.getAndAdd(1);
		i = auto.get();
		}
	};
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args) throws InterruptedException{
		MatrixMultiplication mm = new MatrixMultiplication();
		Thread worker1 = new Thread(mm.run);
		Thread worker2 = new Thread(mm.run);
		Thread worker3 = new Thread(mm.run);
		
		long start = System.nanoTime();
		worker1.start();worker2.start();worker3.start();
		worker1.join();worker2.join();worker3.join();
		long time = System.nanoTime() - start;
		
		System.out.println(time/1e6);
		System.out.println("One");
		//print(one);
		System.out.println("Two");
		//print(two);
		System.out.println("Ans");
		//print(ans);
	}
	/*
	 * Print Matrix 
	 */
	public static void print(int[][] matrix) {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
