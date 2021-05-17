package russel.umas.alpha.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import russel.umas.alpha.UmasAlphaApplication;

import java.util.List;
import java.util.Objects;

@SpringBootTest(classes = UmasAlphaApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    public void testGetAllUsers()
    {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<List> response = restTemplate.exchange(
                createURLWithPort("/api/users"), HttpMethod.GET, entity, List.class);

        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).size(), 2);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
