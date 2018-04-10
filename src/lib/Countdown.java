package lib;

import java.util.Random;

public class Countdown implements Runnable {
    private String name;
    private int limit;
    
    public Countdown(String name, int limit){
        
        this.name = name;
        this.limit = limit;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            
            for(int i = 0; i < limit; ++i){
                System.out.println(name + ": " + i);
                Thread.sleep(rand.nextInt(990)+10);
            }
        } catch (InterruptedException ex) {
            System.out.println("End of thread " + name);
        }
        
    }
    
}
