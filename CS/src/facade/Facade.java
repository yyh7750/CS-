package facade;

import facade.timer.Timer;
import facade.washingmachine.WashingMachine;

public class Facade {

	private WashingMachine washingMachine;
	private Timer timer;

	public Facade() {
		washingMachine = new WashingMachine();
		timer = new Timer();
	}

	public void on(int time) {
		try {
			washingMachine.on();
			timer.set(time);
			washingMachine.off();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
