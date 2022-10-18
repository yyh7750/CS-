package proxy.protection;

// InvocationHandler는 java.lang.reflect 패키지에 있으므로 import
import java.lang.reflect.*;

// 호출 핸들러에 반드시 InvocationHandler 인터페이스를 구현해야 한다.
public class OwnerInvocationHandler implements InvocationHandler {
	IPerson person;

	// 생성자로부터 전달받은 주제의 레퍼런스를 저장.
	public OwnerInvocationHandler(IPerson person) {
		this.person = person;
	}

	// 프록시의 메소드가 호출될 때마다 호출되는 메소드
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {

		// getter 메소드라면 주제의 메소드를 호출한다.
		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(person, args);
			}
			// setGeekRating 메소드라면 IllegalAccessException 발생시킴.
			else if (method.getName().equals("setGeekRating")) {
				throw new IllegalAccessException();
			}
			// 괴짜지수가 아닌 메소드는 본인에겐 모두 허용해준다.
			else if (method.getName().startsWith("set")) {
				return method.invoke(person, args);
			}
		} 
		// 진짜 주제에서 예외를 던졌을 때
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 다른 메소드가 호출된다면 널 리턴.
		return null;
	}
}
