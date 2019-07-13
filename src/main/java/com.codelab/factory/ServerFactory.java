package com.codelab.factory;

import com.codelab.Server;
import com.codelab.controller.MovieController;
import com.codelab.controller.PingController;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerFactory {

    public static Server createServer() {
        PingController pingController = new PingController();
        final Gson snakeCaseGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        MovieController movieController = new MovieController(snakeCaseGson);

        return new Server(pingController, movieController, snakeCaseGson);
    }
}
