package com.textilia.submitcloths;

import com.textilia.submitcloths.SubmitClothsApplication;
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
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SubmitClothsApplication.class)
public class ClothServiceIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ClothRepository clothRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
}
