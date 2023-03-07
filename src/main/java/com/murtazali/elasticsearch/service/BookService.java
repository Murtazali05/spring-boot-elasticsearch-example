package com.murtazali.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.murtazali.elasticsearch.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    public static final String INDEX = "bookstore";

    private final ElasticsearchClient client;

    public List<Book> getAll() throws IOException {
        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(INDEX));
        SearchResponse<Book> searchResponse =  client.search(searchRequest, Book.class);
        List<Hit<Book>> hits = searchResponse.hits().hits();
        List<Book> books = new ArrayList<>();
        for(Hit<Book> object : hits){
            books.add(object.source());
        }
        return books;
    }

    public Book getById(String id) throws IOException {
        final GetResponse<Book> response =
                client.get(b -> b.index(INDEX).id(id), Book.class);
        return response.source();
    }

    public String create(Book book) throws IOException {
        return client.index(b -> b
                .index(INDEX)
                .id(book.getId())
                .document(book))
                .result()
                .name();
    }

    public String update(String id, Book book) throws IOException {
        return client.index(b -> b
                .index(INDEX)
                .id(id)
                .document(book))
                .result()
                .name();
    }

    public String delete(String id) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(INDEX).id(id));

        return client.delete(request).result().name();
    }
}
