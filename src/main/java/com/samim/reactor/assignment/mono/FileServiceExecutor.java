package com.samim.reactor.assignment.mono;

import com.samim.reactor.courseutil.Util;

import java.util.UUID;

/**
 * Assignment as part of Mono lessons. Executes various methods of FileService
 */
public class FileServiceExecutor {

    public static void main(String[] args) {
        String filename = UUID.randomUUID().toString().substring(0, 6) + ".xyz";
        FileService service = new FileService();
        service.readMono("random.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        service.writeMono("Something to write", filename)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        service.deleteMono(filename)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

}
