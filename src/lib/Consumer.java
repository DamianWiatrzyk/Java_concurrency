package lib;

public class Consumer implements Runnable {

	private static final long DEFAULT_DELAY = 50;
	private final Producer producer;
	private final String name;
	private int messageCount = 0;

	public Consumer(String nazwa, Producer producent) {
		this.producer = producent;
		this.name = nazwa;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String msg = producer.getMessage();
				if (msg != null) {
					System.out.println(name + " received a message: " + msg);
					messageCount++;
				} else {
					System.err.println("Failed to receive a message!");
				}
				Thread.sleep(DEFAULT_DELAY);
			} catch (InterruptedException e) {
				System.out.println(name + " interrupted!");
				return;
			}
		}
	}

	public int getMessageCount() { return messageCount; }
}
