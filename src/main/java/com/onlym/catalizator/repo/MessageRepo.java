package com.onlym.catalizator.repo;

import com.onlym.catalizator.model.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepo extends ReactiveCrudRepository<Message, Long> {
}
