package fr.lille.alom.aller_microservice;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class AppConfig {

    private static final Client client = ClientBuilder.newClient();

    // Méthode pour obtenir un client HTTP Jersey
    public static Client getClient() {
        return client;
    }
}
