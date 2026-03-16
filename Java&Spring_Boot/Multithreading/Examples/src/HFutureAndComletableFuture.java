import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class HFutureAndComletableFuture {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            return "Hello";
        });
//        future.complete(null);
        future.thenAccept(System.out::println);
        AtomicInteger counter = new AtomicInteger(1);
        counter.incrementAndGet();
        counter.incrementAndGet();
        System.out.println(counter.get());

    }
}
