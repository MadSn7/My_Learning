package Questions;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Multi producer consumer problem with fix topic size
 * */
class QueueBlock {
    int size;
    Semaphore producerSemaphore;
    Semaphore consumerSemaphore;
    ConcurrentLinkedQueue<Integer> queue;
    QueueBlock(int size) {
        this.size = size;
        producerSemaphore = new Semaphore(size);
        consumerSemaphore = new Semaphore(0);
        queue  = new ConcurrentLinkedQueue<>();
    }
    void produce() throws InterruptedException {
        while(true){
            producerSemaphore.acquire();
            int x = (int) (Math.random()*10);
            System.out.println("Producing " + x);
            queue.add(x);
            Thread.sleep((int)(Math.random()*1000));
            consumerSemaphore.release();
        }

    }
    void consume() throws InterruptedException {
        while(true){
            consumerSemaphore.acquire();
            int x = queue.poll();
            System.out.println("Consuming " + x);
            Thread.sleep((int)(Math.random()*1000));
            producerSemaphore.release();
        }

    }

}
public class CDesignBoundedBlockingQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
        int n = 5;
        ExecutorService executorServiceProducer = Executors.newFixedThreadPool(n);
        ExecutorService executorServiceConsumer = Executors.newFixedThreadPool(n);
        QueueBlock queueBlock = new QueueBlock(n);
        for (int i = 0; i < n; i++) {
            executorServiceProducer.submit(()->{
                try {
                    queueBlock.produce();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < n; i++) {
            executorServiceConsumer.submit(()->{
                try {
                    queueBlock.consume();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        executorServiceProducer.shutdown();
        executorServiceConsumer.shutdown();
    }
}
