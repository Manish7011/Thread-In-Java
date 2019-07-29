package in.ocpjava.chapter.ten;

public class AccountThreadProblem {
	Account account = new Account();
	class Account{
		private int balance = 50;
		public int getBalance() {
			return balance;
		}
		public void withdraw(int amount) {
			balance -= 10;
		}
	}
	Runnable job = () -> {
		for(int i=0;i<5;i++) {
			makeWithdrawal(10);
		}
	};
	public void makeWithdrawal(int amount) {
		if(account.getBalance() >= amount) {
			System.out.println(Thread.currentThread().getName()+" is going to withdraw.");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				
			}
			account.withdraw(10);
			System.out.println(Thread.currentThread().getName()+" completes the withdrawl.");
		}else {
			System.out.println("Not enough in account for "+
								Thread.currentThread().getName()+
								" to withdraw "+account.getBalance());
		}
	}
	public static void main(String[] args) throws InterruptedException{
		AccountThreadProblem atp = new AccountThreadProblem();
		Thread fred = new Thread(atp.job);Thread loy = new Thread(atp.job);
		fred.setName("Fred"); loy.setName("Loy");
		fred.start(); loy.start();
		fred.join(); loy.join();
	}
}
