package co99.khomeini.challenge.application.listing.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="LISTINGS")
public class ListingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "listing_type")
    private String type;

    @Column(name = "price")
    private Integer price;

    @Column(name = "created_at")
    private Long createdAt = System.currentTimeMillis();

    @Column(name = "updated_at")
    private Long updatedAt = System.currentTimeMillis();

    public ListingEntity(Integer userId, String type, Integer price) {
        this.userId = userId;
        this.type = type;
        this.price = price;
    }
}
