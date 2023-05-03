package co99.khomeini.challenge.application.user.api.response;

import co99.khomeini.challenge.application.user.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetUsersResponse extends BaseResponse {
    private List<User> users;

    public GetUsersResponse(final Boolean result, final List<User> users) {
        super(result);
        this.users = users;
    }
}
