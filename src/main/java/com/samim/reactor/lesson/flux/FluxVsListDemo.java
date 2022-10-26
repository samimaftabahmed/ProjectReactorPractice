package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration of the item retrieval behavior of Flux vs a List.
 */
public class FluxVsListDemo {

    public static void main(String[] args) {
        System.out.println("-- namesList() --");
        System.out.println("Names: " + namesList(5)); // We only get the names when the entire List has been computed

        System.out.println("\n\n-- namesFlux() --");
        namesFlux(5)
                .subscribe(s -> System.out.println("Name: " + s)); // Names are published as soon as it is populated in the Flux
    }

    private static List<String> namesList(int count) {
        List<String> names = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Util.sleepSeconds(1);
            names.add(Util.fullName());
        }
        return names;
    }

    private static Flux<String> namesFlux(int count) {
        return Flux.range(0, count)
                .map(integer -> {
                    Util.sleepSeconds(1);
                    return Util.fullName();
                });
    }

}
