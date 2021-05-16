package russel.umas.alpha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import russel.umas.alpha.models.User;
import russel.umas.alpha.payload.response.LoginResponse;
import russel.umas.alpha.payload.response.MessageResponse;
import russel.umas.alpha.repository.UserRepository;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> validateUser(String email, String password) {
        User user;
        try {
            if (!userRepository.existsByEmail(email)) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Invalid email/password!"));
            }else {
                user = userRepository.findByEmail(email);
                if (!BCrypt.checkpw(password, user.getPassword())) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Invalid password!"));
                } else {
                    user.setLastLogin(new Date());
                    userRepository.save(user);
                    return ResponseEntity.ok(new LoginResponse(
                            user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getLastLogin()));
                }
            }
        }catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ex.getMessage()));
        }
    }
}
