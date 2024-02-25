package com.textilia.submitcloths;

import com.textilia.submitcloths.controllers.ClothController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import com.textilia.submitcloths.entities.Cloth;
import com.textilia.submitcloths.repositories.ClothRepository;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

@WebMvcTest(ClothController.class)
public class ClothControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClothRepository clothRepository;

    private Cloth mockCloth;

    @BeforeEach
    void setUp() {
        mockCloth = new Cloth();
        mockCloth.setId(1L);
        mockCloth.setName("T-Shirt");
        mockCloth.setSize("M");
        mockCloth.setColor("Red");

        given(clothRepository.findById(1L)).willReturn(Optional.of(mockCloth));
    }

    @Test
    public void shouldCreateCloth() throws Exception {
        Cloth newCloth = new Cloth();
        newCloth.setName("T-Shirt");
        newCloth.setSize("M");
        newCloth.setColor("Red");

        given(clothRepository.save(newCloth)).willReturn(newCloth);

        mockMvc.perform(post("/cloths")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"T-Shirt\",\"size\":\"M\",\"color\":\"Red\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCloth() throws Exception {
        Long clothId = 1L; // Assuming this ID is for the cloth to be deleted

        given(clothRepository.findById(clothId)).willReturn(Optional.of(mockCloth));

        // Mocking deleteById to simulate the deletion operation
        Mockito.doNothing().when(clothRepository).deleteById(clothId);

        mockMvc.perform(delete("/cloths/{id}", clothId))
                .andExpect(status().isOk());

        // Optionally verify that the deleteById method was called with the correct ID
        Mockito.verify(clothRepository, Mockito.times(1)).deleteById(clothId);
    }

    @Test
    public void shouldUpdateCloth() throws Exception {
        Long clothId = 1L;
        String updatedJson = "{\"name\":\"Updated T-Shirt\",\"size\":\"L\",\"color\":\"Blue\"}";

        Cloth updatedCloth = new Cloth();
        updatedCloth.setId(clothId);
        updatedCloth.setName("Updated T-Shirt");
        updatedCloth.setSize("L");
        updatedCloth.setColor("Blue");

        given(clothRepository.findById(clothId)).willReturn(Optional.of(mockCloth)); // Initial state
        given(clothRepository.save(any(Cloth.class))).willReturn(updatedCloth); // Reflects the update

        mockMvc.perform(put("/cloths/{id}", clothId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated T-Shirt"))
                .andExpect(jsonPath("$.size").value("L"))
                .andExpect(jsonPath("$.color").value("Blue"));
    }
}