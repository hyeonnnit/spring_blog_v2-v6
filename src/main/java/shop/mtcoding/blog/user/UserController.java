package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.SaveDTO reqDTO){
        userService.joinService(reqDTO);
        return "redirect:/login-form";
    }
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userService.loginService(reqDTO);
        session.setAttribute("sessionUser",sessionUser);
        return "redirect:/";
    }
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.updateService(sessionUser.getId(),reqDTO);
        session.setAttribute("sessionUser",newSessionUser);
        return "redirect:/";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.updateFormService(sessionUser.getId());
        request.setAttribute("user",user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
