package com.samim.reactor.assignment.mono;

import com.samim.reactor.courseutil.Util;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Service that reads, writes and deletes files via Mono
 */
public class FileServicePublishers {

    private final static String OUTPUT_DIR = "target/classes/files/";

    public Mono<String> read(String filename) {
        return Mono.fromSupplier(() -> readFile(filename));
    }

    public Mono<Void> write(String content, String filename) {
        return Mono.fromRunnable(() -> writeFile(content, filename));
    }

    public Mono<Void> delete(String filename) {
        return Mono.fromRunnable(() -> {
            Util.sleepSeconds(3); // sleeping, just to view the created file in explorer
            deleteFile(filename);
        });
    }

    private String readFile(String fileName) {
        System.out.println("Reading File: " + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(OUTPUT_DIR, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(bytes);
    }

    private void writeFile(String datum, String fileName) {
        try {
            Files.write(Paths.get(OUTPUT_DIR, fileName), datum.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File write on " + fileName + " , completed");
    }

    private void deleteFile(String fileName) {
        try {
            Files.delete(Paths.get(OUTPUT_DIR, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File: " + fileName + " deleted");
    }
}
