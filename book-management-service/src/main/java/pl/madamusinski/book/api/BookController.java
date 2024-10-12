package pl.madamusinski.book.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.madamusinski.book.model.Book;
import pl.madamusinski.book.model.BookResponse;
import pl.madamusinski.book.service.BookService;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> rentBook(@Valid @NotBlank @RequestParam String clientName, @Valid @RequestParam String isbn) {
        return ResponseEntity.ok(bookService.updateBookStatusRented(isbn, clientName));
    }
}
