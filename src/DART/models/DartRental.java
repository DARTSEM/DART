package com.julia.dart.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DartRental {
    private DartCustomer mCustomer;
    private DartProduct mProduct;
    private LocalDate mRentDate;
    private LocalDate mReturnDate;
    private ICustomerTier mCustomerTier;


    public DartRental(DartCustomer customer, DartProduct product, LocalDate rentDate, ICustomerTier customerTier) {
        mCustomer = customer;
        mProduct = product;
        mProduct.rentProduct();
        mRentDate = rentDate;
        mReturnDate = null;
        mCustomerTier = customerTier;

    }

    public void returnRental(LocalDate returnDate) {
        mProduct.returnProduct();
        mReturnDate = returnDate;
    }

    public Double totalRentFee() {
        LocalDate endDate = mReturnDate == null ? LocalDate.now() : mReturnDate; //
        long daysBetween = Duration.between(mRentDate, endDate).toDays();
        Double fee = daysBetween * mProduct.getDailyRentFee();
        //gross = mCustomerTier.applyDiscount(gross amount)
        return fee;
    }

    public DartCustomer getCustomer() { return mCustomer;}

}

//return customer tier