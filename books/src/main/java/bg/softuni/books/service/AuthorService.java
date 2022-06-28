package bg.softuni.books.service;

import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    Optional<AuthorEntity> findAuthorByName(String name) {
       return this.authorRepository.findByName(name);
    }

    public AuthorEntity save(AuthorEntity newAuthor) {
        return this.authorRepository.save(newAuthor);
    }
}
