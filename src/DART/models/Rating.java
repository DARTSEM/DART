package DART.models;

import java.util.ArrayList;
import java.util.UUID;

public class Rating {
    private UUID customerId;
    private UUID productId;
    private ArrayList<Rating> rating;
    private Integer productRating;
    public String writtenReview;

    public Rating(Integer productRating, String writtenReview) {
        this.writtenReview = writtenReview;
        this.productRating = productRating;

    }

    public UUID getCustomerId() {
        return customerId;
    }

    public ArrayList<Rating> getAllRatings() {
        return rating;
    }


    public String getWrittenReview() {
        return writtenReview;
    }

    public UUID getProductId() {
        return productId;
    }

    public Integer getProductRating(){return productRating;}

    public String toString() {
        return "Rating: " + getProductRating() + " out of 5. Written review:\n" + getWrittenReview();
    }
}
