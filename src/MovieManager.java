import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MovieManager {
    private List<Movie> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
        initializeDefaultMovies();
    }

    private void initializeDefaultMovies() {
        movies.add(new Movie("Premalu", Arrays.asList("Naslen", "Mamitha Baiju", "Shyam Mohan"), "Romance", "February 9, 2024", 10));
        movies.add(new Movie("Laapataa Ladies", Arrays.asList("Nitanshi Goel", "Sparsh Srivastav", "Savita Malviya"), "Comedy", "September 8, 2023", 12));
        movies.add(new Movie("Article 370", Arrays.asList("Yami Gautam", "Mohan Agashe" , "Sukhita Aiyar"), "Action", "February 23, 2024", 24));
        movies.add(new Movie("The Idea of You", Arrays.asList("Anne Hathaway", "Nicholas Galitzine", "Ella Rubin"), "Romance", "March 16, 2024", 25));
        movies.add(new Movie("PK", Arrays.asList("Aamir Khan", "Anushka Sharma", "Sanjay Dutt"), "Sci-fi", "December 19, 2014", 101));
    }

    // search movies by title / cast / category
    public List<Movie> searchMovie(int searchChoice, String searchTerm) {
        System.out.println("\nSearching ...");
        List<Movie> searchResults = new ArrayList<>();
        for (Movie movie : movies) {
            boolean match = false;
            switch (searchChoice) {
                case 1: // title
                    if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                        match = true;
                    }
                    break;
                case 2: // cast
                    for (String castMember : movie.getCast()) {
                        if (castMember.toLowerCase().contains(searchTerm.toLowerCase())) {
                            match = true;
                            break;
                        }
                    }
                    break;
                case 3: // category
                    if (movie.getCategory().toLowerCase().contains(searchTerm.toLowerCase())) {
                        match = true;
                    }
                    break;
                default:
                    System.out.println("Invalid search choice.");
                    return new ArrayList<>();
            }
            if (match) {
                searchResults.add(movie);
            }
        }
        // Sort search results by movie title
        searchResults.sort((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()));
        return searchResults;
    }
}
