package com.codelab;

import com.codelab.factory.ServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));

        logger.info("Starting service");
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));
        final Server server = ServerFactory.createServer();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping service...");
            Spark.stop();
        }));
    }
}
