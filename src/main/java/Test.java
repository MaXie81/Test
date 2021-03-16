import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Thread t2 = new Thread(() -> {
            Test obj = new Test();
            try {
                obj.m(Thread.currentThread());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        });
        t2.start();
    }
    void m(Thread tr) {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                tr.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("1");
    }
}
