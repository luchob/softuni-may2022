package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BooksController {

  private final BookService bookService;

  @Autowired//Autowired not required on constructor
  public BooksController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> getAllBooks() {
    return ResponseEntity.
        ok(bookService.getAllBooks());
  }

  @Tag(name = "Get book by ID", description = "Returns the book details by its id")
  @Parameter(
      name = "id",
      description = "The ID of the book",
      required = true
  )
  @ApiResponse(
      responseCode = "200",
      description = "If the book was retrieved successfully"
  )
  @ApiResponse(
      responseCode = "404",
      description = "If the book was not found"
  )
  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
    Optional<BookDTO> bookOpt = bookService.getBookById(bookId);
    if (bookOpt.isEmpty()) {
      return ResponseEntity.
          notFound().
          build();
    } else {
      return ResponseEntity.
          ok(bookOpt.get());
    }
  }

  @PostMapping
  public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook,
                                            UriComponentsBuilder uriComponentsBuilder) {
    long newBookID = bookService.createBook(newBook);

    return
        ResponseEntity.
            created(uriComponentsBuilder.path("/api/books/{id}").
                build(newBookID)).
            build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long bookId) {
    bookService.deleteBookById(bookId);

    return ResponseEntity.
        noContent().
        build();
  }

  @PostMapping("/{id}")
  public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {

    BookDTO updatedBookDTO = this.bookService.persistBook(id, bookDTO);

    if(updatedBookDTO == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedBookDTO);
  }

}
