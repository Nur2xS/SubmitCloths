package com.textilia.submitcloths.controllers;

import com.textilia.submitcloths.entities.Cloth;
import com.textilia.submitcloths.repositories.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ClothController {

    private final ClothRepository clothRepository;

    @Autowired
    public ClothController(ClothRepository clothRepository) {
        this.clothRepository = clothRepository;
    }
    @PostMapping("/cloths")
    public ResponseEntity<Cloth> createCloth(@RequestBody Cloth cloth) {
        System.out.println("Received cloth: " + cloth);
        Cloth savedCloth = clothRepository.save(cloth);
        return ResponseEntity.ok(savedCloth);
    }
    @DeleteMapping("/cloths/{id}")
    public ResponseEntity<Void> deleteCloth(@PathVariable Long id) throws Exception {
        Cloth cloth = clothRepository.findById(id)
                .orElseThrow(() -> new Exception("Cloth not found for this id :: " + id));
        clothRepository.deleteById(cloth.getId());
        return ResponseEntity.ok().build();
    }
    @PutMapping("/cloths/{id}")
    public ResponseEntity<Cloth> updateCloth(@PathVariable Long id, @RequestBody Cloth clothDetails) throws Exception {
        Cloth cloth = clothRepository.findById(id)
                .orElseThrow(() -> new Exception("Cloth not found for this id :: " + id));
        cloth.setName(clothDetails.getName());
        cloth.setSize(clothDetails.getSize());
        cloth.setColor(clothDetails.getColor());
        final Cloth updatedCloth = clothRepository.save(cloth);
        return ResponseEntity.ok(updatedCloth);
    }

    @GetMapping("/cloths/{id}")
    public ResponseEntity<Cloth> getClothById(@PathVariable Long id) {
        Cloth cloth = clothRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cloth not found with id " + id));
        return ResponseEntity.ok(cloth);
    }
    @GetMapping("/cloths")
    public ResponseEntity<List<Cloth>> getAllCloths() {
        List<Cloth> cloths = clothRepository.findAll();
        if(cloths.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cloths, HttpStatus.OK);
    }
}
