package AThreadCreation;//import java.util.concurrent.Callable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "RandomString"+ this.hashCode()+ Thread.currentThread().getName();
    }
}
public class Basic {
    public static void main(String[] args) throws Exception {
//        MyCallable m = new MyCallable();
//        System.out.println(m.call());
        ExecutorService exec = Executors.newFixedThreadPool(5);
        Future<String> future1 =  exec.submit(new MyCallable());
        System.out.println(future1.get());
        Future<String> future2 =  exec.submit(new MyCallable());
        System.out.println(future2.get());
        exec.shutdown();

    }
}


//class MyRunnable implements Runnable{
//    @Override
//    public void run() {
//        //        any code here
//        System.out.println(Thread.currentThread().getName()+" Running");
//    }
//}
//class Basic{
//    public static void main(String[] args) {
//        MyRunnable myRunnable = new MyRunnable();
//        Thread myThread = new Thread(myRunnable);
//        myThread.start();
//        myThread.run();
//        myThread.notify();
//        MyThread1 myThreadObject = new MyThread1();
//        myThreadObject.start();
//        //        if two or more threads are created we can not be sure of order of execution
//    }
//}
//
//class MyThread1 extends Thread{
//    @Override
//    public void run() {
//        System.out.println("Hello World from extending thread");
//    }
//}