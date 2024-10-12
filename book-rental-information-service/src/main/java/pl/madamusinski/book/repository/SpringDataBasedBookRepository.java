package pl.madamusinski.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.madamusinski.book.model.BookEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataBasedBookRepository extends JpaRepository<BookEntity, Long> {

    @Query(value = "SELECT book from BookEntity as book WHERE book.borrower IS NOT NULL")
    List<BookEntity> findAllRented();

    @Query(value = "SELECT book from BookEntity as book WHERE book.isbn = :isbn")
    Optional<BookEntity> findBookByIsbn(@Param("isbn") String isbn);
}
