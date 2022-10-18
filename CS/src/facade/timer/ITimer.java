package facade.timer;

public interface ITimer {

	public void set(int time) throws InterruptedException;

	public void end();
}
