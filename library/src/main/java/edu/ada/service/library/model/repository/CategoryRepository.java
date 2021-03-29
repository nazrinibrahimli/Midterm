package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

}
