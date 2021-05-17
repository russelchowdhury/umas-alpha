package russel.umas.alpha.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import russel.umas.alpha.models.User;
import russel.umas.alpha.payload.response.LoginResponse;
import russel.umas.alpha.repository.UserRepository;
import russel.umas.alpha.services.UserService;

@SpringBootTest
public class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository, PasswordEncoder encoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Test
    public void testValidateUser() {

        User user = new User("russel","russelchowdhury28@gmail.com",
                encoder.encode("abcd!1234"));
        userRepository.save(user);

        ResponseEntity<?> validatedResponse = userService.validateUser("russelchowdhury28@gmail.com","abcd!1234");
        LoginResponse validatedUser = (LoginResponse) validatedResponse.getBody();

        assert validatedUser != null;
        Assertions.assertEquals(user.getUsername(), validatedUser.getUsername());
    }
}
