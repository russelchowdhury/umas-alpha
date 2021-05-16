package russel.umas.alpha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import russel.umas.alpha.models.User;
import russel.umas.alpha.payload.request.LoginRequest;
import russel.umas.alpha.payload.request.SignupRequest;
import russel.umas.alpha.payload.response.MessageResponse;
import russel.umas.alpha.repository.UserRepository;
import russel.umas.alpha.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    UserRepository userRepository;
    PasswordEncoder encoder;
    UserService userService;

    @Autowired
    public AuthController(UserRepository userRepository,
                          PasswordEncoder encoder,
                          UserService userService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Apologies: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Apologies: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Great! User registered successfully!"));
    }
}
