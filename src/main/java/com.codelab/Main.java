package com.codelab;

import com.codelab.factory.ServerFactory;
import spark.Spark;

import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

public class Main {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));

        System.out.println("Starting service");
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));
        final Server server = ServerFactory.createServer();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping service...");

            Spark.stop();
        }));
    }
}
