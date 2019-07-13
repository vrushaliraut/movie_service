package com.codelab.factory;

import com.codelab.Server;
import com.codelab.Service.MovieService;
import com.codelab.controller.MovieController;
import com.codelab.controller.PingController;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerFactory {

    public static Server createServer() {
        final Gson snakeCaseGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        PingController pingController = new PingController();
        MovieService movieService = new MovieService();
        MovieController movieController = new MovieController(snakeCaseGson, movieService);
        return new Server(pingController, movieController, snakeCaseGson);
    }
}
