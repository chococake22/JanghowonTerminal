package janghowon.terminal.dto;

import janghowon.terminal.domain.Board;
import janghowon.terminal.domain.Comment;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String writer;
    private String content;
    private Board board;

    public Comment toEntity() {
        Comment comment = Comment.builder()
                .id(id)
                .writer(writer)
                .content(content)
                .board(board)
                .build();
        return comment;
    }

    @Builder
    public CommentDto(Long id, String writer, String content, Board board) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.board = board;
    }


}
