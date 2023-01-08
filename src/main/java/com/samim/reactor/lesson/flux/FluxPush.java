package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.NameProducer;
import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxPush {

    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Runnable runnable = () -> nameProducer.produce();

        // Flux.push is not thread-safe
        Flux.push(nameProducer)
                .subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
