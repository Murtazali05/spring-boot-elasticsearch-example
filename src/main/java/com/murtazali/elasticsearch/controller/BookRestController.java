package com.murtazali.elasticsearch.controller;

import com.murtazali.elasticsearch.entity.Book;
import com.murtazali.elasticsearch.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookRepository repository;

    @GetMapping
    public Flux<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @PostMapping
    public Mono<Book> createBook(@RequestBody Book book) {
        return repository.save(book);
    }
}
