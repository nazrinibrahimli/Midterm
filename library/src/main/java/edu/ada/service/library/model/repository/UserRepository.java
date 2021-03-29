package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByToken(String token);
//    UserEntity findByEmail(String email);

}
