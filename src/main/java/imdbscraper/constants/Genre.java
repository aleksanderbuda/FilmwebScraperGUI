package imdbscraper.constants;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    MUSICAL("Musical"),
    THRILLER("Thriller"),
    COMEDY("Comedy"),
    HORROR("Horror"),
    SCI_FI("Sci-Fi");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }
}