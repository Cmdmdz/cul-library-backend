package com.cdx.library.service.user;

import com.cdx.library.documents.BookList;
import com.cdx.library.documents.User;
import com.cdx.library.model.request.BorrowUserRequest;
import com.cdx.library.model.request.UserRequest;
import com.cdx.library.model.response.BorrowUserResponse;
import com.cdx.library.model.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public UserResponse register(UserRequest request);
    public UserResponse login(UserRequest request);

    public BorrowUserResponse borrowedBook(String userId, BorrowUserRequest request);
    public List<BookList> getBorrowedBookList(String userId);
}
