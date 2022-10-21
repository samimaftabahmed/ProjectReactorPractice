package com.samim.reactor.monolesson;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

public class MonoEmptyOrError {

    public static void main(String[] args) {
        int nextInt = ThreadLocalRandom.current().nextInt(1, 5);
        userRepository(nextInt)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    /**
     * A Proper Mono using Faker library
     */
    private static Mono<String> userRepository(int userId) {
        System.out.println("user id: " + userId);
        if (userId == 1) {
            return Mono.just(Util.faker().name().fullName());
        } else if (userId == 2) {
            return Mono.empty();
        }
        return Mono.error(new RuntimeException("Not in the allowed range"));
    }
}
