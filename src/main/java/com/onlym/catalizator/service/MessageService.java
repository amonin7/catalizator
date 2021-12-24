package com.onlym.catalizator.service;

import com.onlym.catalizator.model.Message;
import com.onlym.catalizator.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private MessageRepo repo;

    @Autowired
    public MessageService(MessageRepo repo) {
        this.repo = repo;
    }

    public Flux<Message> list() {
        return repo.findAll();
    }

    public Mono<Message> addOne(Message message) {
        return repo.save(message);
    }
}
