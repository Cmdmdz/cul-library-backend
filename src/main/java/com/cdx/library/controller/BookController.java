package com.cdx.library.controller;

import com.cdx.library.model.request.BookRequest;
import com.cdx.library.model.response.BookResponse;
import com.cdx.library.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    private final BookService bookService;

    @GetMapping("book")
    public List<BookResponse> findAllBook(){
        return bookService.findAllBook();
    }

    @GetMapping("book/{idBook}")
    public BookResponse findByNameBook(@PathVariable String idBook){
        return bookService.findById(idBook);
    }

    @PutMapping("book/{idBook}")
    public BookResponse updateBookById(@PathVariable String idBook,@RequestBody BookRequest request){
        return bookService.updateBookById(idBook,request);
    }

    @PostMapping("book")
    public BookResponse addBook(@RequestBody BookRequest request){
        return bookService.addBook(request);
    }

    @DeleteMapping("book/{idBook}")
    public HttpStatus deleteById(@PathVariable String idBook){
        return bookService.deleteById(idBook);
    }
}
