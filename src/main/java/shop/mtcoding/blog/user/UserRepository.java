package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;
    @Transactional
    public void save(User user){
        em.persist(user);
    }
    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password",User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }
}
