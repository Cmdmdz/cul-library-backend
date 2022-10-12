package com.cdx.library.model.response;

import com.cdx.library.documents.BookList;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowUserResponse {
    private List<BookList> bookList;
}
