package DART.models.products;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Rating {
    private UUID customerId;
    private UUID productId;
    private ArrayList<Integer> rating;
    private Integer productRating;
    public String writtenReview;

    public Rating(Integer productRating, String writtenReview) {
        this.rating = rating;
        this.writtenReview = writtenReview;
        this.productRating = productRating;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public ArrayList<Integer> getRating() {
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
        return getProductRating() + "stars! Written review:\n" + getWrittenReview();
    }
}
