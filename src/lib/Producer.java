package lib;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;

public class Producer implements Runnable {

	private static final int MAX_QUEUE = 5;
	private static final long DEFAULT_DELAY = 10;
	private final SecureRandom random = new SecureRandom();
	private final Queue<String> messages = new LinkedList<>();
	private int messageCount = 0;
	
	@Override
	public void run() {
		while (true) {
			try {
				putMessage();
				messageCount++;
				Thread.sleep(DEFAULT_DELAY);
			} catch (InterruptedException e) {
				System.out.println("Producer interrupted!");
				return;
			}
		}
	}

	public int getMessageCount() { return messageCount; }

	synchronized private void putMessage() throws InterruptedException {
		// FIXME: non thread-safe method
		/*while (messages.size() >= MAX_QUEUE) {
			Thread.sleep(100);
		}*/
                if(messages.size() >= MAX_QUEUE) this.wait();
                
		messages.add(new BigInteger(130, random).toString(32));
                this.notifyAll();
	}

	synchronized public String getMessage() throws InterruptedException {
		// FIXME: non thread-safe method
		/*while (messages.isEmpty()) {
			Thread.sleep(100);
		}*/
                if(messages.isEmpty()) this.wait();
		return messages.poll();
	}
}
