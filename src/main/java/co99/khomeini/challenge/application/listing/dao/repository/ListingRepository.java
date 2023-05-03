package co99.khomeini.challenge.application.listing.dao.repository;

import co99.khomeini.challenge.application.listing.dao.entity.ListingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ListingRepository extends PagingAndSortingRepository<ListingEntity, Integer> {
    public ListingEntity save(final ListingEntity entity);
    public Page<ListingEntity> findAllByUserId(final Integer userId, final Pageable page);
}
