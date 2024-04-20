package com.example.demo;

import com.example.demo.web.TestEncryptResponseController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(TestEncryptResponseController.class)
public class TestEncryptResponse {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_encrypt1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/encrypt1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"name\":\"tom\",\"age\":20}"));

    }

    @Test
    public void test_encrypt2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/encrypt2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":200,\"msg\":\"ok\",\"obj\":{\"name\":\"tom\",\"age\":20}}"));

    }

    @Test
    public void test_encrypt3() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/encrypt3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":200,\"msg\":\"TU9vsZNE8/UP8dgrQCMFIQ==\",\"obj\":\"XzkqekFfyfBkVd/wvTSJ5zsEFUIBe/U9fCiGaclMYwE=\"}"));

    }
}
