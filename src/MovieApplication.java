import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class MovieApplication {
    private Scanner scanner;
    private UserManager userManager;
    private MovieManager movieManager;

    public MovieApplication() {
        this.scanner = new Scanner(System.in);
        this.userManager = new UserManager();
        this.movieManager = new MovieManager();
    }

    public void run() {
        System.out.println("\tWelcome to Moviezen - A Movie Listing Application!");

        // Register a new user
        User newUser = registerUser();

        // Once registered, the user can perform other operations
        if (newUser != null) {
            System.out.println("\nWelcome, " + newUser.getUsername() + "! Registration Completed.");

            // user can see others option after successfully registration
            showOptions(newUser);
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private User registerUser() {
        System.out.println("\n==== User Registration ====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        String password;
        String reenterPassword;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            System.out.print("Re-enter your password: ");
            reenterPassword = scanner.nextLine();
            if (!password.equals(reenterPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!password.equals(reenterPassword));

        // Register the user
        boolean isRegistered = userManager.registerUser(name, email, username, password);

        // Return the registered user
        if (isRegistered) {
            return new User(name, email, username, password);
        } else {
            return null;
        }
    }

    private void showOptions(User user) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n==== Main Manu ====");
            System.out.println("1. Search Movies");
            System.out.println("2. View Personal Details");
            System.out.println("3. See Favorites List");
            System.out.println("4. Search from Favorites List");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchMovies(user, false);
                    break;
                case 2:
                    System.out.println("\nPersonal Details:");
                    System.out.println("Name: " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Username: " + user.getUsername());
                    break;
                case 3:
                    List<Movie> favorites = user.getFavorites();
                    if (favorites.isEmpty()) {
                        System.out.println("\nYour favorites list is empty.");
                    } else {
                        System.out.println("\nYour Favorites List:");
                        for (int i = 0; i < favorites.size(); i++) {
                            Movie movie = favorites.get(i);
                            System.out.println((i + 1) + ". " + movie.getTitle());
                            // System.out.println("   Cast: " + String.join(", ", movie.getCast()));
                            System.out.println("   Category: " + movie.getCategory());
                        }
                    }
                    break;
                case 4:
                    searchFavoritesMovies(user);
                    break;
                case 5:
                    System.out.println("Exiting from Moviezen. Thanks!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showMovieDetails(Movie movie) {
        System.out.println("\nFull Details for '" + movie.getTitle() + "':");
        System.out.println("\tTitle: " + movie.getTitle());
        System.out.println("\tCast: " + String.join(", ", movie.getCast()));
        System.out.println("\tRelease Date: " + movie.getReleaseDate());
        System.out.println("\tCategory: " + movie.getCategory());
        System.out.println("\tBudget: $" + movie.getBudget() + " million");
    }

    private void displaySearchResults(List<Movie> searchResults, User user) {
        if (searchResults.isEmpty()) {
            System.out.println("No movies found matching the search criteria.");
        } else {
            System.out.println("Search Results:");
            int movieNumber = 1;
            for (Movie movie : searchResults) {
                System.out.println(movieNumber + ". " + movie.getTitle());
                movieNumber++;
            }
            System.out.println("0. Back to main menu");

            System.out.print("Enter the movie number to select or 0 to go back: ");
            int selectedMovieNumber = scanner.nextInt();
            scanner.nextLine();

            if (selectedMovieNumber == 0) {
                return;
            }

            if (selectedMovieNumber < 1 || selectedMovieNumber > searchResults.size()) {
                System.out.println("Invalid movie number.");
                return;
            }

            Movie selectedMovie = searchResults.get(selectedMovieNumber - 1);
            System.out.println("\nSelected Movie: '" + selectedMovie.getTitle() + "'");
            System.out.println("\tCast: " + String.join(", ", selectedMovie.getCast()));
            System.out.println("\tRelease Date: " + selectedMovie.getReleaseDate());
            System.out.println("\tCategory: " + selectedMovie.getCategory());

            if (user.getFavorites().contains(selectedMovie)) {
                System.out.println("\t(In Favorites)");
                System.out.println("Options:");
                System.out.println("1. Remove from Favorites");
            } else {
                System.out.println("\t(Not in Favorites)");
                System.out.println("Options:");
                System.out.println("1. Add to Favorites");
            }
            System.out.println("2. View full details");
            System.out.println("3. Back to main menu");

            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (user.getFavorites().contains(selectedMovie)) {
                        user.removeFromFavorites(selectedMovie);
                        System.out.println(selectedMovie.getTitle() + " removed from favorites.");
                    } else {
                        user.addToFavorites(selectedMovie);
                        System.out.println(selectedMovie.getTitle() + " added to favorites.");
                    }
                    break;
                case 2:
                    showMovieDetails(selectedMovie);
                    break;
                case 3:
                    // Back to main menu
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void searchMovies(User user, boolean searchFromFavList) {
        System.out.println("Search movies by:");
        System.out.println("1. Title");
        System.out.println("2. Cast");
        System.out.println("3. Category");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        String searchTerm;
        switch (searchChoice) {
            case 1:
                System.out.print("Enter the movie title to search: ");
                searchTerm = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter the cast member to search: ");
                searchTerm = scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter the category to search: ");
                searchTerm = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        List<Movie> searchResults = movieManager.searchMovie(searchChoice, searchTerm);
        if (searchFromFavList) {
            searchResults = filterFavorites(searchResults, user);
        }
        displaySearchResults(searchResults, user);
    }

    private List<Movie> filterFavorites(List<Movie> searchResults, User user) {
        List<Movie> favorites = user.getFavorites();
        List<Movie> filteredResults = new ArrayList<>();
        for (Movie movie : searchResults) {
            if (favorites.contains(movie)) {
                filteredResults.add(movie);
            }
        }
        return filteredResults;
    }

    private void searchFavoritesMovies(User user) {
        if(user.getFavorites().isEmpty()) {
            System.out.println("Unable to search. Your favorites list is empty!");
            return;
        }
        System.out.println("\nSearching from favorite list ...");
        searchMovies(user, true);
    }
}
