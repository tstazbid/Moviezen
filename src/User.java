import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String username;
    private String password;
    private List<Movie> favorites;

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.favorites = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Movie> getFavorites() {
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        return favorites;
    }

    public void removeFromFavorites(Movie movie) {
        favorites.remove(movie);
    }

    public void addToFavorites(Movie movie) {
        favorites.add(movie);
    }

    public int getTotalFavoriteMovies() {
        return favorites.size();
    }
}
