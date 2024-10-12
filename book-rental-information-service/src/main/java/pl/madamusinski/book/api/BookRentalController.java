package pl.madamusinski.book.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.service.BookRentalInformationService;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
@RequiredArgsConstructor
public class BookRentalController {

    private final BookRentalInformationService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getRentedBooks() {
        return ResponseEntity.ok(bookService.getAllRentedBooks());
    }
}
