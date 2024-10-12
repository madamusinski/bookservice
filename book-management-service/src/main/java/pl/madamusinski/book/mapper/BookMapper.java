package pl.madamusinski.book.mapper;

import lombok.experimental.UtilityClass;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.model.BookDocument;
import pl.madamusinski.book.model.BookResponse;

@UtilityClass
public class BookMapper {

    public static Book toDto(BookDocument document) {
        return Book.builder()
                .title(document.getTitle())
                .author(document.getAuthor())
                .isbn(document.getIsbn())
                .category(document.getCategory())
                .borrower(document.getBorrower())
                .build();
    }

    public static BookDocument toDocument(Book book) {
        return BookDocument.builder()
                .title(book.title())
                .author(book.author())
                .isbn(book.isbn())
                .category(book.category())
                .borrower(book.borrower())
                .build();
    }

    public static BookResponse toResponse(Book dto) {
        return BookResponse.builder()
                .title(dto.title())
                .author(dto.author())
                .isbn(dto.isbn())
                .category(dto.category())
                .build();
    }
}
