package hu.nye.rft.neptun;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NeptunApplicationApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testApiEndpoint() {
        String apiUrl = "http://localhost:8080/";

        String result = restTemplate.getForObject(apiUrl, String.class);

        assertEquals("ExpectedResult", result);
    }
}