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

  public BookDTO persistBook(Long id, BookDTO bookDTO) {

    Optional<BookEntity> bookOpt = this.bookRepository.findById(id);

    if(bookOpt.isEmpty()) {
      return null;
    }

    BookEntity bookEntity = bookOpt.get();

    updateBookEntity(bookEntity, bookDTO);

    this.bookRepository.save(bookEntity);

    return this.map(bookEntity);
  }

  private void updateBookEntity(BookEntity bookEntity, BookDTO bookDTO) {
    String title = bookDTO.getTitle();
    if(title != null) {
      bookEntity.setTitle(title);
    }

    String isbn = bookDTO.getIsbn();
    if(isbn != null) {
      bookEntity.setIsbn(isbn);
    }

    if(bookDTO.getAuthor() != null) {
      Optional<AuthorEntity> authorOpt = this.authorService.findAuthorByName(bookDTO.getAuthor().getName());

      bookEntity.setAuthor(authorOpt.isEmpty()
              ? this.authorService.save(new AuthorEntity().setName(bookDTO.getAuthor().getName()))
              : authorOpt.get());
    }
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
