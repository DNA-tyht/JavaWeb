package com.DNA.thread;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/5/18 12:29
 */
public class ThreadLocalTest {
    public static Map<String, Object> data = new ConcurrentHashMap<String, Object>();
    private static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            int i = random.nextInt(1000);
            String name = Thread.currentThread().getName();
            System.out.println("Thread " + name + " number is: " + i);
            data.put(name, i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = data.get(name);
            System.out.println(o);
        }
    }
}
