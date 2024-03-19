package shop.mtcoding.blog.reply;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.board.Board;

/**
 * 1. One관계는 조인하고, Many관계는 조회를 한번 더 하기 - DTO에 담기
 * 2. Many 관계를 양방향 매핑하기
 */
@DataJpaTest
public class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;

}
