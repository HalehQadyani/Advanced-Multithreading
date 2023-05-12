package sbu.cs.Semaphore;

import java.util.Date;

import static java.lang.Thread.currentThread;

public class Resource {

    public static void accessResource() {
        System.out.println("Thread name: " + currentThread().getName() + " Time: " + new Date());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
