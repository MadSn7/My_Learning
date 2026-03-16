package Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
n forks n philosophers
want to eat, to eat one need two forks
sitting in circular table
have to take left side and right side fork
 */
class PhilosopherTable{
//    Semaphore semaphore = new Semaphore(4);
    int n ;
    Semaphore arr[];
    Semaphore canVisit ;
    PhilosopherTable(int n){
        this.n = n;
        arr = new Semaphore[n];
        canVisit = new Semaphore(n-1);
        for (int i = 0; i < n; i++) {
            arr[i] = new Semaphore(1);
        }
    }
    void eating(int i) throws InterruptedException{
        while(true){
            canVisit.acquire();
//            for(int i = 0; i < n; i++){
//                if(!arr[i].tryAcquire(100, TimeUnit.MICROSECONDS)) continue;
//                if(i == n-1){
//                    arr[(i+1)%n].acquire();
//                    arr[i].acquire();
//                }else{

//                }
            arr[i].acquire();
            arr[(i+1)%n].acquire();
            Thread.sleep(1000);
            System.out.println("Philosopher "+i+" is eating");
                arr[i].release();
                arr[(i+1)%n].release();

                canVisit.release();
                Thread.sleep(500);

//                if(!arr[i+1].tryAcquire(100, TimeUnit.MICROSECONDS)){
//                    arr[i].release();
//                    continue;
//                };
//                System.out.println("Philosopher "+(i+1)+"is eating");
////                Thread.sleep(Math.round(Math.random()*1000));
//                System.out.println(arr[i]);
//                arr[i].release();
//                arr[i+1].release();
////                if(arr[i].tryAcquire()){
////                    if(arr[i+1 % n].tryAcquire()){
////                        System.out.println("Philosopher "+(i+1)+" is eating");
////                        Thread.sleep(Math.round(Math.random()*1000));
////                        arr[i+1 % n].release();
////                        arr[i].release();
////                        System.out.println("Philosopher "+(i+1)+" done eating");
////                        notifyAll();
////                    }else arr[i].release();
////                }
//            }
        }
    }
}
public class DTheDiningPhilosophers {
    public static void main(String[] args) {
        int size = 5;
//        System.out.println("Creating Philosophers Table");
        PhilosopherTable philosopherTable = new PhilosopherTable(size);
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for(int i = 0; i < size; i++){
            int finalI = i;
            executorService.submit(()-> {
                try {
                    philosopherTable.eating(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
    }
}
