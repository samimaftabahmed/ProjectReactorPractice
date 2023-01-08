package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.NameProducer;
import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {

        System.out.println("-- Create Flux Directly, Emit unbounded");
        unBoundedEmitting();

        System.out.println("\n\n-- Create Flux Directly, Emit bounded by flux completeness");
        boundedEmitting();

        System.out.println("\n\n-- Create Flux with Consumer FluxSink");
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

    private static void unBoundedEmitting(){
        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Util.faker().country().name();
                        System.out.println("emitting : " + country);
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("canada"));
                    fluxSink.complete();
                })
                .take(5)
                .subscribe(Util.subscriber());
    }

    private static void boundedEmitting(){
        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Util.faker().country().name();
                        System.out.println("emitting : " + country);
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("canada") && !fluxSink.isCancelled());
                    fluxSink.complete();
                })
                .take(5)
                .subscribe(Util.subscriber());
    }
}
