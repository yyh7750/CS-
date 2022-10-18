package facade.washingmachine;
public class WashingMachine implements IWashingMachine {

	public WashingMachine() {
	}
	
	@Override
	public void on() {
		System.out.println("세탁기의 전원이 켜졌습니다.");
	}
	
	@Override
	public void off() {
		System.out.println("세탁기의 전원이 꺼졌습니다.");
	}
}