package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.NameProducer;
import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {

        System.out.println("-- Create Flux Directly");
        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Util.faker().country().name();
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("canada"));
                    fluxSink.complete();
                })
                .subscribe(Util.subscriber());

        System.out.println("-- Create Flux with Consumer FluxSink");
        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer).subscribe(Util.subscriber());
        nameProducer.produce();
        nameProducer.produce();
        nameProducer.produce();

        Runnable runnable = () -> nameProducer.produce();

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

    }
}
