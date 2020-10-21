package DART.models.products;

import DART.enums.ProductType;
import DART.models.Customer;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Product implements Comparable<Product> {
    private UUID Id;
    private String title;
    private double dailyRentFee;
    private boolean available;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private ProductType productType;
    private int productRating;
    private Double averageRating;
    private int releaseYear;
    public ArrayList<Integer> ratings;

    public Product(String title, double dailyRentFee, int releaseYear, ProductType productType, boolean available) {
        this.Id = UUID.randomUUID();
        this.title = title;
        this.dailyRentFee = dailyRentFee;
        this.available = available;
        this.productType = productType;
        this.ratings = new ArrayList<Integer>();
        this.averageRating = averageRating;
        this.releaseYear = releaseYear;
    }

    public UUID getId() {
        return Id;
    }

    public int getProductRating() {
        return productRating;
    }

    public int getReleaseYear(){ return releaseYear; }

    public String getTitle() { return title;}

    public double getDailyRentFee() {
        return dailyRentFee;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public void rentedProduct() {
        this.available = false;
    }

    public void returnedProduct() {
        this.available = true;
    }

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public double getAverageRatings() {
        Integer sum = 0;
        double rating;
        if(!ratings.isEmpty()) {
            for (Integer productRating : ratings) {
                sum += productRating;
            }
            return sum.doubleValue() / ratings.size();
        }
        return sum;
    }

    public String printAvailable() {
        String availablePrint;
        if (available == true) {
            availablePrint = "AVAILABLE";
        } else {
            availablePrint = "UNAVAILABLE";
        }
        return availablePrint;
    }

    public String checkRating(){
        String checkRating = null;
        if(getAverageRatings() == 0){
            checkRating = " - No current ratings.";
        } else {
            checkRating = " - Average rating of: " + String.valueOf(df2.format(getAverageRatings()));
        }
        return checkRating;
    }

    @Override
    public int compareTo(Product anotherProduct) {

        if(this.releaseYear < anotherProduct.getReleaseYear()) {
            return -1;
        } else if (this.releaseYear == anotherProduct.getReleaseYear()) {
            return 0;
        } else {
            return 1;
        }
    }
}

