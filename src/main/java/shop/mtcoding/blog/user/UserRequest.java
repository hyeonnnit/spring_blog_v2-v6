package shop.mtcoding.blog.user;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import shop.mtcoding.blog.board.Board;


public class UserRequest {
    @Data
    public static class SaveDTO{
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }
    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }
}
