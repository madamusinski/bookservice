package pl.madamusinski.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.madamusinski.book.model.BookDocument;

import java.util.Optional;

@Repository
public interface MongoDbBookRepository extends MongoRepository<BookDocument, String> {

    @Query("{'isbn': ?0}")
    Optional<BookDocument> findByIsbnAndPerson(String isbn);
}
