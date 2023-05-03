package co99.khomeini.challenge.application.web.integration.service;

import co99.khomeini.challenge.application.web.api.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserWebClientService {
    public User createUser(final String name) throws JsonProcessingException;
}
