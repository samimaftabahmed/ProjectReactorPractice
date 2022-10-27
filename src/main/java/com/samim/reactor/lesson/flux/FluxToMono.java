package com.samim.reactor.lesson.flux;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxToMono {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(integer -> integer > 3)
                .next() // receives only 1 Item from the publisher instead of all the items
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
