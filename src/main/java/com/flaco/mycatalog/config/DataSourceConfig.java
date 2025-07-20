package com.flaco.mycatalog.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("production") // Questa configurazione è attiva SOLO per il profilo 'production'
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        // Render fornisce i dettagli di connessione in una singola variabile DATABASE_URL.
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null) {
            throw new IllegalStateException("La variabile d'ambiente DATABASE_URL non è impostata!");
        }

        try {
            URI dbUri = new URI(databaseUrl);

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];

            // Costruiamo l'URL JDBC corretto dal formato di Render.
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .driverClassName("org.postgresql.Driver")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("URL del database non valido: " + databaseUrl, e);
        }
    }
}