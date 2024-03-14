package shop.mtcoding.blog.user;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;


public class UserRequest {
    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }
}
