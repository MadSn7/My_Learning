package Questions;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//class PrintZeroEvenSoln{
//    AtomicInteger counter = new AtomicInteger(1);
//    int n;
//    PrintZeroEvenSoln(int n){
//        this.n = n;
//    }
//    void printZero(){
//        while(counter.get()<=2*n){
//            while(counter.get()%2 != 1);
//            synchronized (this){
//                if(counter.get()> 2*n) break;
//                System.out.print(0+" ");
//                counter.incrementAndGet();
//            }
//        }
//    }
//    void printEven(){
//        while(counter.get()<=2*n){
//            while(counter.get()%2 == 1 && (counter.get()/2)%2 == 0);
//            synchronized (this){
//                if(counter.get()%2 != 1 || (counter.get()/2)%2 != 0) continue;
//                if(counter.get()> 2*n) break;
//                System.out.print("even "+ counter.get()/2+" ");
//                counter.incrementAndGet();
//            }
//        }
//    }
//    void printOdd(){
//        while(counter.get()<=2*n){
//            while(counter.get()%2 == 1 && (counter.get()/2)%2 == 1);
//            synchronized (this){
//                if(counter.get()%2 != 1 || (counter.get()/2)%2 != 1) continue;
//                if(counter.get()> 2*n) break;
//                System.out.print("odd "+counter.get()/2+" ");
//                counter.incrementAndGet();
//            }
//        }
//    }
//}

//public class printzeroevenodd {
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        Scanner sc = new Scanner(System.in);
////        int n = sc.nextInt();
//        PrintZeroEvenSoln pz = new PrintZeroEvenSoln(5);
//        executorService.submit(()->pz.printZero());
//        executorService.submit(()->pz.printEven());
//        executorService.submit(()->pz.printOdd());
//        executorService.shutdown();
//    }
//}
class PrintZeroEvenSoln2{
    AtomicInteger counter = new AtomicInteger(1);
//    AtomicInteger evenOddCounter = new AtomicInteger(1);
    Semaphore zeroSemaphore = new Semaphore(1);
    Semaphore oddSemaphore = new Semaphore(0);
    Semaphore evenSemaphore = new Semaphore(0);

    int n;
    PrintZeroEvenSoln2(int n){
        this.n = n;
    }
//    void printZero() throws InterruptedException {
//        while(counter.get()<=n){
//            zeroSemaphore.acquire();
//            System.out.print("0 ");
//            oddSemaphore.release();
//            if(counter.get()>n) break;
//            zeroSemaphore.acquire();
//            System.out.print("0 ");
//            evenSemaphore.release();
//
//        }
//    }
void printZero() throws InterruptedException {
    while (counter.get() <= n) {
        // Wait for a permit from the last printed number
        zeroSemaphore.acquire();
        if (counter.get() > n) {
//            // Terminate gracefully
            evenSemaphore.release();
            oddSemaphore.release();
            break;
        }
        System.out.print("0 ");

        // Check if the next number is odd or even and release the correct semaphore

        if (counter.get() % 2 != 0) {
            oddSemaphore.release();
        } else {
            evenSemaphore.release();
        }

        // This break statement is now unnecessary and would cause issues if N is odd.
        // The loop will correctly terminate on its own.
    }
}
    void printOdd() throws InterruptedException {
        while(counter.get()<=n){
            oddSemaphore.acquire();
            if (counter.get() > n) {
                // Terminate gracefully
                zeroSemaphore.release();
                evenSemaphore.release();
                break;
            }
            System.out.print(counter.get()+" ");
            counter.incrementAndGet();
            zeroSemaphore.release();
        }
    }
    void printEven() throws InterruptedException {
        while(counter.get()<=n){
            evenSemaphore.acquire();
            if (counter.get() > n) {
                // Terminate gracefully
                zeroSemaphore.release();
                oddSemaphore.release();
                break;
            }
            System.out.print(counter.get()+" ");
            counter.incrementAndGet();
            zeroSemaphore.release();
        }
    }
}
public class Aprintzeroevenodd{
    public static void main(String[] args)  {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
        PrintZeroEvenSoln2 pz = new PrintZeroEvenSoln2(7);
        new Thread(()-> {
            try {
                pz.printZero();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()-> {
            try {
                pz.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()-> {
            try {
                pz.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
//        executor.submit(()-> {
//            try {
//                pz.printZero();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        executor.submit(()-> {
//            try {
//                pz.printEven();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        executor.submit(()-> {
//            try {
//                pz.printOdd();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }
}