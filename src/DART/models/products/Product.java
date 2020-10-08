package DART.models.products;

import DART.enums.ProductType;

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

    public Product(String title, double dailyRentFee, ProductType productType) {

        this.Id = UUID.randomUUID();
        this.title = title;
        this.dailyRentFee = dailyRentFee;
        this.available = true;
        this.rentDate = LocalDate.now();
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

    public LocalDate getRentDate() {
        return this.rentDate = LocalDate.now();
    }

    public LocalDate getReturnDate() {
        return this.returnDate = LocalDate.now();
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public LocalDate setRentDate() {
        return this.rentDate = LocalDate.now();
    }

    public LocalDate setReturnDate() {
        return this.returnDate = LocalDate.now();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    // in controller, you do setAvailable(false) to set availability of product to false.

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDailyRentFee(double dailyRentFee) {
        this.dailyRentFee = dailyRentFee;
    }

    public String printAvailable() {
        String availablePrint;
        if (available = true) {
            availablePrint = "AVAILABLE";
        } else {
            availablePrint = "UNAVAILABLE";
        }
        return availablePrint;
    }

    public Double totalRentFee() {
        LocalDate endDate = returnDate == null ? LocalDate.now() : returnDate; //
        long daysBetween = Period.between(rentDate, endDate).getDays() + 1; // Period counts days between x and y,
        // with Days converting it to days (instead of e.g. seconds)
        double fee = daysBetween * getDailyRentFee();
        return fee;
    }

}


