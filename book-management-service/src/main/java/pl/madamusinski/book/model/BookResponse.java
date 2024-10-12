package pl.madamusinski.book.model;

import lombok.Builder;

@Builder
public record BookResponse(String title, String author, String isbn, String category) {
}
