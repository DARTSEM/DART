package DART.models.customer;

public class GoldCustomer implements CustomerMembership {

    @Override
    public String getName() { return "GOLD"; }

    @Override
    public double getDiscount() { return 0.85; }

    @Override
    public double getFreeProduct() { return 0.0; }

    @Override
    public int maxRent() { return 5; }

    @Override
    public int getCredits() { return 2; }

    @Override
    public String toString() { return getName(); }

}
