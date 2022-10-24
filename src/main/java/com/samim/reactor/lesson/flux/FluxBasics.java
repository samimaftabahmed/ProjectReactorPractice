package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxBasics {

    public static void main(String[] args) {
        System.out.println("-- 1 --");
        Flux.just(1, 2, 3, 4, "abc", Util.fullName())
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        System.out.println("-- 2 --");
        List<String> strings = Arrays.asList("a", "b", "c");
        Flux.fromIterable(strings)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        System.out.println("-- 3 --");
        Integer[] arr = {1, 2, 8, 6};
        Flux.fromArray(arr)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
