import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> userList;

    public UserManager() {
        this.userList = new ArrayList<>();
    }

    public boolean registerUser(String name, String email, String username, String password) {
        // Check if the user is already registered
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                System.out.println("User with the same email already exists. Registration failed.");
                return false;
            }
        }

        // If the user is new
        userList.add(new User(name, email, username, password));
        System.out.println("User registered successfully.");
        return true;
    }
}
