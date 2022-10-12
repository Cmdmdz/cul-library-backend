package com.cdx.library.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Document(collection= "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class User {

    @Id
    private String userId;

    private String username;

    private String password;

    private List<BookList> myBook;
}
