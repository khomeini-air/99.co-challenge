package co99.khomeini.challenge.application.user.service;

import co99.khomeini.challenge.application.user.api.response.GetOrCreateUserResponse;
import co99.khomeini.challenge.application.user.api.response.GetUsersResponse;
import co99.khomeini.challenge.application.user.dao.entity.UserEntity;
import co99.khomeini.challenge.application.user.dao.repository.UserRepository;
import co99.khomeini.challenge.application.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository repository;

    public GetOrCreateUserResponse createUser(final String name) {
        UserEntity userEntity = repository.save(new UserEntity(name));
        return new GetOrCreateUserResponse(true, new User(userEntity.getId(), userEntity.getName(), userEntity.getCreatedAt(), userEntity.getUpdatedAt()));
    }

    public GetUsersResponse findAll(final int page, final int size) {
        final Page<UserEntity> userPage = repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));

        if (!userPage.hasContent()) {
            return new GetUsersResponse(false, null);
        }

        return new GetUsersResponse(true, convertToUserModel(userPage.getContent()));
    }

    public GetOrCreateUserResponse findById(final Integer id) {
        final UserEntity userEntity = repository.findById(id);

        if (Objects.isNull(userEntity)) {
            return new GetOrCreateUserResponse(false, null);
        }

        return new GetOrCreateUserResponse(true, new User(userEntity.getId(), userEntity.getName(), userEntity.getCreatedAt(), userEntity.getUpdatedAt()));
    }

    private List<User> convertToUserModel(final List<UserEntity> userEntities) {
        if (CollectionUtils.isEmpty(userEntities)) {
            return Collections.emptyList();
        }

        return userEntities.stream().map(e -> new User(e.getId(), e.getName(), e.getCreatedAt(), e.getUpdatedAt())).collect(Collectors.toList());
    }
}
