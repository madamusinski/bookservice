package pl.madamusinski.book.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "books")
public class BookDocument {
    @Id
    private String id;
    private String title;
    private String author;
    @Indexed(name = "isbn_index", unique = true)
    private String isbn;
    private String category;
    private String borrower;
}
