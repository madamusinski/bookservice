package pl.madamusinski.book.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Objects;

@Table(name = "book")
@Entity
@Data
public final class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String author;
    private @NotBlank @Column(unique = true) String isbn;
    private String category;
    private String borrower;

    // required for hibernate
    public BookEntity() {

    }

    public BookEntity(Long id, String title,
            String author,
            @NotBlank String isbn,
            String category,
            String borrower) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.borrower = borrower;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BookEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.author, that.author) &&
                Objects.equals(this.isbn, that.isbn) &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, category, borrower);
    }

    @Override
    public String toString() {
        return "BookEntity[" +
                "id=" + id + ", " +
                "title=" + title + ", " +
                "author=" + author + ", " +
                "isbn=" + isbn + ", " +
                "category=" + category + ", " +
                "borrower=" + borrower + ']';
    }


}
