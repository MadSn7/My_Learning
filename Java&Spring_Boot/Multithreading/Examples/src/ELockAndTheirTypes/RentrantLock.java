package ELockAndTheirTypes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class RentrantLockExample{
    final ReentrantLock lock = new ReentrantLock();

    int counter = 0;

    void increment(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"Incrementing :"+ ++counter);
        lock.unlock();
    }
}

class ReentrantReadWriteLockExample{
    final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    int counter = 0;
    void increment() throws InterruptedException {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+" Incrementing :"+ ++counter);
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+" Leaving ");
        lock.readLock().unlock();
    }

    void change(){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+" Changing " );
        counter = 10;

        lock.writeLock().unlock();
    }
}
public class RentrantLock {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        RentrantLockExample example = new RentrantLockExample();
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new Runnable() {
//                public void run() {
//                    example.increment();
//                }
//            });
//        }
//        executorService.shutdown();

        ExecutorService exec = Executors.newFixedThreadPool(5);
        ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();
       exec.execute(new Runnable() {
           public void run() {
               try {
                   example.increment();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       });

        exec.execute(new Runnable() {
            public void run() {
                try {
                    example.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        exec.execute(new Runnable() {
            public void run() {
                example.change();
            }
        });
        exec.execute(new Runnable() {
            public void run() {
                try {
                    example.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        exec.shutdown();
        // write always wait for all read to be done and vice versa
    }
}
