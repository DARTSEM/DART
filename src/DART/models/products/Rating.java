package DART.models.products;

import java.util.UUID;

public class Rating {
   private UUID customerId;
   private UUID productId;
   private Integer rating;
   private String writtenReview;

   public Rating(Integer rating, String writtenReview){
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
