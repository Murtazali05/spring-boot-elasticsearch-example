//package com.example.elasticsearch.controller;
//
//import com.example.elasticsearch.entity.Book;
//import com.example.elasticsearch.utils.JsonUtils;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ReactiveIndexOperations;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.data.elasticsearch.core.query.Query;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/api/books")
//public class BookController {
//
//    @Autowired
//    private ReactiveElasticsearchOperations elasticsearchOperations;
//
//    @GetMapping
//    public Flux<Book> getAllBooks() {
//        Query query = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchAllQuery())
//                .build();
//        return elasticsearchOperations.search(query, Book.class)
//                .map(SearchHit::getContent);
//    }
//
//    @GetMapping("/{id}")
//    public Mono<ResponseEntity<Book>> getBookById(@PathVariable String id) {
//        return elasticsearchOperations.get(id, Book.class)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Mono<Book> createBook(@Validated @RequestBody Book book) {
//        ReactiveIndexOperations indexOps = elasticsearchOperations.indexOps(Book.class);
//        IndexCoordinates indexCoordinates = indexOps.getIndexCoordinates();
//        IndexRequest indexRequest = new IndexRequest(indexCoordinates.getIndexName())
//                .source(JsonUtils.serialize(book), XContentType.JSON);
//        return indexOps.index(indexRequest)
//                .map(response -> {
//                    book.setId(response.getId());
//                    return book;
//                });
//    }
//
//    @PutMapping("/{id}")
//    public Mono<ResponseEntity<Book>> updateBook(@PathVariable String id, @Validated @RequestBody Book book) {
//        ReactiveIndexOperations indexOps = elasticsearchOperations.indexOps(Book.class);
//        IndexCoordinates indexCoordinates = indexOps.getIndexCoordinates();
//        UpdateRequest updateRequest = new UpdateRequest(indexCoordinates.getIndexName(), id)
//                .doc(JsonUtils.serialize(book), XContentType.JSON);
//        return elasticsearchOperations.update(updateRequest)
//                .map(response -> ResponseEntity.ok(book))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable String id) {
//        ReactiveIndexOperations indexOps = elasticsearchOperations.indexOps(Book.class);
//        IndexCoordinates indexCoordinates = indexOps.getIndexCoordinates();
//        return elasticsearchOperations.delete(id, indexCoordinates)
//                .map(response -> ResponseEntity.ok().<Void>build())
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//}
