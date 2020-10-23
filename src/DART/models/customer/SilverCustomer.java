package DART.models.customer;

public class SilverCustomer implements CustomerMembership {

    @Override
    public String getName() { return "SILVER"; }

    @Override
    public double getDiscount() { return 0.9; }

    @Override
    public double getFreeProduct() { return 0.0; }

    @Override
    public int maxRent() { return 3; }

    @Override
    public int getCredits() { return 1; }

}
