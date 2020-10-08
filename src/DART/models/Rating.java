package DART.models;

import java.util.UUID;

public class Rating {
    private UUID customerId;
    private UUID productId;
    private Integer rating;
    private String writtenReview;

    public Rating(UUID customerId, UUID productId, Integer rating, String writtenReview){
        this.customerId = customerId;
        this.productId = productId; //SEARCH FOR PRODUCT ID
        this.rating = rating;
        this.writtenReview = writtenReview;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getWrittenReview() {
        return writtenReview;
    }

    public UUID getProductId() {
        return productId;
    }
}