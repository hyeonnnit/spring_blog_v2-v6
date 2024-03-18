package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service // Ioc 등록
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void joinService(UserRequest.SaveDTO reqDTO) {
        // 1. 유효성 검사 (컨트롤러 책임)

        // 2. username 중복 검사 (서비스 체크) - DB연결 필요
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()){
//            throw new Exception400("중복된 username");
        }
        userJPARepository.save(reqDTO.toEntity());

    }
}
