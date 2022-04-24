package com.aipeel.springbackend.repo;

import com.aipeel.springbackend.entity.Subscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRespository extends CrudRepository<Subscriber, Long> {
}
