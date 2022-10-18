package proxy.common;

public class Main {

	public static void main(String[] args) {
		
		ISubject subject = new Proxy();
		System.out.println(subject.request());
	}
}