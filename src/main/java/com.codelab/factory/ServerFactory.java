package com.codelab.factory;

import com.codelab.Server;
import com.codelab.controller.MovieController;
import com.codelab.controller.PingController;
import com.codelab.repository.KafkaRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerFactory {

    public static Server createServer() {
        final Gson snakeCaseGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        PingController pingController = new PingController();
        KafkaRepository kafkaRepository = new KafkaRepository();
        MovieController movieController = new MovieController(snakeCaseGson, kafkaRepository);
        return new Server(pingController, movieController, snakeCaseGson);
    }


}
