package imdbscraper.model;

import lombok.Value;

@Value
public class Movie {
    String title;
    String userScore;
    String voteCount;
    String movieDescription;
    String releaseYear;
    String movieLength;
    String contentRating;
}