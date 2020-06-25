package app.config;

import app.model.AnimalsCage;
import app.model.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "app")
public class AppConfig {

    public AnimalsCage getAnimalCage() {
        AnimalsCage animalsCage = new AnimalsCage();
        animalsCage.whatAnimalSay();
        return animalsCage;
    }

}
