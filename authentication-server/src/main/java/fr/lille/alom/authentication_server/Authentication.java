package fr.lille.alom.authentication_server;


import org.springframework.stereotype.Service;

@Service
public class Authentication {

    // Method that handles authentication and returns a message
    public String authenticate(String username, String password) {
        // Replace this with real authentication logic (e.g., check from database)
        if ("admin".equals(username) && "password".equals(password)) {
            return "Login successful";  // Login success message
        } else {
            return "Invalid credentials"; // Login failed message
        }
    }
}
