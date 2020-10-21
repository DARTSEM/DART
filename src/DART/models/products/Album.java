package DART.models.products;

import DART.enums.ProductType;

import java.text.DecimalFormat;

public class Album extends Product {
    private String artist;

    public Album(String title, String artist, int releaseYear, double dailyRentFee, boolean available) {

        super(title, dailyRentFee, releaseYear, ProductType.ALBUM, available);

        this.artist = artist;

    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return getId() + " : '" + getTitle() + "' by " + getArtist() + ". Released in " + getReleaseYear() +
                ". Price: " + getDailyRentFee() + " SEK/day. - " + printAvailable() + checkRating();
    }
}


