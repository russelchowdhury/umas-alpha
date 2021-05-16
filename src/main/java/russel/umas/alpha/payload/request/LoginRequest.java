package russel.umas.alpha.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

    @Email(message = "Please enter a valid e-mail address")
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
