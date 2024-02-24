package com.textilia.submitcloths.controllers;

import com.textilia.submitcloths.entities.Cloth;
import com.textilia.submitcloths.repositories.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClothController {

    private final ClothRepository clothRepository;

    @Autowired
    public ClothController(ClothRepository clothRepository) {
        this.clothRepository = clothRepository;
    }
    @PostMapping("/cloths")
    public ResponseEntity<Cloth> createCloth(@RequestBody Cloth cloth) {
        Cloth savedCloth = clothRepository.save(cloth);
        return ResponseEntity.ok(savedCloth);
    }
    @DeleteMapping("/cloths/{id}")
    public ResponseEntity<Void> deleteCloth(@PathVariable Long id) {
        clothRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
