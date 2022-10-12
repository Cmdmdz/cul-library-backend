package com.cdx.library.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private String idBook;
    private String bookName;
    private String detail;
    private String imageUrl;
}
