package co99.khomeini.challenge.application.web.api.response;

import co99.khomeini.challenge.application.web.api.model.Listing;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetListingsResponse extends BaseResponse {
    private List<Listing> listings;

    public GetListingsResponse(Boolean result, List<Listing> listings) {
        super(result);
        this.listings = listings;
    }
}
