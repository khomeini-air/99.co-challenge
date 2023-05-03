package co99.khomeini.challenge.application.listing.service;

import co99.khomeini.challenge.application.listing.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.listing.api.response.GetListingsResponse;

public interface ListingService {
    public GetListingsResponse findAll(final int page, final int size);
    public GetListingsResponse findAllByUserId(final Integer userId, final int page, final int size);
    public CreateListingResponse createListing(final Integer userId, final String type, final Integer price);

}
