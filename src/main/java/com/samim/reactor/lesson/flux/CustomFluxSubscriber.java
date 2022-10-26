package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class CustomFluxSubscriber {

    public static void main(String[] args) {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 10)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("-- onSubscribe --");
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("-- onNext --: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("-- onError --");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("-- onComplete --");
                    }
                });

        Util.sleepSeconds(2);
        atomicReference.get().request(3); // receives only 3 item out of the Subscription
        Util.sleepSeconds(2);
        atomicReference.get().request(4); // receives only 4 item out of the Subscription
        Util.sleepSeconds(2);
        atomicReference.get().cancel(); // Subscription is cancelled. No items are retained after this point.
        atomicReference.get().request(3); // does not receive any item as Subscription is cancelled
        System.out.println("done...");
    }

}
