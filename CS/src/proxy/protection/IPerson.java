package proxy.protection;

public interface IPerson {

	// 이름
	String getName();

	// 성별
	String getGender();

	// 관심사항
	String getInterests();

	// 괴짜 지수
	int getGeekRating();

	void setName(String name);

	void setGender(String gender);

	void setInterests(String interests);

	void setHotOrNotRating(int rating);
}
