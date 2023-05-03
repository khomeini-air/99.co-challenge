package co99.khomeini.challenge.application.user.service;

import co99.khomeini.challenge.application.user.api.response.GetOrCreateUserResponse;
import co99.khomeini.challenge.application.user.api.response.GetUsersResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public GetOrCreateUserResponse createUser(final String name);
    public GetUsersResponse findAll(final int page, final int size);
    public GetOrCreateUserResponse findById(final Integer id);

}
