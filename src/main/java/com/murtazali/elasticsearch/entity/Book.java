package com.murtazali.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bookstore")
@Data
public class Book {

    @Id
    private String id;

    private String title;

    private String author;

    private String _class;

}