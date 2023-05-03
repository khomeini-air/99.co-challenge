package co99.khomeini.challenge.application.web.service;

import co99.khomeini.challenge.application.web.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.web.api.response.GetListingsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ListingService {
    GetListingsResponse findAll(Integer userId, Integer page, Integer size) throws JsonProcessingException;
    CreateListingResponse createListing(Integer userId, String type, Integer price) throws JsonProcessingException;
}
