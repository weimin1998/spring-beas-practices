package com.example.demo;

import com.example.demo.web.TestDecryptRequestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(TestDecryptRequestController.class)
public class TestDecryptRequest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_encrypt1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("XzkqekFfyfBkVd/wvTSJ5zsEFUIBe/U9fCiGaclMYwE="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void test_encrypt2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("XzkqekFfyfBkVd/wvTSJ5zsEFUIBe/U9fCiGaclMYwE="))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"name\":\"tom\",\"age\":20}"));

    }

    @Test
    public void test_encrypt3() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("XzkqekFfyfBkVd/wvTSJ5zsEFUIBe/U9fCiGaclMYwE="))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"name\":\"tom\",\"age\":20}"));

    }
}
