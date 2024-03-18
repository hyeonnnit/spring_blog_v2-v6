package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final HttpSession session;

    // @Transactional 트랜잭션 시간이 너무 길어져서 service에 넣어야함
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.updateService(id, sessionUser.getId(),reqDTO);
        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.findBoardService(id);
        request.setAttribute("board", board );
        return "/board/update-form"; // 서버가 내부적으로 index를 요청 - 외부에서는 다이렉트 접근이 안됨
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) { // DTO 없이 구현
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.deleteService(id, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardService.findBoardListService();
        request.setAttribute("boardList", boardList);
        return "index"; // 서버가 내부적으로 index를 요청 - 외부에서는 다이렉트 접근이 안됨
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) { // DTO 없이 구현
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.saveService(reqDTO, sessionUser);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


//    @GetMapping("/board/{id}")
//    public @ResponseBody BoardResponse.DetailDTO detail(@PathVariable Integer id, HttpServletRequest request) { // Integer : 없으면 null, int : 0
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        return boardService.detailBoardService(id, sessionUser);
//
////        Board board = boardRepository.findByIdJoinUser(id);
////        request.setAttribute("isOwner", isOwner);
////        request.setAttribute("board", board);
////        return "board/detail";
//    }
    // SSR은 DTO를 굳이 만들필요가 없다. 필요한 데이터만 렌더링해서 클라이언트에게 전달할 것이기 때문
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) { // Integer : 없으면 null, int : 0
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.detailBoardService(id, sessionUser);
      //        request.setAttribute("isOwner", isOwner);
        request.setAttribute("board", board);
        return "board/detail";
    }
}
