package com.textilia.submitcloths.repositories;

import com.textilia.submitcloths.entities.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
