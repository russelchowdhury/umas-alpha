package russel.umas.alpha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import russel.umas.alpha.exception.ResourceNotFoundException;
import russel.umas.alpha.models.User;
import russel.umas.alpha.payload.response.MessageResponse;
import russel.umas.alpha.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserRepository userRepository;
    PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

            // You may decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody User user) throws ResourceNotFoundException {

        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Apologies! User not found with id: " + userId));

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Apologies: Username is already taken!"));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Apologies: Email is already in use!"));
        }

        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Apologies, we are unable to delete User with id : " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Cool! User deleted.", Boolean.TRUE);
        return response;
    }
}
