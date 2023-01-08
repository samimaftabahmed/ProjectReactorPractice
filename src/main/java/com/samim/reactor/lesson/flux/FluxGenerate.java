package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {

    public static void main(String[] args) {
        //generate will use synchronous Sink and generate items endlessly.
        // It is like an endless loop.
        Flux.generate((synchronousSink) -> {
                    synchronousSink.next(Util.fullName());
                })
                .take(3)
                .subscribe(Util.subscriber());

        System.out.println("-- loop until canada is found");

        Flux.generate(synchronousSink -> {
                    String name = Util.faker().country().name();
                    synchronousSink.next(name);
                    if (name.equalsIgnoreCase("canada")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
