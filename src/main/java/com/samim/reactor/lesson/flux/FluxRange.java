package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

/**
 * Demonstration for the usage of Flux.range().
 * Usage of Flux.range() is like using a for-loop
 */
public class FluxRange {

    public static void main(String[] args) {
        System.out.println("---- intRange1() ----");
        intRange1();
        System.out.println("\n---- intRange2() ----");
        intRange2();
        System.out.println("\n---- stringRange() ----");
        stringRange();
        System.out.println("\n---- logStringRange() ----");
        logStringRange();
    }

    private static void intRange1() {
        Flux.range(0, 12)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    private static void intRange2() {
        Flux.range(3, 12)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    private static void stringRange() {
        Flux.range(3, 12)
                .map(integer -> Util.fullName())
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    private static void logStringRange() {
        Flux.range(3, 12)
                .log()
                .map(integer -> Util.fullName())
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
