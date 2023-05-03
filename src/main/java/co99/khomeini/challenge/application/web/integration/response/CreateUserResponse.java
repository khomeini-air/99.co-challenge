package co99.khomeini.challenge.application.web.integration.response;

import co99.khomeini.challenge.application.web.api.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateUserResponse {
    private Boolean result;
    private User user;
}
