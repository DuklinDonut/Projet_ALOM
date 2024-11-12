package fr.lille.alom.aller_microservice;

public class AuthResponseDTO {
    private String username;
    private String hashedPassword;
    private String token;

    // Constructeurs
    public AuthResponseDTO() { }

    public AuthResponseDTO(String username, String hashedPassword, String token) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.token = token;
    }

    // Getters et Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
