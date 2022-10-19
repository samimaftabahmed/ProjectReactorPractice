package com.samim.reactor.monolesson;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError {

    public static void main(String[] args) {
        userRepository(1)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    /**
     * A Proper Mono using Faker library
     */
    private static Mono<String> userRepository(int userId) {
        if (userId == 1) {
            return Mono.just(Util.faker().name().fullName());
        } else if (userId == 2) {
            return Mono.empty();
        }
        return Mono.error(new RuntimeException("Not in the allowed range"));
    }
}
