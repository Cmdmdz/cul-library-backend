package com.cdx.library.repositorier;

import com.cdx.library.documents.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {


    Optional<Book> findByBookName(String nameBook);
}
