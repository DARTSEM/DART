package DART.models.products;

import DART.enums.ProductType;

public class Game extends Product {
    private String genre;

    public Game(String title, String genre, double dailyRentFee, boolean available) {

        super(title, dailyRentFee, ProductType.GAME, available);

        this.genre = genre;

    }

    public String getGenre() {
        return genre;
    }


    @Override
    public String toString() {
        return getId() + " : '" + getTitle() + "'. Genre: " + getGenre() + ". Price: " + getDailyRentFee() +
                " SEK/day. - " + printAvailable();
    }
}


