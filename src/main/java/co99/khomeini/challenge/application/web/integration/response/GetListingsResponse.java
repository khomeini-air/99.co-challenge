package co99.khomeini.challenge.application.web.integration.response;

import co99.khomeini.challenge.application.web.api.model.Listing;
import co99.khomeini.challenge.application.web.api.response.BaseResponse;
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
public class GetListingsResponse extends BaseResponse {
    private Boolean result;
    private List<Listing> listings;
}
