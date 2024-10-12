package pl.madamusinski.book.model;

import lombok.Builder;

@Builder
public record Book(String id, String title, String author, String isbn, String category, String borrower) {
    public static Book.BookBuilder copyFrom(Book book) {
        return Book.builder()
                .id(book.id())
                .title(book.title())
                .author(book.author())
                .isbn(book.isbn())
                .category(book.category())
                .borrower(book.borrower());

    }
}
