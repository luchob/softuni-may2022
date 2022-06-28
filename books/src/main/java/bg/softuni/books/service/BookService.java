package bg.softuni.books.service;

import bg.softuni.books.model.dto.AuthorDTO;
import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorService authorService;

  public BookService(BookRepository bookRepository, AuthorService authorService) {
    this.bookRepository = bookRepository;
    this.authorService = authorService;
  }


  public void deleteBookById(Long bookId) {
    try {
      this.bookRepository.deleteById(bookId);
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
    }
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
    String authorName = bookDTO.getAuthor().getName();
    Optional<AuthorEntity> authorOpt = this.authorService.findAuthorByName(authorName);

    BookEntity entityToBeSaved = new BookEntity()
            .setTitle(bookDTO.getTitle())
            .setIsbn(bookDTO.getIsbn())
            .setAuthor(authorOpt.isPresent()
                    ? authorOpt.get()
                    : this.authorService.save(new AuthorEntity().setName(authorName)));

    this.bookRepository.save(entityToBeSaved);

    return entityToBeSaved.getId();
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
