import lib.Consumer;
import lib.Countdown;
import lib.Producer;
import lib.Counter;

public class Main {

    public static void main(String[] args) {
        try {
            example1();
            example2();
            example3();
            example4();
        }
        catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }
    
    
    public static void example1() {
        try {
            Thread t1 = new Thread(new Countdown("t1", 10));
            t1.start();
            t1.join();
            System.out.println("End of example1.1");
        }
        catch(InterruptedException e){
            System.out.println("End of example1.1");
        }
        
        Thread t1 = new Thread(new Countdown("t1", 10));
        t1.start();
        while(t1.isAlive() != false) {}
        System.out.println("End of example1.2");
    }
    
    public static void example2() {
        try {
            Thread racer1 = new Thread(new Countdown("racer 1", 20));
            Thread racer2 = new Thread(new Countdown("racer 2", 20));
            racer1.start();
            racer2.start();
            racer1.join();
            racer2.join();
            System.out.println("Race is over");
        }
        catch(InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void example3() {
        try {
            Counter counter = new Counter();
            
            Thread t1 = new Thread(()-> {
                for(int i=0; i<1000; ++i) {
                    counter.add(i);
                }
            });
            
            Thread t2 = new Thread(()-> {
                for(int i=0; i<1000; ++i) {
                    counter.add(i);
                }
            });
            
            Thread t3 = new Thread(()-> {
                for(int i=0; i<1000; ++i) {
                    counter.add(i);
                }
            });
            
            t1.start();
            t2.start();
            t3.start();
            
            t1.join();
            t2.join();
            t3.join();
            
            System.out.println("Counter's value after execute in 3 threads: "
                    + counter.getCount());
            
            Counter counter2 = new Counter();
            
            
            for(int i=0; i<1000; i++) {
                counter2.add(i);
            }

            for(int i=0; i<1000; i++) {
                counter2.add(i);
            }

            for(int i=0; i<1000; i++) {
                counter2.add(i);
            }
            System.out.println("Counter's value after execute in 1 thread: "
                    + counter.getCount());
        }
        catch(Throwable e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void example4() throws InterruptedException {
        Producer producer = new Producer();
        Consumer consumer1 = new Consumer("Consumer 1 ", producer);
        Consumer consumer2 = new Consumer("Consumer 2 ", producer);

        Thread p = new Thread(producer);
        Thread c1 = new Thread(consumer1);
        Thread c2 = new Thread(consumer2);

        p.start();
        c1.start();
        c2.start();

        Thread.sleep(3000);
        p.interrupt();
        p.join();
        System.out.println("\nProduced " + producer.getMessageCount()
                          + " messages.");

        Thread.sleep(500);
        c1.interrupt();
        c2.interrupt();
        c1.join();
        c2.join();
        System.out.println("\nConsumed " + (consumer1.getMessageCount()
                          + consumer2.getMessageCount()) + " messages.");

        System.out.println("\nMain thread stopped.");
    }
}
