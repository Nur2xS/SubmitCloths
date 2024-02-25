package com.textilia.submitcloths;

import com.textilia.submitcloths.entities.Cloth;
import com.textilia.submitcloths.repositories.ClothRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SubmitClothsApplication.class)
public class ClothServiceIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ClothRepository clothRepository;

    private MockMvc mockMvc;

    private Cloth mockCloth;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.clothRepository.deleteAll(); // Ensure a clean state

        Cloth cloth = new Cloth();
        cloth.setName("Initial Cloth");
        cloth.setSize("M");
        cloth.setColor("Black");
        this.mockCloth = this.clothRepository.save(cloth);
    }

    @Test
     public void whenPostRequestToClothsAndValidCloth_thenCorrectResponse() throws Exception {
        String cloth = "{\"name\": \"Jeans\", \"size\": \"L\", \"color\": \"Blue\"}";
        mockMvc.perform(post("/cloths")
                        .content(cloth)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Jeans")))
                .andExpect(jsonPath("$.size", is("L")))
                .andExpect(jsonPath("$.color", is("Blue")));
    }


    @Test
    public void whenGetClothById_thenReturnsCloth() throws Exception {
        mockMvc.perform(get("/cloths/{id}", mockCloth.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(mockCloth.getId().intValue())))
                .andExpect(jsonPath("$.name", is(mockCloth.getName())))
                .andExpect(jsonPath("$.size", is(mockCloth.getSize())))
                .andExpect(jsonPath("$.color", is(mockCloth.getColor())));
    }

    @Test
    public void whenUpdateCloth_thenClothIsUpdated() throws Exception {
        Cloth updatedCloth = new Cloth();
        updatedCloth.setId(mockCloth.getId());
        updatedCloth.setName("Jeans");
        updatedCloth.setSize("L");
        updatedCloth.setColor("Blue");

        mockMvc.perform(put("/cloths/{id}", mockCloth.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Jeans\", \"size\": \"L\", \"color\": \"Blue\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(updatedCloth.getName())))
                .andExpect(jsonPath("$.size", is(updatedCloth.getSize())))
                .andExpect(jsonPath("$.color", is(updatedCloth.getColor())));
    }

    @Test
    public void whenDeleteCloth_thenClothIsDeleted() throws Exception {
        // Confirm presence
        mockMvc.perform(get("/cloths/{id}", mockCloth.getId()))
                .andExpect(status().isOk());

        // Perform deletion
        mockMvc.perform(delete("/cloths/{id}", mockCloth.getId()))
                .andExpect(status().isOk());

        // Verify deletion
        mockMvc.perform(get("/cloths/{id}", mockCloth.getId()))
                .andExpect(status().isNotFound());
    }
}
