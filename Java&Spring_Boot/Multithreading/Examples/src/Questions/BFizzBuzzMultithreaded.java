package Questions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    Semaphore numSemaphore = new Semaphore(1);
    Semaphore buzzSemaphore = new Semaphore(0);
    Semaphore fizzSemaphore = new Semaphore(0);
    Semaphore fizzbuzzSemaphore = new Semaphore(0);
    int counter = 1;
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz() throws InterruptedException {
        for(int i=1;i<=n;i++){
            if(i%3==0 && i%5 != 0){
                fizzSemaphore.acquire();
                System.out.print("Fizz ");
                counter++;
                if(counter % 3 == 0 && counter % 5 == 0){
                    fizzbuzzSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 != 0){
                    numSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 == 0){
                    buzzSemaphore.release();
                }else{
                    fizzSemaphore.release();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz() throws InterruptedException {
        for(int i=1;i<=n;i++){
            if(i%3!=0 && i%5 == 0){
                buzzSemaphore.acquire();
                System.out.print("Buzz ");
                counter++;
                if(counter % 3 == 0 && counter % 5 == 0){
                    fizzbuzzSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 != 0){
                    numSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 == 0){
                    buzzSemaphore.release();
                }else{
                    fizzSemaphore.release();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz() throws InterruptedException {
        for(int i=1;i<=n;i++){
            if(i%3==0 && i%5 == 0){
                fizzbuzzSemaphore.acquire();
                System.out.print("FizzBuzz ");
                counter++;
                if(counter % 3 == 0 && counter % 5 == 0){
                    fizzbuzzSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 != 0){
                    numSemaphore.release();

                }else if(counter % 3 != 0 && counter % 5 == 0){
                    buzzSemaphore.release();
                }else{
                    fizzSemaphore.release();
                    System.out.println("Releasing numSemaphore");

                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number() throws InterruptedException {
        for(int i=1;i<=n;i++){
            if(i%3!=0 && i%5 != 0){
                numSemaphore.acquire();
                System.out.print(counter+" ");
                counter++;
                if(counter % 3 == 0 && counter % 5 == 0){
                    fizzbuzzSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 != 0){
                    numSemaphore.release();
                }else if(counter % 3 != 0 && counter % 5 == 0){
                    buzzSemaphore.release();
                }else{
                    fizzSemaphore.release();
                }
            }
        }
    }
}
/*
"fizzbuzz" if i is divisible by 3 and 5,
"fizz" if i is divisible by 3 and not 5,
"buzz" if i is divisible by 5 and not 3,
or i if i is not divisible by 3 or 5.
 */
public class BFizzBuzzMultithreaded {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FizzBuzz  fizzBuzz = new FizzBuzz(15);
        executorService.submit(()-> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(()-> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(()-> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(()-> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
    }
}
