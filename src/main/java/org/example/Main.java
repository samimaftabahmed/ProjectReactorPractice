package org.example;

import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- Lesson 2 --");

        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(System.out::println);

        System.out.println("\n-- Lesson 3.1 --");

        Mono<String> mono2 = Mono.just("ball");
        mono2.subscribe(
                System.out::println,
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("Mono Completed")
        );

        System.out.println("\n-- Lesson 3.2 --");

        Mono<String> mono3 = Mono.just("ball");
        mono3.subscribe(
                s -> {
                    int value = 7 / 0;
                },
                err -> System.out.println("Exception: " + err.getMessage()),
                () -> System.out.println("Mono Completed")
        );

    }
}