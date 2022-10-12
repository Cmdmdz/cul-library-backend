package com.cdx.library.service.book;

import com.cdx.library.documents.Book;
import com.cdx.library.exception.CustomerException;
import com.cdx.library.exception.NoSuchElementFoundException;
import com.cdx.library.model.request.BookRequest;
import com.cdx.library.model.response.BookResponse;
import com.cdx.library.repositorier.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookResponse> findAllBook() {

        try {
            return bookRepository.findAll()
                    .stream()
                    .map(book -> BookResponse.builder()
                            .bookName(book.getBookName())
                            .idBook(book.getIdBook())
                            .detail(book.getDetail())
                            .imageUrl("https://source.unsplash.com/random/300x200?sig=1")
                            .build())
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new CustomerException("error find all book",e);
        }


    }

    @Override
    public BookResponse findById(String idBook) {

        var bookOptional = bookRepository.findById(idBook);
        if (bookOptional.isPresent()){

            var book = bookOptional.get();
            return BookResponse.builder()
                    .bookName(book.getBookName())
                    .idBook(book.getIdBook())
                    .detail(book.getDetail())
                    .imageUrl("https://source.unsplash.com/random/300x200?sig=1")
                    .build();
        }else {
            throw  new NoSuchElementFoundException("Not found idBook : "+idBook);
        }

    }

    @Override
    public BookResponse updateBookById(String idBook, BookRequest request) {

        var bookOptional = bookRepository.findById(idBook);
        if (bookOptional.isPresent()){

            var book = bookOptional.get();
            book.setBookName(request.getBookName());
            book.setDetail(request.getDetail());
            var updateBook = bookRepository.save(book);

            return BookResponse.builder()
                    .bookName(updateBook.getBookName())
                    .idBook(updateBook.getIdBook())
                    .detail(updateBook.getDetail())
                    .imageUrl("https://source.unsplash.com/random/300x200?sig=1")
                    .build();
        }else {
            throw  new NoSuchElementFoundException("Not found idBook : "+idBook);
        }

    }

    @Override
    public BookResponse addBook(BookRequest request) {

        var book = bookRepository.save(Book.builder()
                        .bookName(request.getBookName())
                        .detail(request.getDetail())
                .build());

        return BookResponse.builder()
                .bookName(book.getBookName())
                .idBook(book.getIdBook())
                .detail(book.getDetail())
                .imageUrl("https://source.unsplash.com/random/300x200?sig=1")
                .build();
    }

    @Override
    public HttpStatus deleteById(String idBook) {
        try {
            bookRepository.deleteById(idBook);
            return HttpStatus.NO_CONTENT;
        }catch (Exception e){

            throw  new NoSuchElementFoundException("Not found idBook : "+idBook);
        }
    }
}
