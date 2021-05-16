package russel.umas.alpha.services;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> validateUser(String email, String password);
}
