package DThreadCommsWaitNotify;

//import java.nio.Buffer;

import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumerImplementation{
//    int arr[];
    Queue<Integer> queue;
    int SIZE;
    public ProducerConsumerImplementation(int SIZE){
        queue = new LinkedList<Integer>();
        this.SIZE = SIZE;
//        arr = new int[SIZE];
    }

    void produce() throws InterruptedException {
        while(true){
            synchronized (this){
                try{
                    if(queue.size() == SIZE){
                        System.out.println("Waiting for consumer to finish");
                        wait();
                    }
                        int num = (int)(Math.random()*100);
                        System.out.println("Producing "+num);
                        queue.add(num);
                        notifyAll();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
        }
    }
    void consume() throws InterruptedException {
        while(true){
            synchronized (this){
                try{
                    wait(1000);
                    if(queue.size() == 0){
                        System.out.println("Waiting for producer to produce");
                        wait();
                    }
                    System.out.println("Consuming "+queue.poll() );
                    notifyAll();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}

public class ProducerConsumer {
    public static void main(String[] args)  {
        ProducerConsumerImplementation pc = new ProducerConsumerImplementation(5);
        new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
//better example of this is pub sub like kafka