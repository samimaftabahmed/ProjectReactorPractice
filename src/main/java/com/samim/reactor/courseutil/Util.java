package com.samim.reactor.courseutil;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Util {

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received: " + o);
    }

    public static Consumer<Throwable> onError() {
        return throwable -> System.out.println("Error: " + throwable.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed");
    }

    public static Faker faker() {
        return Faker.instance();
    }

    public static String fullName() {
        System.out.println("Generating full name...");
        return faker().name().fullName();
    }

    public static void sleepSeconds(int seconds) {
        System.out.println("Entered sleepSeconds");
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
