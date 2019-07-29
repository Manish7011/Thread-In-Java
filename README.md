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
		  Call Stack A				  Call Stack B
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
		| static main |             |   void run  |
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

Starting A Thread: t.start()	: lowercase t is referring to the thread of execution rather than Thread class
* A new thread of execution starts(with a new call stack)
* The thread moves from the new state to the runnable state
* When the thread gets a chance to execute, its target run() method will run

Thread t = new Thread();
t.run()					// Legal, but does not start a new thread

setName() getName() methods on Thread object: ** ThreadNames.java **

Thread.currentThread() -> currentThread is static method, which returns a reference to the currently executing thread

The behavior is not guaranteed.

Multithreading Example By Counting Numbers: ** MultithreadingExample.java **
* Nothing is guaranteed except this "Each Thread will start, and each thread will run to completion"
* Order is not guaranteed by scheduler

A thread is done being a thread when its target run() method completes
* The stack for that thread dissolves
* Thread is considered dead/terminated
* You can call other method but not start() again

Once a thread has been started, it can never be started again
* IllegalThreadStateException

Thread ID: ** ThreadNames.java **
* getId(): long number


Thread Scheduler
* The thread scheduler is part of JVM
* TS that decides which thread – of all that are eligible – will actually run
* The order in which runnable threads are chosen to run is not guaranteed

Although we don't control the thread scheduler, we can sometimes influence it with some methods.

Methods from the java.lang.Thread class
* public static void sleep(long millis) throws InterruptedException  // Overloaded versions are there
* public static void yield()

* public final void join() throws InterruptedException // Overloaded versions are there
* public final void setPriority(int newPriority)

Methods from the java.lang.Object class: it's being called from synchronize code
* public final void wait() throws InterruptedException
* public final void notify()
* public final void notifyAll()

Thread States and Transitions
* We have seen: Runnable, Running and Dead states
* When run method completes thread moves from the running state directly to dead state(terminated)

THREAD STATES (Five): ** ThreadTransition.java **
* New: Thread object is created but start method has not been invoked on it. Live thread object
* Runnable: Thread enters into runnable when the start method is invoked. It(alive thread) is available in runnable thread pool and eligible for run. 
	- New -> Runnable
* Running: Where the action is. Thread scheduler selects it from the runnable pool to be the currently executing process.
	- Runnable -> Running, Running -> Runnable
* Waiting/Blocked/Sleeping: All three are same with common concept "thread is not eligible to run".
	- Running -> Waiting, Waiting -> Runnable, Runnable -> Running
	- There are several ways to get into this state
		1. A thread may blocked because it's waiting for the resource
		2. A thread may be sleeping because the thread's run code tells it to sleep for some period of time
		3. A thread may be waiting because the thread's code causes it to wait
	- One thing is for sure that one thread does not tell another thread to block
	- Thread is blocked but it's alive
* Dead: A thread is dead when it's run() method completes.
	- A thread object is still viable but it is no longer a separate thread of execution.
	- Once a thread is dead, it can never be brought back to life
	- If you invoke start() on a dead thread instance, it will raise "IllegalThreadStateException"
	- It's no longer considered alive
	
					Waiting/Blocked/Sleeping
                                      ^					
                       /               \
                      *
			NEW --> RUNNABLE -------> RUNNING --> DEAD
			
Preventing Thread Execution
* Sleeping * Waiting * Blocked  because it needs an object's lock

* Sleeping
	- sleep() method is static method of class Thread, one thread can not put another thread to sleep
	- use it in your code to slow a thread down
	- Thread.sleep(long millis) throws InterruptedException
	- sleep is best way to help all threads get a chance to run
	- when sleep call, it must go to sleep for at least the specified number of milliseconds
	- RUNNING -> SLEEP -> RUNNABLE -> RUNNING
* Thread Priorities and yield()
	- The scheduler in most JVMs uses preemptive, priority-based scheduling - which implies some sort of time slicing
	- The lower priority running thread usually will be bumped back to runnable and the highest-priority thread will be chosen to run.
	- In most cases, the running thread will be of equal or greater priority than the highest-priority thread in pool.
	- Don't rely on thread priority when designing your multithreaded application. Because thread scheduling priority is not guaranteed. it's better to avoid modifying thread priorities. 
	- A scheduler might do one of following things(among other things)
		a. Pick thread to run, and run it there until it blocks or completes.
		b. Time-slice the threads in the pool to give everyone an equal opportunity to run.

* Setting a Thread's priority: ** PriorityThread.java **
	- public final void setPriority(int newPriority) 
	- public final static int MIN_PRIORITY = 1;  There are two more MAX_PRIORITY and NORM_PRIORITY with value 10 and 5 respectively
	
* Yield() method
	- public static native void yield();
	- It supposed to do is make the currently running thread head back to runnable to allow other threads of the same priority to get their turn.
	- That will promote graceful turn-taking among equal-priority threads
	- Yield will cause a thread to go from running to runnable
	- There's no guarantee the yielding thread won't just be chosen again over all the others!
	
* Join() method ** you will find use of join method in almost every examples **
	- public final void join() throws InterruptedException
	- Class Thread lets one Thread "join onto the end" of another thread
	- t.join() -> join me(current thread) to the end of t, so that t must finish before I(current thread) can run again
	- public final synchronized void join(long millis) throws InterruptedException -> Wait until thread t is done, but if it takes longer than 5000 milliseconds, then stop waiting and become runnable anyway.
	- It works on live thread like runnable or running or sleeping(not on new)

Synchronizing Code, Thread Problems
* Account overdrawan problem
* Multiple access of a single account ** AccountThreadProblem.java **
* Race condition-> Where multiple threads can access the same resource (object)

Preventing the account overdraw
*