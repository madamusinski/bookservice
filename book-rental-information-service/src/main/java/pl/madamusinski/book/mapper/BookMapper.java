package pl.madamusinski.book.mapper;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.model.BookEntity;

@UtilityClass
public class BookMapper {

    public static Book toDto(BookEntity entity) {
        return Book.builder()
                .id(String.valueOf(entity.getId()))
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .isbn(entity.getIsbn())
                .category(entity.getCategory())
                .borrower(entity.getBorrower())
                .build();
    }

    public static BookEntity toEntity(Book book) {
        return new BookEntity(parseBookId(book), book.title(), book.author(), book.isbn(), book.category(), book.borrower());
    }

    private Long parseBookId(Book book) {
        return StringUtils.isNotBlank(book.id()) ? Long.valueOf(book.id()) : null;
    }

}
