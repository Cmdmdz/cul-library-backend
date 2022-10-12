package com.cdx.library.service.user;

import com.cdx.library.documents.BookList;
import com.cdx.library.documents.User;
import com.cdx.library.exception.CustomerException;
import com.cdx.library.exception.NoSuchElementFoundException;
import com.cdx.library.model.request.BorrowUserRequest;
import com.cdx.library.model.request.UserRequest;
import com.cdx.library.model.response.BorrowUserResponse;
import com.cdx.library.model.response.UserResponse;
import com.cdx.library.repositorier.BookRepository;
import com.cdx.library.repositorier.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BookRepository bookRepository;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static long TIME_BORROW_BOOK = 3;
    @Override
    public UserResponse register(UserRequest request) {

        if (Boolean.TRUE.equals(userRepository.existsByUsername(request.getUsername()))) {
            throw new CustomerException("already exist username : " + request.getUsername());
        }

        var user = userRepository.save(User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build());

        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

    }

    @Override
    public UserResponse login(UserRequest request) {

        if (Boolean.TRUE.equals(userRepository.existsByUsernameAndPassword(request.getUsername(), request.getPassword()))) {
            byte[] TOKEN = new byte[24];
            secureRandom.nextBytes(TOKEN);
            var user = userRepository.findByUsername(request.getUsername());

            if (user.isPresent()){

                return UserResponse.builder()
                        .userId(user.get().getUserId())
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .token(base64Encoder.encodeToString(TOKEN))
                        .build();
            }

            throw new NoSuchElementFoundException("Not found username : ");

        } else {
            throw new NoSuchElementFoundException("Not found username and password : ");
        }
    }

    @Override
    public BorrowUserResponse borrowedBook(String userId,BorrowUserRequest request){

        var userOptional = userRepository.findById(userId);
        var bookOptional = bookRepository.findById(request.getIdBook());
        if (userOptional.isPresent() && bookOptional.isPresent()){
            var borrowDate = LocalDateTime.now();
            var sendDate = borrowDate.plusDays(TIME_BORROW_BOOK);
            var user = userOptional.get();

            if (user.getMyBook() == null){
                user.setMyBook(List.of(BookList.builder()
                        .idBook(request.getIdBook())
                        .borrowDate(borrowDate.toString())
                        .bookName(bookOptional.get().getBookName())
                        .sendDate(sendDate.toString())
                        .build()));
            }else {
            var bookList = user.getMyBook();
            bookList.add(BookList.builder()
                    .idBook(request.getIdBook())
                    .borrowDate(borrowDate.toString())
                    .bookName(bookOptional.get().getBookName())
                    .sendDate(sendDate.toString())
                    .build());
            user.setMyBook(bookList);
        }

            var save = userRepository.save(user);

            return BorrowUserResponse.builder()
                    .bookList(save.getMyBook())
                    .build();

        }else {

            throw new NoSuchElementFoundException("Not found userId : "+ userId);
        }
    }

    @Override
    public List<BookList> getBorrowedBookList(String userId){
        var userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()){
            var user = userOptional.get();

            return user.getMyBook();
        }else {
            throw new NoSuchElementFoundException("Not found userId : "+ userId);
        }
    }


}
