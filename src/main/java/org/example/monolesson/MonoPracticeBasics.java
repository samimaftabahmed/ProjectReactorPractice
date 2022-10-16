package org.example.monolesson;

import reactor.core.publisher.Mono;

public class MonoPracticeBasics {

    /**
     * Simple Mono
     */
    public void lesson2() {
        System.out.println("-- Lesson 2 --");

        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(System.out::println);
    }

    /**
     * Mono with proper syntax
     */
    public void lesson3_1() {
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
    public void lesson3_2() {
        System.out.println("\n-- Lesson 3.2 --");

        Mono<String> mono3 = Mono.just("ball");
        mono3.subscribe(
                s -> {
                    int value = 7 / 0;
                },
                err -> {
                    System.out.println("Exception: " + err.getMessage());
                    System.err.println("Exception: " + err.getMessage());
                },
                () -> System.out.println("Mono Completed")
        );
    }

}
