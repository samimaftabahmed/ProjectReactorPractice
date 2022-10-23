package com.samim.reactor.monolesson;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {

    public static void main(String[] args) {
        Mono.fromFuture(getFutureName())
            .subscribe(Util.onNext()); // Main thread completes execution before running the Future in forkJoinPool.
        // So future doesn't get time to display the output.
        Util.sleepSeconds(1); // blocking the main thread to display the output from above.
    }

    private static CompletableFuture<String> getFutureName() {
        return CompletableFuture.supplyAsync(Util::fullName);
    }
}
