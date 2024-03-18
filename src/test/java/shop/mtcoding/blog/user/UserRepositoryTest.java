package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
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
    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test(){
        // given
        int id = 1;
        String password="123456";
        String email = "ssar12@naver.com";
        // when
        UserRequest.UpdateDTO request = new UserRequest.UpdateDTO();
        userRepository.update(id, request);
        // then
        em.flush();
        System.out.println("updateById_test : " + id);
        System.out.println("updateById_test : " + password);
        System.out.println("updateById_test : " + email);
    }

    @Test
    public void findById_test(){
        int id = 1;
        userRepository.findById(id);
        System.out.println("findById_test: "+id);
    }

    @Test
    public void save_test(){
        User user = new User();
        userRepository.save(user);
    }
    @Test
        public void findByUsername_test(){
            // given
        String username = "ssar";
        String password = "1234";
//             when
//        User user = userRepository.findByUsernameAndPassword();
//        if (user == null){
//            System.out.println("login_test: 아이디 혹은 비밀번호가 틀렸습니다.");
//        }else {
//            if (user.getPassword().equals(password)){
//                System.out.println("login_test: 로그인 되었습니다.");
//            }else {
//                System.out.println("login_test: 비밀번호가 틀렸습니다.");
//            }
//        }
//             then
//        Assertions.assertThat().
        }

}
