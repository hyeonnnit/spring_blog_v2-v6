package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.reply.ReplyJPARepository;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private ReplyJPARepository replyJPARepository;
    @Autowired
    private EntityManager em;
//    @Test
//        public void findByBoardId_test(){
//            // given
//        int boardId = 4;
//        Board board = boardJPARepository.findByIdJoinUser(boardId)
//                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다."));
//            // when
//        List<Reply> replyList = replyJPARepository.findByBoardId(boardId);
//            // then
//        System.out.println("findByBoardId_test: " + replyList.size());
//        }
    @Test
        public void findByIdJoinUserAndReplies_test(){
        //given
        int id = 4;

        //when
        //옵셔널이라 오류가 나니까 .get 붙여줌. get는 있으니까 가져와! 라는 말
        Board board = boardJPARepository.findByIdJoinUserAndReplies(id).get();

        //then
        }
    // save
    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();
        // when
        boardJPARepository.save(board);
        // then
        System.out.println("save_test: id: " + board.getId());
    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 5;
        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()) {          // 존재하면
            Board board = boardOP.get();    // 꺼내기
            System.out.println("findById_test : " + board.getTitle());
        }                                   // 없으면 throw하기
        // then

    }

    //findByIdJoinUser
    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;
        // when
        Board board = boardJPARepository.findByIdJoinUser(id);
        // then
        System.out.println("findByIdJoinUser_test: "+board.getTitle());
        System.out.println("findByIdJoinUser_test: "+board.getUser().getUsername());
    }

    // findAll (sort)

   @Test
       public void findAll_test(){
           // given
       Sort sort = Sort.by(Sort.Direction.DESC, "id");
           // when
       List<Board> boardList = boardJPARepository.findAll(sort);
           // then
       System.out.println("findAll_test: "+boardList);
       }

    // deleteById
    @Test
        public void deleteById_test(){
            // given
        int id = 1;
            // when
        boardJPARepository.deleteById(id);
        em.flush();
            // then
        }

}
