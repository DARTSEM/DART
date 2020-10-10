package DART.models.products;

import DART.enums.ProductType;
import DART.models.Customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Product {
    protected UUID Id;
    protected String title;
    protected double dailyRentFee;
    protected boolean available;
    protected LocalDate rentDate;
    protected LocalDate returnDate;
    protected ProductType productType;
    protected int productRating;

    public Product(String title, double dailyRentFee, ProductType productType, boolean available) {
        this.Id = UUID.randomUUID();
        this.title = title;
        this.dailyRentFee = dailyRentFee;
        this.available = available;
        this.productType = productType;
    }

    public UUID getId() {
        return Id;
    }

    public int getProductRating() {
        return productRating;
    }

    public String getTitle() {
        return title;
    }

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

    public String printAvailable() {
        String availablePrint;
        if (available == true) {
            availablePrint = "AVAILABLE";
        } else {
            availablePrint = "UNAVAILABLE";
        }
        return availablePrint;
    }
}

