package com.samim.reactor.assignment.flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class StockPriceSimulator {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        getStockPriceFlux()
                .subscribeWith(new Subscriber<Integer>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                        this.subscription = subscription;
                    }

                    @Override
                    public void onNext(Integer price) {
                        if (price >= 90 && price <= 110) {
                            System.out.println("Time: " + LocalDateTime.now() + " :: Stock price: " + price);
                        } else {
                            System.out.println("Time: " + LocalDateTime.now() + " :: Stock price " + price + " out of range.");
                            subscription.cancel();
                            latch.countDown();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Flux stopped.");
                        latch.countDown();
                    }
                });

        latch.await();
    }

    public static Flux<Integer> getStockPriceFlux() {
        return Flux.interval(Duration.ofMillis(500))
                .map(aLong -> ThreadLocalRandom.current().nextInt(88, 112));
    }
}
