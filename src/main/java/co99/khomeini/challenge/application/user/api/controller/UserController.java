package co99.khomeini.challenge.application.user.api.controller;

import co99.khomeini.challenge.application.user.api.response.GetOrCreateUserResponse;
import co99.khomeini.challenge.application.user.api.response.GetUsersResponse;
import co99.khomeini.challenge.application.user.service.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.logging.Logger;

@RestController
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    protected UserService userService;

    @GetMapping("/users")
    public ResponseEntity<GetUsersResponse> getAll(@RequestParam(defaultValue = "1") @Min(1)  final Integer page,
                                                   @RequestParam(defaultValue = "10") @Min(1) final Integer size,
                                                   @RequestParam(required = false, name = "user_id") final Integer userId) {
        final GetUsersResponse response = ObjectUtils.isEmpty(userId) ? userService.findAll(page-1, size) : userService.findAll(page-1, size);

        return CollectionUtils.isEmpty(response.getUsers()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(response) : ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetOrCreateUserResponse> getUser(@PathVariable final Integer userId) {
        final GetOrCreateUserResponse response = userService.findById(userId);

        return Objects.isNull(response.getUser()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(response) : ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<GetOrCreateUserResponse> createUser(@RequestParam final String name) {
        final GetOrCreateUserResponse result = userService.createUser(name);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
