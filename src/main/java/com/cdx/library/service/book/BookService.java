package com.cdx.library.service.book;

import com.cdx.library.model.request.BookRequest;
import com.cdx.library.model.response.BookResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface BookService {

    public List<BookResponse> findAllBook();
    public BookResponse findById(String idBook);
    public BookResponse updateBookById(String idBook, BookRequest request);
    public BookResponse addBook(BookRequest request);
    public HttpStatus deleteById(String idBook);
}
