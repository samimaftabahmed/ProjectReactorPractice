package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

        System.out.println("-- Flux with empty and error --");
        int count = ThreadLocalRandom.current().nextInt(1, 5);
        userRepository(count)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    private static Flux<String> userRepository(int userId) {
        if (userId == 1) {
            return Flux.just(Util.fullName());
        } else if (userId == 2) {
            return Flux.empty();
        }
        return Flux.error(new RuntimeException("Not in the allowed range"));
    }
}
