package com.openclassrooms.webapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data // Génère automatiquement getter/setter pour les attributs de classe
@Configuration // Permet de déclarer la classe en tant que bean de configuration
@ConfigurationProperties(prefix = "com.openclassrooms.webapp") // Demande à Spring de charger les properties qui commencent par “com.openclassrooms.webapp” au sein des attributs de la classe.
public class CustomProperties {

    private String apiUrl;
}
