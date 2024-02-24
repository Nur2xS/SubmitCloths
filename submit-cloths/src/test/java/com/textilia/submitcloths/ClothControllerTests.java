package com.textilia.submitcloths;

import com.textilia.submitcloths.controllers.ClothController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import com.textilia.submitcloths.entities.Cloth;
import com.textilia.submitcloths.repositories.ClothRepository;

@WebMvcTest(ClothController.class)
public class ClothControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClothRepository clothRepository;

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
        Long clothId = 1L;

        mockMvc.perform(delete("/cloths/{id}", clothId))
                .andExpect(status().isOk());
    }
}
