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
}
