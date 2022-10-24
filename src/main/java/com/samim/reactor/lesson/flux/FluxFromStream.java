package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FluxFromStream {

    public static void main(String[] args) {
        System.out.println("-- stream.forEach --");
        List<Integer> integers = Arrays.asList(1, 2, 3, 6, 4);
        Stream<Integer> integerStream = integers.stream();

        try {
            integerStream.forEach(System.out::println);
            integerStream.forEach(System.out::println); // as streams can be iterated only once so throws an error.
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }

        System.out.println("\n-- Flux.fromStream from single stream as source --");
        Stream<Integer> integerStream2 = integers.stream();

        Flux.fromStream(integerStream2)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        // same behaviour as Stream since the stream source is same
        Flux.fromStream(integerStream2)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        System.out.println("\n-- Flux.fromStream from new stream everytime --");
        Flux<Integer> integerFlux = Flux.fromStream(() -> integers.stream());

        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        System.out.println("-- DONE 1 --");
        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        System.out.println("-- DONE 2 --");
        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        System.out.println("-- DONE 3 --");

    }
}
