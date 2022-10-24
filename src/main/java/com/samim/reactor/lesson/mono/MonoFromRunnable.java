package com.samim.reactor.lesson.mono;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

    public static void main(String[] args) {
        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        () -> System.out.println("Operation done after time consuming mono completion like sending emails...")
                );
    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            System.out.println("Operation starting...");
            Util.sleepSeconds(3);
            System.out.println("Operation completed...");
        };
    }

}
