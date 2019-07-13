package com.codelab;

import com.codelab.controller.MovieController;
import com.codelab.controller.PingController;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class Server {

    private PingController pingController;
    private MovieController movieController;
    private Gson gson;

    public Server(PingController pingController,
                  MovieController movieController,
                  Gson gson) {
        this.pingController = pingController;
        this.movieController = movieController;
        this.gson = gson;
    }

    public void start() {
        port(8090);
        get("/ping", pingController::ping, gson::toJson);
        post("/publish_movie", movieController::publishMovie, gson::toJson);
    }
}
