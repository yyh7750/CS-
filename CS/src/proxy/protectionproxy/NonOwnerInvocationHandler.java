package proxy.protectionproxy;

import java.lang.reflect.*;

//호출 핸들러에 반드시 InvocationHandler 인터페이스를 구현해야 한다.
public class NonOwnerInvocationHandler implements InvocationHandler {
	IPerson person;

	public NonOwnerInvocationHandler(IPerson person) {
		this.person = person;
	}

	// 생성자로부터 전달받은 주제의 레퍼런스를 저장.
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {

		// getter 메소드라면 주제의 메소드를 호출한다.
		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(person, args);
			}
			// 괴짜 지수를 매긴다.
			else if (method.getName().equals("setGeekRating")) {
				return method.invoke(person, args);
			} 
			// 괴짜 지수를 매기는 것 외의 설정은 불가능.
			else if (method.getName().startsWith("set")) {
				throw new IllegalAccessException();
			}
		} 
		// 진짜 주제에서 예외를 던졌을 때
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 다른 메소드가 호출되면 널 리턴.
		return null;
	}
}