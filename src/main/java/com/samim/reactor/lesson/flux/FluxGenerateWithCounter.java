package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateWithCounter {

    public static void main(String[] args) {
        Flux.generate(() -> 1, (integer, synchronousSink) -> {
                    String name = Util.faker().country().name();
                    synchronousSink.next(name);
                    if (name.equalsIgnoreCase("canada") || integer == 10) {
                        synchronousSink.complete();
                    }
                    ++integer;
                    return integer;
                })
                .subscribe(Util.subscriber());
    }
}
