package exercise.controller;

import java.net.URI;
import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDTO>> index() {
        var books = bookService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(books.size()))
                .body(books);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> show(@PathVariable Long id) {
        var dto = bookService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid BookCreateDTO bookData) {
        try {
            var dto = bookService.create(bookData);
            return ResponseEntity.created(URI.create("/books/" + dto.getId()))
                    .body(dto);
        } catch (ResourceNotFoundException e) {
            if ("Author not found".equals(e.getMessage())) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            throw e;
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> update(@RequestBody @Valid BookUpdateDTO bookData, @PathVariable Long id) {
        var dto = bookService.update(bookData, id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
    // END
}
