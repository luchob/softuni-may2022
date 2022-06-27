package bg.softuni.books.service;

import bg.softuni.books.model.dto.AuthorDTO;
import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }


  public void deleteBookById(Long bookId) {
    bookRepository.deleteById(bookId);
  }

  public Optional<BookDTO> getBookById(Long bookId) {
    return bookRepository.
        findById(bookId).
        map(this::map);
  }

  public List<BookDTO> getAllBooks() {
    return bookRepository.
        findAll().
        stream().
        map(this::map).
        collect(Collectors.toList());
  }

  public Long createBook(BookDTO bookDTO) {
    // TODO: implement the method.

    return 50L;//TODO:
  }

  private BookDTO map(BookEntity bookEntity) {
    return new
        BookDTO().
          setId(bookEntity.getId()).
          setTitle(bookEntity.getTitle()).
          setIsbn(bookEntity.getIsbn()).
        setAuthor(new AuthorDTO().setName(bookEntity.getAuthor().getName()));
  }

}
