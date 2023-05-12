package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    private Semaphore S;
    public Operator(String name, Semaphore S) {
        super(name);
        this.S = S;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try {
                S.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            S.release();
        }
    }
}
