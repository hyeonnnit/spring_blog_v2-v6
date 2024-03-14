package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class) // IoC 등록코드
@DataJpaTest // Datasource(connection pool), EntityManager
public class UserRepositoryTest {
    @Autowired // DI
    private UserRepository userRepository;
    @Test
        public void findByUsername_test(){
            // given
        String username = "ssar";
//        String password = "1234";
            // when
        User user = userRepository.findByUsername(username);
//        if (user == null){
//            System.out.println("login_test: 아이디 혹은 비밀번호가 틀렸습니다.");
//        }else {
//            if (user.getPassword().equals(password)){
//                System.out.println("login_test: 로그인 되었습니다.");
//            }else {
//                System.out.println("login_test: 비밀번호가 틀렸습니다.");
//            }
//        }
            // then
//        Assertions.assertThat()
        }

}
