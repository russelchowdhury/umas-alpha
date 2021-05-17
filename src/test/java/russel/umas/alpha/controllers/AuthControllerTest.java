package russel.umas.alpha.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import russel.umas.alpha.UmasAlphaApplication;
import russel.umas.alpha.models.User;

@SpringBootTest(classes = UmasAlphaApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterUser() {
        User user = new User("russelchowdhury", "russelchowdhury@gmail.com", "abcd!1234");
        ResponseEntity<User> responseEntity = this.restTemplate
                .postForEntity(createURLWithPort("auth/signup"), user, User.class);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
