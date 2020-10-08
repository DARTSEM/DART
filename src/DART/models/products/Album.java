package DART.models.products;

import DART.enums.ProductType;

public class Album extends Product {
    private String artist;
    private int releaseYear;

    public Album(String title, String artist, int releaseYear, double dailyRentFee) {

        super(title, dailyRentFee, ProductType.ALBUM);

        this.artist = artist;
        this.releaseYear = releaseYear;

    }

    public String getArtist() {
        return artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return getId() + " : '" + getTitle() + "' by " + getArtist() + ". Price: " + getDailyRentFee() +
                " SEK/day. - " + printAvailable();
    }
}

