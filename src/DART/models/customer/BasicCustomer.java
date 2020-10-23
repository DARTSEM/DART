package DART.models.customer;

public class BasicCustomer implements CustomerMembership {

    @Override
    public String getName() { return "BASIC"; }

    @Override
    public double getDiscount() { return 1.00; }

    @Override
    public int maxRent() { return 1; }

    @Override
    public int getCredits() { return 0; }

    @Override
    public double getFreeProduct() { return 0.0; }

    @Override
    public String toString() { return getName(); }

}
