package co99.khomeini.challenge.application.user.dao.repository;

import co99.khomeini.challenge.application.user.dao.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    public UserEntity save(UserEntity userEntity);
    public UserEntity findById(Integer id);
}
