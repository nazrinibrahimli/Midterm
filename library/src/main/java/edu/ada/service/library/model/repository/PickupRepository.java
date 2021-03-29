package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.BookEntity;
import edu.ada.service.library.model.entity.PickupEntity;
import edu.ada.service.library.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PickupRepository extends CrudRepository<PickupEntity, Long> {
    List<PickupEntity> findByUser(UserEntity userEntity);
    PickupEntity findByBookAndDropOffFalse(BookEntity bookEntity);
    List<PickupEntity> findByUserAndDropOffFalse(UserEntity userEntity);
    PickupEntity findByBookAndUserAndDropOffFalse(BookEntity bookEntity, UserEntity userEntity);
}
