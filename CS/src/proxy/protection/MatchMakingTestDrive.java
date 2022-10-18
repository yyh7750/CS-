package proxy.protection;

import java.lang.reflect.*;
import java.util.*;

public class MatchMakingTestDrive {
	HashMap<String, IPerson> datingDB = new HashMap<String, IPerson>();

	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}

	// 회원 초기화
	public MatchMakingTestDrive() {
		initializeDatabase();
	}

	public void drive() {
		// 인물 정보를 가져온다.
		IPerson joe = getPersonFromDatabase("김자바");
		// 본인용 프록시 생성
		IPerson ownerProxy = getOwnerProxy(joe);
		System.out.println("이름: " + ownerProxy.getName());
		ownerProxy.setInterests("볼링, 바둑");
		System.out.println("본인 프록시에 관심 사항을 등록합니다.");
		try {
			// 괴짜 지수 설정 시도. (본인이 설정 불가능!!!)
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("본인 프록시에는 괴짜 지수를 매길 수 없습니다.");
		}
		System.out.println("괴짜 지수: " + ownerProxy.getGeekRating());

		// 타인용 프록시 생성
		IPerson nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("이름: " + nonOwnerProxy.getName());
		try {
			// setter 메소드 호출. (불가능!!!)
			nonOwnerProxy.setInterests("볼링, 바둑");
		} catch (Exception e) {
			System.out.println("타인 프록시에는 관심 사항을 등록할 수 없습니다.");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("타인 프록시에 괴짜 지수를 매깁니다.");
		System.out.println("괴짜 지수: " + nonOwnerProxy.getGeekRating());
	}

	// IPerson 객체(진짜 주제)를 인자로 받아오고 프록시를 리턴.
	IPerson getOwnerProxy(IPerson person) {
		// 프록시를 생성한다.
		return (IPerson) Proxy.newProxyInstance(//
				person.getClass().getClassLoader(), // 
				person.getClass().getInterfaces(), //
				new OwnerInvocationHandler(person));
	}

	// IPerson 객체(진짜 주제)를 인자로 받아오고 프록시를 리턴.
	IPerson getNonOwnerProxy(IPerson person) {
		// 프록시를 생성한다.
		return (IPerson) Proxy.newProxyInstance(//
				person.getClass().getClassLoader(), //
				person.getClass().getInterfaces(), //
				new NonOwnerInvocationHandler(person));
	}

	IPerson getPersonFromDatabase(String name) {
		return (IPerson) datingDB.get(name);
	}

	void initializeDatabase() {
		IPerson joe = new PersonImpl();
		joe.setName("Joe Javabean");
		joe.setInterests("cars, computers, music");
		joe.setHotOrNotRating(7);
		datingDB.put(joe.getName(), joe);

		IPerson kelly = new PersonImpl();
		kelly.setName("Kelly Klosure");
		kelly.setInterests("ebay, movies, music");
		kelly.setHotOrNotRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
}