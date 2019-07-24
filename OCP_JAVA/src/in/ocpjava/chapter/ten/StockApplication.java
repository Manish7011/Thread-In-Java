package in.ocpjava.chapter.ten;

public class StockApplication {
	public static void main(String [] args) {
		String s1 = "abc";
		String s2 = new String("abc").intern();
		System.out.println(s2==s1);
	}
}
