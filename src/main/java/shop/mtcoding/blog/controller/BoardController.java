package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping( "/")
    public String index() {
        return "index";
    }

    @PostMapping("/board/save")
    public String save(String username, String title, String content){
        System.out.println("username : " + username);
        System.out.println("username : " + title);
        System.out.println("username : " + content);
        return "redirect:/";
    }
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {
        return "board/detail";
    }
}
