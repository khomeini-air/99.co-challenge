package co99.khomeini.challenge.application.web.service;

import co99.khomeini.challenge.application.web.api.model.User;
import co99.khomeini.challenge.application.web.api.response.CreateUserResponse;
import co99.khomeini.challenge.application.web.integration.service.UserWebClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserWebClientService webClientService;

    @Override
    public CreateUserResponse createUser(final String name) throws JsonProcessingException {
        final User user = webClientService.createUser(name);
        return new CreateUserResponse(true, user);
    }
}
