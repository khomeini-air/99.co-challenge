package co99.khomeini.challenge.application.listing.api.controller;

import co99.khomeini.challenge.application.listing.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.listing.api.response.GetListingsResponse;
import co99.khomeini.challenge.application.listing.service.ListingService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.logging.Logger;

@RestController
public class ListingController {
    private Logger logger = Logger.getLogger(ListingController.class.getName());

    @Autowired
    protected ListingService listingService;

    @GetMapping("/listings")
    public ResponseEntity<GetListingsResponse> getAllListing(@RequestParam(defaultValue = "1") @Min(1)  final Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) final Integer size,
                                                             @RequestParam(required = false, name = "user_id") final Integer userId) {
        final GetListingsResponse response = ObjectUtils.isEmpty(userId) ? listingService.findAll(page-1, size) : listingService.findAllByUserId(userId, page-1, size);

        return Objects.isNull(response.getListings()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(response) : ResponseEntity.ok(response);
    }

    @PostMapping("/listings")
    public ResponseEntity<CreateListingResponse> createListing(@RequestParam(name = "user_id") @Min(1)  final Integer userId,
                                                               @RequestParam(name = "listing_type") final String listingType,
                                                               @RequestParam(required = false, name = "price") final Integer price) {
        final CreateListingResponse response = listingService.createListing(userId, listingType, price);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
