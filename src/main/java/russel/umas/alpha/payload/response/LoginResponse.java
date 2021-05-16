package russel.umas.alpha.payload.response;

import java.util.Date;

public class LoginResponse {

    private Long id;
    private String username;
    private String email;
    private Date lastLogin;

    // Other attributes will be added here later like token, roles etc

    public LoginResponse( Long id, String username, String email,
                         Date lastLogin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.lastLogin = lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
