package com.textilia.submitcloths;

import com.textilia.submitcloths.entities.Cloth;
import com.textilia.submitcloths.repositories.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class SubmitClothsApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ClothRepository repository;

	@Test
	public void shouldSaveAndFindClothById() {
		Cloth cloth = new Cloth();
		cloth.setName("T-Shirt");
		cloth.setSize("M");
		cloth.setColor("Red");
		cloth = entityManager.persistAndFlush(cloth);

		Cloth foundCloth = repository.findById(cloth.getId()).orElse(null);
		assertThat(foundCloth).isNotNull();
		assertThat(foundCloth.getName()).isEqualTo(cloth.getName());
	}

	// Additional tests for update and delete operations
}
