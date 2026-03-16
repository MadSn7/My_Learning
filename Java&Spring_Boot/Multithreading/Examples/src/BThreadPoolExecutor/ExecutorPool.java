package BThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" will now sleep");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+" now executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ExecutorPool {
    public static void main(String[] args) throws InterruptedException {
       ExecutorService executorService =  Executors.newFixedThreadPool(5);
       for (int i = 0; i < 8; i++) {
            executorService.submit(new MyRunnable());
       }
//       Thread.sleep(1100);
       executorService.shutdown();//graceful
//       executorService.shutdownNow();
    }
}
