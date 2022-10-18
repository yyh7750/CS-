package proxy.common;

public class Proxy implements ISubject {

	private final SubjectImpl subject = new SubjectImpl();
	
	@Override
	public String request() {
		// 실제 메소드 호출
		return subject.request();
	}
}