package janghowon.terminal.domain;

import janghowon.terminal.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    // board -> comment 단방향 (게시물에 있는 댓글들 확인, board_id를 통해서 comment를 받아온다.)
    // 이 댓글이 어느 게시물에 있는지를 파악한다. (게시글 기준이 아니라 댓글 기준)
    // 연관관계 주인은 다쪽이다(하나의 게시글에 여러 개의 댓글)
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public CommentDto toEntity() {
        CommentDto commentDto = CommentDto.builder()
                .id(id)
                .writer(writer)
                .content(content)
                .board(board)
                .build();
        return commentDto;
    }

    @Builder
    public Comment(Long id, String writer, String content, Board board) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.board = board;
    }
}
