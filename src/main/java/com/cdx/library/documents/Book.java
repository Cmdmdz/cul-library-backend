package com.cdx.library.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection= "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Book {
    @Id
    private String idBook;
    private String bookName;
    private String detail;

}
