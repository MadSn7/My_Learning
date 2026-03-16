package AThreadCreation;
class SomeSharedReource{
    synchronized void funtionSleep()  {
        System.out.println(Thread.currentThread().getName()+" entered funtionSleep");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" exiting funtionSleep");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    synchronized void functionWait()  {
        System.out.println(Thread.currentThread().getName()+" entered functionWait");
        try {
            wait(2000);
            System.out.println(Thread.currentThread().getName()+" exiting functionWait");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    synchronized void notifier(){
        System.out.println(Thread.currentThread().getName()+" entered notifier");
//        notifyAll();
        notify();
    }
}
public class SleepWait {
    public static void main(String[] args) {
        SomeSharedReource sr = new SomeSharedReource();
        new Thread(() -> sr.functionWait()).start();
        new Thread(() -> sr.functionWait()).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sr.notifier();
        }).start(); // wait all can enter and resume only when notify/nptifyALL

//        new Thread(() -> sr.funtionSleep()).start();
//        new Thread(() -> sr.funtionSleep()).start(); //example of sleep only one can enter
//      using synchronized in methods cause threads are sharing methods, can use alternative locks etc also
    }
}
