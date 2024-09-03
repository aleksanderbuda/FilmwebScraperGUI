package imdbscraper.constants;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    HORROR("Horror"),
    SCI_FI("Sci-Fi");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }
}