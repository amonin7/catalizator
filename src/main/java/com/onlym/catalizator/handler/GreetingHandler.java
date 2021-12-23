package com.onlym.catalizator.handler;

import com.onlym.catalizator.model.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        Long offset = request
                .queryParam("offset")
                .map(Long::valueOf)
                .orElse(0L);
        Long pageSize = request
                .queryParam("pageSize")
                .map(Long::valueOf)
                .orElse(3L);

        Flux<Message> data = Flux
                .just(
                        "Hello, React!",
                        "More then one",
                        "Third post",
                        "Forth message"
                )
                .skip(offset)
                .take(pageSize)
                .map(Message::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        String user = serverRequest
                .queryParam("user")
                .orElse("user");
        return ServerResponse
                .ok()
                .render("index", Map.of("user", user));
    }

}