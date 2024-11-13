package info.makingsense.training.api.database;

import info.makingsense.training.api.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(DataService dataService) {
        return args -> {
            dataService.importData();
        };
    }
}

