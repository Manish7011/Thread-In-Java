# Thread-In-Java
Single and Multithreading Applications

----------------------**** PROJECT OCP_JAVA  ****----------------------

--------------------------*** CHAPTER 10 ***--------------------------

Use case
* Stock Broker Application: Where more than 2 jobs are running simultaneously. Downloading stock prices, Check Prices and Analysis.

Thread in Java
* Instance of class java.lang.Thread class : Lives and dies on Heap
* Thread of execution: It is an individual process(lightweight process) that has it's own call stack.
* One thread per call stack or call stack per a thread

		  Main Method 				  Thread 1 
		  Call Stack				  Call Stack
		|-------------|             |-------------|
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|             |             |             |
		|-------------|             |-------------|
		| static main |             | 	void run  |
		|-------------|             |-------------|
		
Execution on JVM
* Different JVMs can run threads in profoundly different ways. Round Robin for some time slice, Sequential manner

Daemon Thread: ** DaemonThread.java **
* Using setDaemon method user can make it daemon thread(before thread execution start). 
* If JVM finds running daemon thread, it terminates the thread and after that shutdown itself. 
* JVM does not care whether Daemon thread is running or not.				
* It is an utmost low priority thread


Making Thread: 

Two ways of instantiate Thread
* Extend the java.lang.Thread. Thread class implements Runnable interface.	** ThreadExtends.java **
* Implement the Runnable interface  ** RunnableInterface.java **
* In good OO design Runnable interface is preferred

Defining A Thread
* By extending Thread class: Limitation: You can not extend another class if required in future
* By Implementing Runnable Interface: Runnable is a functional interface. It has only one abstract method: ** RunnableFunctionInterface.java **


Instantiating Thread
* Every thread execution begins as an instance of Thread
* Thread is worker and Runnable is the job to be done
	- Create job with implemented runnable class
		class MyRunnable implements Runnable {	@Override public void run(){ // Job }}
	- Create instance of job
		Runnable job = new MyRunnable();
	- Create worker and assign the job
		Thread worker = new Thread(job);
* Directly assign job to worker:  ** WorkerAnonymousJob.java **
* Giving the same job or target to multiple thread means that several thread of execution will be running very same job

Overloaded Constructors in class Thread
Thread()
Thread(Runnable target)
Thread(Runnable target, String name)
Thread(String name)

Thread State: Just a brief. getState(instance method) and isAlive(native method) methods are there for thread state information.
* New: Thread t = new Thread(job);
* Runnable: t.start()
* Running: t.start() - Thread gets it's turn to perform given job
* Hold/Wait: Waiting for something
* Terminated: Done with its execution

Starting A Thread