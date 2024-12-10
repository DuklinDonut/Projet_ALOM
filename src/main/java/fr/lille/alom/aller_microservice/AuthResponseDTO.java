package fr.lille.alom.aller_microservice;

public class AuthResponseDTO {
    private String username;
    private String hashedPassword;
    private String token;
    private String password; // Mot de passe en clair, ajouté pour la réponse

    // Constructeurs
    public AuthResponseDTO() { }

    public AuthResponseDTO(String username, String hashedPassword, String token, String password) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.token = token;
        this.password = password;
    }

    // Getters et Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
