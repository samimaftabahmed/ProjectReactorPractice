package com.samim.reactor.lesson.mono;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Behaviour of Mono from Supplier and Callable
 */
public class MonoFromSupplierAndCallable {

    public static void main(String[] args) {

//        This mono will call its underlying method even without any subscriber
        Mono.just(Util.fullName());

//        This mono will call its underlying method only when there is a subscriber
        Supplier<String> stringSupplier = () -> Util.fullName();
        Mono<String> fromSupplier = Mono.fromSupplier(stringSupplier);
//        fromSupplier.subscribe(Util.onNext());

        Callable<String> stringCallable = () -> Util.fullName();
//        Underlying methods in a Mono from callable and supplier are called lazily
        Mono<String> fromCallable = Mono.fromCallable(stringCallable);
//        fromCallable.subscribe(Util.onNext());
    }

}
