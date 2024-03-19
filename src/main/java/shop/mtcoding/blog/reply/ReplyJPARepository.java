package shop.mtcoding.blog.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.board.Board;

import java.util.Optional;

public interface ReplyJPARepository extends JpaRepository<Reply,Integer> {


}
