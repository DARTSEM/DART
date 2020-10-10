package DART.models;

import java.util.UUID;

public class Rating {
    private UUID customerId;
    private UUID productId;
    private Integer rating;
    private String writtenReview;

    public Rating(Integer rating, String writtenReview) {
        this.customerId = customerId;
        this.productId = productId; //SEARCH FOR PRODUCT ID
        this.rating = rating;
        this.writtenReview = writtenReview;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public Integer getRating() {
        return this.rating;
    }

    public String getWrittenReview() {
        return this.writtenReview;
    }

    public UUID getProductId() {
        return this.productId;
    }
}