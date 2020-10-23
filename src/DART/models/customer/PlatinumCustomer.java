package DART.models.customer;

public class PlatinumCustomer implements CustomerMembership {

    @Override
    public String getName() { return "PLATINUM"; }

    @Override
    public double getDiscount() { return 0.75; }

    @Override
    public double getFreeProduct() { return 0.0; }

    @Override
    public int maxRent() { return 7; }

    @Override
    public int getCredits() { return 3; }

    @Override
    public String toString() { return getName(); }

}
