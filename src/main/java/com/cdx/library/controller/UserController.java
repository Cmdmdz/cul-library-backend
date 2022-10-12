package com.cdx.library.controller;

import com.cdx.library.documents.BookList;
import com.cdx.library.model.request.BorrowUserRequest;
import com.cdx.library.model.request.UserRequest;
import com.cdx.library.model.response.BorrowUserResponse;
import com.cdx.library.model.response.UserResponse;
import com.cdx.library.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)

public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public UserResponse register(@RequestBody UserRequest request){
        return userService.register(request);
    }

    @PostMapping("login")
    public UserResponse login(@RequestBody UserRequest request){
        return userService.login(request);
    }

    @PutMapping("user/{userId}")
    public BorrowUserResponse borrowedBook(@PathVariable String userId,@RequestBody BorrowUserRequest request){
       return userService.borrowedBook(userId,request);
    }

    @GetMapping("user/{userId}")
    public List<BookList> getBorrowedBookList(@PathVariable String userId){
        return userService.getBorrowedBookList(userId);
    }
}
