package CSyncronization;

import java.util.concurrent.atomic.AtomicInteger;

class NonSyncRunnable implements Runnable {
//    volatile int val = 0;
    AtomicInteger counter = new AtomicInteger(0); //makes it thread safe
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            val = val + 1;
            counter.incrementAndGet();

            System.out.println();
//            System.out.println(Thread.currentThread().getName() + " " + counter.get());
        }
    }
    int getVal() {
        return counter.get();
//        return val;
    }
}

class SyncRunnable implements Runnable {
    int val = 0;
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            Thread.sleep(1000);
            synchronized (this) {
                val = val + 1;
                System.out.println(Thread.currentThread().getName() + " " + val);
            }
        }
    }
    int getVal() {
        return val;
    }
}
public class SyncExample {
    public static void main(String[] args) throws InterruptedException {
//        NonSyncRunnable nonSyncRunnable = new NonSyncRunnable();
//        new Thread(nonSyncRunnable).start();
//        new Thread(nonSyncRunnable).start();
//        Thread.sleep(2000);
//        System.out.println(nonSyncRunnable.getVal());//error not always prints correct, correct is 20, race condition, using atomic makes it correct

        SyncRunnable SyncRunnable = new SyncRunnable();
        new Thread(SyncRunnable).start();
        new Thread(SyncRunnable).start();
        SyncRunnable syncRunnable1 = new SyncRunnable();
        new Thread(syncRunnable1).start();
        Thread.sleep(2000);
        System.out.println(syncRunnable1.getVal());
//        Thread.sleep(1500); //can join instead of sleep
        System.out.println(SyncRunnable.getVal());//always prints correct, correct is 20
    }
}
