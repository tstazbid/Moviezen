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
        movies.add(new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), "Sci-fi", "July 16, 2010", 160));
        movies.add(new Movie("The Shawshank Redemption", Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), "Drama", "October 14, 1994", 25));
        movies.add(new Movie("The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), "Action", "July 18, 2008", 185));
        movies.add(new Movie("Interstellar", Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), "Sci-fi", "November 5, 2014", 165));
        movies.add(new Movie("Forrest Gump", Arrays.asList("Tom Hanks", "Robin Wright", "Gary Sinise"), "Drama", "July 6, 1994", 55));
        movies.add(new Movie("The Godfather", Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), "Crime", "March 24, 1972", 6));
        movies.add(new Movie("Pulp Fiction", Arrays.asList("John Travolta", "Uma Thurman", "Samuel L. Jackson"), "Crime", "October 14, 1994", 8));
        movies.add(new Movie("The Matrix", Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"), "Sci-fi", "March 31, 1999", 63));
        movies.add(new Movie("Schindler's List", Arrays.asList("Liam Neeson", "Ben Kingsley", "Ralph Fiennes"), "Biography", "February 4, 1994", 22));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", Arrays.asList("Elijah Wood", "Ian McKellen", "Viggo Mortensen"), "Fantasy", "December 19, 2001", 93));
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
