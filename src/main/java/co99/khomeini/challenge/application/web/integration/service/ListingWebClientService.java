package co99.khomeini.challenge.application.web.integration.service;

import co99.khomeini.challenge.application.web.api.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ListingWebClientService<T> {
    public List<Listing> findAll(Integer userId, Integer page, Integer size) throws JsonProcessingException;
    public Listing createListing(Integer userId, String type, Integer price) throws JsonProcessingException;
}
