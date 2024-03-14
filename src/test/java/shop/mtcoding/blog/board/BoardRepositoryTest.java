package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void randomquery_test(){
        int[] ids = {1,2};
        String q = "select u from User u where u.id in (";
        for (int i = 0; i < ids.length; i++) {
            if (i==ids.length-1){
                q=q+"?)";
            }else {
                q=q+"?,";
            }

        }
        System.out.println(q);
    }
    @Test
    public void findAll_custom_inquery_test(){
        List<Board> boradList = boardRepository.findAll();
        int[] userIds = boradList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i:userIds){
            System.out.println(i);
        }

    }

    @Test
    public void findByIdJoinUser_test() {
        int id = 1;
        boardRepository.findByIdJoinUser(id);
    }

    @Test
    public void findById_test() {
        int id = 1;
        System.out.println("start - 1");
        Board board = boardRepository.findById(id);
        System.out.println("start - 2");
        System.out.println(board.getUser().getId());
        System.out.println("start - 3");
        System.out.println(board.getUser().getUsername());
    }
}
