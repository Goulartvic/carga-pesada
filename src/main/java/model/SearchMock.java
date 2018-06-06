package model;

public class SearchMock {

    private String customer;

    private String rating;

    private String kmPrice;

    public SearchMock(String customer, String rating, String kmPrice) {
        this.customer = customer;
        this.rating = rating;
        this.kmPrice = kmPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public String getRating() {
        return rating;
    }

    public String getKmPrice() {
        return kmPrice;
    }
}
