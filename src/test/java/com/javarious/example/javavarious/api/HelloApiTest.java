package com.javarious.example.javavarious.api;

import java.net.URL;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @WebMvcTest(HelloController.class) // DataSource が必要なので、@WebMvcTest は使えない
@SpringBootTest
@AutoConfigureMockMvc
public class HelloApiTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private DataSource dataSource;

        @Test
        public void testHello() throws Exception {

                IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
                URL givenUrl = this.getClass().getResource("/hello/hello/default/given/");
                databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
                databaseTester.onSetup();

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

                var actualDataSet = databaseTester.getConnection().createDataSet();
                var actualTestTable = actualDataSet.getTable("test");
                var expectedUrl = this.getClass().getResource("/hello/hello/default/expected/");
                var expectedDataSet = new CsvURLDataSet(expectedUrl);
                var expectedTestTable = expectedDataSet.getTable("test");
                Assertion.assertEquals(expectedTestTable, actualTestTable);
        }
}
