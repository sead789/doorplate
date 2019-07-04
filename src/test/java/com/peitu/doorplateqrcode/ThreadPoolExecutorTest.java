package com.peitu.doorplateqrcode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Rising
 * @date 2019/6/14
 */
public class ThreadPoolExecutorTest {

    private static final Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(() -> System.out.println("执行线程" + finalI));
        }
    }

}
