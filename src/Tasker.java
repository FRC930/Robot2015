import java.util.Timer;
import java.util.TimerTask;


public class Tasker {

	private Taskable task;
	private long period;
	private Timer timer;
	
	public Tasker(Taskable task, long period) {
		this.task = task;
		this.period = period;
		this.timer = new Timer();
	}
	
	public void start() {
		if(timer == null) {
			timer = new Timer();
			timer.schedule(new Task(task), 0L, period);
		}
	}
	
	public void stop() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
	
	private class Task extends TimerTask {
		
		private Taskable task;
		
		public Task(Taskable task) {
			if(task == null) {
				throw new NullPointerException("Taskable was null");
			}
			this.task = task;
		}

		@Override
		public void run() {
			task.perform();
		}
		
	}
}