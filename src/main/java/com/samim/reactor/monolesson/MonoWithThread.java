package com.samim.reactor.monolesson;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoWithThread {

    public static void main(String[] args) {
        System.out.println("1");
        monoName(); // mono doesn't execute as no subscriber
        System.out.println("2");
        monoName().subscribe(Util.onNext()); // mono is executed and Thread is blocked
        System.out.println("3");
        monoName(); // mono doesn't execute as no subscriber
        System.out.println("4");
        monoName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext()); // mono is executed and Thread is NON blocking
        System.out.println("5");
        monoName(); // mono doesn't execute as no subscriber
        Util.sleepSeconds(5); // blocked the main thread to view the result of no. 4 execution.
        // If thread is not blocked then the program ends.
    }

    private static Mono<String> monoName() {
        return Mono.fromSupplier(() -> {
            System.out.println("Generating mono of name...");
            Util.sleepSeconds(3);
            return Util.fullName();
        }).map(String::toUpperCase);
    }
}
