package com.textilia.submitcloths.controllers;

import com.textilia.submitcloths.repositories.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClothController {

    private final ClothRepository clothRepository;

    @Autowired
    public ClothController(ClothRepository clothRepository) {
        this.clothRepository = clothRepository;
    }

}
