package co99.khomeini.challenge.application.listing.service;

import co99.khomeini.challenge.application.listing.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.listing.api.response.GetListingsResponse;
import co99.khomeini.challenge.application.listing.dao.entity.ListingEntity;
import co99.khomeini.challenge.application.listing.dao.repository.ListingRepository;
import co99.khomeini.challenge.application.listing.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {
    @Autowired
    protected ListingRepository repository;

    @Override
    public GetListingsResponse findAll(final int page, final int size) {
        final Page<ListingEntity> listingPage = repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));

        if (!listingPage.hasContent()) {
            return new GetListingsResponse(false, null);
        }

        return new GetListingsResponse(true, convertToListingModel(listingPage.getContent()));
    }

    @Override
    public GetListingsResponse findAllByUserId(final Integer userId, final int page, final int size) {
        final Page<ListingEntity> listingPage = repository.findAllByUserId(userId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));

        if (!listingPage.hasContent()) {
            return new GetListingsResponse(false, null);
        }

        return new GetListingsResponse(true, convertToListingModel(listingPage.getContent()));
    }

    @Override
    public CreateListingResponse createListing(Integer userId, String type, Integer price) {
        final ListingEntity entity = repository.save(new ListingEntity(userId, type, price));

        return new CreateListingResponse(true, new Listing(entity.getId(), entity.getUserId(), entity.getType(),
                entity.getPrice(), entity.getCreatedAt(), entity.getUpdatedAt()));
    }

    private List<Listing> convertToListingModel(final List<ListingEntity> listingEntities) {
        if (CollectionUtils.isEmpty(listingEntities)) {
            return Collections.emptyList();
        }

        return listingEntities.stream().map(e -> new Listing(e.getId(), e.getUserId(), e.getType(), e.getPrice(),
                e.getCreatedAt(), e.getUpdatedAt())).collect(Collectors.toList());
    }
}
