package DART.models.products;

import DART.enums.ProductType;

import java.text.DecimalFormat;

public class Game extends Product {
    private String genre;

    public Game(String title, String genre, int releaseYear, double dailyRentFee, boolean available) {
        super(title, dailyRentFee, releaseYear ,ProductType.GAME, available);
        this.genre = genre;

    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return getId() + " : '" + getTitle() + "'. Genre: " + getGenre()  + ". Released in "
                + getReleaseYear() + ". Price: " + getDailyRentFee() + " SEK/day. - " + printAvailable() + checkRating();
    }
}
