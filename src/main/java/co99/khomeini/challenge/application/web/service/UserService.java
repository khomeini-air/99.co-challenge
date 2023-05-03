package co99.khomeini.challenge.application.web.service;

import co99.khomeini.challenge.application.web.api.response.CreateUserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {
    CreateUserResponse createUser(final String name) throws JsonProcessingException;
}
