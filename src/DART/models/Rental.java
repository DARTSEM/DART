package DART.models;

import DART.models.products.Product;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Rental {
    private Customer customer;
    private Product product;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Rental( Customer customer, Product product, LocalDate rentDate ) {
        this.customer = customer;
        this.product = product;
        this.product.rentedProduct();
        this.rentDate = rentDate;
        this.returnDate = null;
    }

    public void returnRental (LocalDate returnDate) {
        this.product.returnedProduct();
        this.returnDate = returnDate;
    }

    public Double totalRentFee() {
        LocalDate endDate = this.returnDate == null ? LocalDate.now() : this.returnDate; //
        long daysBetween = Duration.between(this.rentDate, endDate).toDays();
        Double fee = daysBetween * this.product.getDailyRentFee();
        //gross = mCustomerTier.applyDiscount(gross amount)
        return fee;
    }

    public Customer getCustomer() { return this.customer; }

    //modify to reflect changes in architecture
    public void rentProduct(Customer customer, Product product, LocalDate rentDate) {
        Rental rental = new Rental(customer, product, rentDate);

        rentals.add(rental);
    }

    //update membership and credits, check if free w redeemable credits
    //allow but not require rating and review, included in item
    public Double returnProduct(Rental rental, LocalDate returnDate) {
        rental.returnRental(returnDate);
        //returns total amount incurred
        return rental.totalRentFee();
    }

    //modify to reflect changes in architecture
    //returns all rentals associated with specific customer ID
    public Collection<Rental> getRentalsForCustomer(Customer customer) {
        ArrayList<Rental> customerRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            if (customer.getId().equals(rental.getCustomer().getId())) {
                customerRentals.add(rental);
            }
        }
        return customerRentals;
    }

    //modify to reflect changes in architecture
    // calculates total profit for all rentals
    public Double getTotalProfit() {
        Double totalProfit = 0.0;
        for (Rental rental : rentals) {
            totalProfit += rental.totalRentFee();
        }
        return totalProfit;
    }

    //pass in enumeration albums, games, all
    //filters products by type
    //modify to reflect changes in architecture
    public Collection<Product> getProductByType(ProductType productType) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product: products) {
            if (productType == null) {
                filteredProducts.add(product);
            } else if (productType == product.getProductType()) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    //rent frequency for items

    //best customer

}


