package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxBasics {

    public static void main(String[] args) {
        System.out.println("-- Flux.just --");
        Flux.just(1, 2, 3, 4, "abc", Util.fullName()) // Output flux: Flux<? extends Serializable>
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        System.out.println("-- Flux.fromIterable --");
        List<String> strings = Arrays.asList("a", "b", "c");
        Flux.fromIterable(strings)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        System.out.println("-- Flux.fromArray --");
        Integer[] arr = {1, 2, 8, 6};
        Flux.fromArray(arr)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
