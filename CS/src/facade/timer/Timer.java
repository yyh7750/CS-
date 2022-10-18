package facade.timer;

public class Timer implements ITimer{
	
	public Timer() {
	}

	@Override
	public void set(int time) throws InterruptedException {
		System.out.println(time + "시간(초)이 설정되었습니다. 세탁이 시작됩니다.");
		
		while(time > 0) {
			System.out.println("종료까지 " + time + "시간(초) 남았습니다.");
			Thread.sleep(1000);
			time--;
		}
		
		end();
	}

	@Override
	public void end() {
		System.out.println("세탁이 완료되었습니다.");
	}
}