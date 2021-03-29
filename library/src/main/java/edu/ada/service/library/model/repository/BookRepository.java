package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAllByNameContainingAndCategory_idAndAuthorContaining(String name, Long id, String author);

    List<BookEntity> findAllByNameContainingOrCategory_idOrAuthorContaining(String name, Long id, String author);
}
