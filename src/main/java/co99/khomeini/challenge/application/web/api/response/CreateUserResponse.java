package co99.khomeini.challenge.application.web.api.response;

import co99.khomeini.challenge.application.web.api.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateUserResponse extends BaseResponse {
    private User user;

    public CreateUserResponse(final Boolean result, final User user) {
        super(result);
        this.user = user;
    }
}
