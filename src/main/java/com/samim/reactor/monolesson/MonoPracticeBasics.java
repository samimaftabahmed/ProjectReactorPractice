package com.samim.reactor.monolesson;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoPracticeBasics {

    public static void main(String[] args) {
        lesson2();
        lesson3_1();
        lesson3_2();
        lesson3_3();
        lesson3_4();
    }

    /**
     * Simple Mono
     */
    public static void lesson2() {
        System.out.println("-- Lesson 2 --");

        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(System.out::println);
    }

    /**
     * Mono with proper syntax
     */
    public static void lesson3_1() {
        System.out.println("\n-- Lesson 3.1 --");

        Mono<String> mono2 = Mono.just("ball");
        mono2.subscribe(
                System.out::println,
                err -> System.err.println(err.getMessage()),
                () -> System.out.println("Mono Completed")
        );
    }

    /**
     * Mono with exception
     */
    public static void lesson3_2() {
        System.out.println("\n-- Lesson 3.2 --");

        Mono<String> mono3 = Mono.just("ball");
        mono3.subscribe(
                s -> {
                    int value = 7 / 0;
                },
                err -> System.out.println("3_2 Exception: " + err.getMessage()),
                () -> System.out.println("Mono Completed") // Executes after consumer error
        );
    }

    /**
     * Mono with exception
     */
    public static void lesson3_3() {
        System.out.println("\n-- Lesson 3.3 --");

        Mono<Integer> mono = Mono.just("ball")
                .map(s -> s.length())
                .map(integer -> integer / 0);
        mono.subscribe(
                s -> System.out.println(s),
                err -> System.out.println("3_3 Exception: " + err.getMessage()),
                () -> System.out.println("Mono Completed") // Does not execute
        );
    }

    /**
     * Mono with exception, with Helper methods
     */
    public static void lesson3_4() {
        System.out.println("\n-- Lesson 3.4 --");

        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(integer -> integer / 0);
        mono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
