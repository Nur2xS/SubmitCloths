package com.textilia.submitcloths.repositories;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.textilia.submitcloths.entities.Cloth;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

//    @Bean
//    CommandLineRunner initDatabase(ClothRepository repository) {
//        return args -> {
//            Cloth cloth = new Cloth();
//            cloth.setName("T-Shirt");
//            cloth.setSize("M");
//            cloth.setColor("Red");
//            cloth.setCreatedTimestamp(LocalDate.now());
//            cloth.setUpdatedTimestamp(LocalDate.now());
//            repository.save(cloth);
//        };
//    }
}
