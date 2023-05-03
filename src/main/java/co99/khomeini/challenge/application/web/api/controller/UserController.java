package co99.khomeini.challenge.application.web.api.controller;

import co99.khomeini.challenge.application.web.api.response.CreateUserResponse;
import co99.khomeini.challenge.application.web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService service;

    @PostMapping("/public-api/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody final Map<String, String> params) throws JsonProcessingException {
        final CreateUserResponse result = service.createUser(params.get("name"));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
