package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class FluxFromMono {

    public static void main(String[] args) {
        Mono<List<Integer>> listMono = Mono.just(Arrays.asList(1, 2, 3));
        Flux.from(listMono)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
