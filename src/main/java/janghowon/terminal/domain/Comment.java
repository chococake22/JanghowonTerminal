package janghowon.terminal.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    // Board와 연관관계 매핑(다대일)
    // board -> comment 단방향 (게시물에 있는 댓글들 확인)
    // 이 댓글이 어느 게시물에 있는지를 파악한다. (게시글 기준이 아니라 댓글 기준)
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


}
