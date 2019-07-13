package com.codelab.Service;

import com.codelab.contract.request.MovieRequest;
import com.codelab.repository.KafkaRepository;

public class MovieService {

    public boolean publish(MovieRequest movieRequest) {
        boolean isSuccessful = KafkaRepository.publish(movieRequest.getMovieName());
        if (!isSuccessful) {
            System.out.println("error occurred while publishing to kafka");
            return false;
        }
        System.out.println("publish successfully");
        return true;
    }
}
