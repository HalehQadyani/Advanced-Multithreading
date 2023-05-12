package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator {
    public static class CalculatePi implements Runnable{
    MathContext MC;
    int n;

    public CalculatePi(int precision, int n){
        this.MC = new MathContext(100);
        this.n = n;
    }
    @Override
    public void run() {
        BigDecimal sign = new BigDecimal(1);
        if (n % 2 == 0){
            sign = new BigDecimal(-1);
        }
        BigDecimal numerator = new BigDecimal(1);
        numerator = numerator.multiply(sign, MC);
        BigDecimal dominator = new BigDecimal(2 * n - 1);
        BigDecimal x = numerator.divide(dominator, MC);
        add(x);
    }
}
    public static BigDecimal result;
    public static synchronized void add(BigDecimal n){
        result = result.add(n);
    }


    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     Experiment with different algorithms to find accurate results.

     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.

     */

    public static String calculate(int floatingPoint)
    {
        ExecutorService TP = Executors.newFixedThreadPool(4);
        result = new BigDecimal(0);
        for (int i = 1; i <= 1000; i++){
            CalculatePi pi = new CalculatePi(floatingPoint, i);
            TP.execute(pi);
        }
        TP.shutdown();

        try {
            TP.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = result.multiply(new BigDecimal(4));
        result = result.setScale(floatingPoint, RoundingMode.DOWN);
        return result.toString();
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself
    }
}
