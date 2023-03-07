package com.murtazali.elasticsearch.repository;

import com.murtazali.elasticsearch.entity.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {
}
