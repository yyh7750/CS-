package proxy.common;

public class SubjectImpl implements ISubject {

	@Override
	public String request() {
		return "response";
	}
}
