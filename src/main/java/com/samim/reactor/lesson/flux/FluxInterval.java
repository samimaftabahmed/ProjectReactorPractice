package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxInterval {

    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1)) // Publishes item after every 1 second, without blocking the main thread
                .map(integer -> Util.fullName())
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        Util.sleepSeconds(10); // blocking the main thread to view the content of the above subscription
    }
}
