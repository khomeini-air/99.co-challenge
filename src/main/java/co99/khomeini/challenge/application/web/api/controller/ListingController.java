package co99.khomeini.challenge.application.web.api.controller;

import co99.khomeini.challenge.application.web.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.web.api.response.GetListingsResponse;
import co99.khomeini.challenge.application.web.service.ListingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ListingController {
    private Logger logger = Logger.getLogger(ListingController.class.getName());

    @Autowired
    private ListingService listingService;

    @GetMapping("/public-api/listings")
    public ResponseEntity<GetListingsResponse> findAllListing(@RequestParam(defaultValue = "1") @Min(1)  final Integer page,
                                         @RequestParam(defaultValue = "10") @Min(1) final Integer size,
                                         @RequestParam(required = false, name = "user_id") final Integer userId) throws JsonProcessingException {
        final GetListingsResponse response = listingService.findAll(userId, page, size);
        return CollectionUtils.isEmpty(response.getListings()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(response) : ResponseEntity.ok(response);
    }

    @PostMapping("/public-api/listings")
    public ResponseEntity<CreateListingResponse> createListing(@RequestBody final Map<String, String> params) throws JsonProcessingException {
        final CreateListingResponse result = listingService.createListing(Integer.valueOf(params.get("user_id")),
                params.get("listing_type"),
                Integer.valueOf(params.get("price")));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
