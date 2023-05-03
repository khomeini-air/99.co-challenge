package co99.khomeini.challenge.application.web.service;

import co99.khomeini.challenge.application.web.api.model.Listing;
import co99.khomeini.challenge.application.web.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.web.api.response.GetListingsResponse;
import co99.khomeini.challenge.application.web.integration.service.ListingWebClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {
    @Autowired
    ListingWebClientService webClientService;

    @Override
    public GetListingsResponse findAll(Integer userId, Integer page, Integer size) throws JsonProcessingException {
        List<Listing> listings = webClientService.findAll(userId, page, size);
        return CollectionUtils.isEmpty(listings) ? new GetListingsResponse(false, listings) : new GetListingsResponse(true, listings);
    }

    @Override
    public CreateListingResponse createListing(Integer userId, String type, Integer price) throws JsonProcessingException {
        final Listing listing = webClientService.createListing(userId, type, price);
        return new CreateListingResponse(true, listing);
    }
}
