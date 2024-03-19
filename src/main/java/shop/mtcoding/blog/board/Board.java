package shop.mtcoding.blog.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog._core.utils.MyDateUtil;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    // @JoinColumn(name = "user_id") 직접 지정할 때
    @ManyToOne(fetch = FetchType.LAZY) // ORM 할 것이기에 user 객체 필요
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // Entity 객체의 변수명 == fk의 주인
    private List<Reply> replies = new ArrayList<>();
    @Transient // 테이블 생성 안됨
    private boolean isOwner;

    public String getBoardDate(){
        return MyDateUtil.timestampFormat(createdAt);
    }
    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
