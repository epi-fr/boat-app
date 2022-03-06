package ch.epinon.boatappback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
public class UserController {

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return user.getUserName().equals("user") && user.getPassword().equals("password");
    }

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
