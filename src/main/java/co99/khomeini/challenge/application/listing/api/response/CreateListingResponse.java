package co99.khomeini.challenge.application.listing.api.response;

import co99.khomeini.challenge.application.listing.model.Listing;
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
public class CreateListingResponse extends BaseResponse {
    private Listing listing;

    public CreateListingResponse(final Boolean result, final Listing listing) {
        super(result);
        this.listing = listing;
    }
}
