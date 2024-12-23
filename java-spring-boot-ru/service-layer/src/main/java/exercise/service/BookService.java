package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        var res = books.stream()
                .map(bookMapper::map)
                .toList();
        return res;
    }

    public BookDTO create(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
        var author = authorRepository.findById(bookData.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        book.setAuthor(author);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
