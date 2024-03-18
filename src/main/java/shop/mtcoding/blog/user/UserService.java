package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service // Ioc 등록
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public User updateService(int id, UserRequest.UpdateDTO reqDTO){
        User user = userJPARepository.findById(id)
                .orElseThrow(
                        ()-> new Exception404("회원정보를 찾을 수 없습니다.")
                );
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    } // 더티체킹

    public User updateFormService(int id){
        User user = userJPARepository.findById(id)
                .orElseThrow(
                        ()-> new Exception404("회원정보를 찾을 수 없습니다.")
                );
        return user;
    }
    public User loginService(UserRequest.LoginDTO reqDTO){
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(
                        ()-> new Exception401("인증되지 않았습니다.")
                );
        return sessionUser;
    }
    @Transactional
    public void joinService(UserRequest.SaveDTO reqDTO) {
        // 1. 유효성 검사 (컨트롤러 책임)

        // 2. username 중복 검사 (서비스 체크) - DB연결 필요
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()){
            throw new Exception400("중복된 username");
        }
        userJPARepository.save(reqDTO.toEntity());

    }
}
