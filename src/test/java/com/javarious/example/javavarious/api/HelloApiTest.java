package com.javarious.example.javavarious.api;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.javarious.example.javavarious.app.controller.HelloController;

@WebMvcTest(HelloController.class)
@AutoConfigureMockMvc
public class HelloApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/hello?name=test-user-name")
                        .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((result) -> JSONAssert.assertEquals("""
                        {
                          "message": "Hello, test-user-name"
                        }
                        """,
                        result.getResponse().getContentAsString(),
                        false));
    }
}
