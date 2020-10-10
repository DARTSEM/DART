package DART.models;

import DART.models.products.Product;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

public class Rental {
    private Customer customer;
    private Product product;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private Double membershipDiscount;

    public Rental( Customer customer, Product product, LocalDate rentDate ) {
        this.customer = customer;
        this.product = product;
        this.product.rentedProduct();
        this.rentDate = rentDate;
        this.returnDate = null;
        this.membershipDiscount = customer.getDiscount();
    }

    public void returnRental (LocalDate returnDate) {
        this.product.returnedProduct();
        this.returnDate = returnDate;
    }

    public Double totalRentFee() {
        LocalDate endDate = this.returnDate == null ? LocalDate.now() : this.returnDate; //
        // long daysBetween = Duration.between(this.rentDate, endDate).toDays();
        long daysBetween = Period.between(rentDate, endDate).getDays() + 1;
        Double fee = daysBetween * this.product.getDailyRentFee();
        Double discountedFee = this.membershipDiscount * fee;
        return discountedFee;
    }

    public Customer getCustomer() { return this.customer; }

    public Product getProduct() { return this.product; }

    //rent frequency for items
}