package com.murtazali.elasticsearch.controller;

import com.murtazali.elasticsearch.entity.Book;
import com.murtazali.elasticsearch.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() throws IOException {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") String id) throws IOException {
        return bookService.getById(id);
    }

    @PostMapping
    public String create(@RequestBody Book book) throws IOException {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @Validated @RequestBody Book book) throws IOException {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) throws IOException {
        return bookService.delete(id);
    }
}
