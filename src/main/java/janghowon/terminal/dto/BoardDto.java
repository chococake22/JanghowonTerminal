package janghowon.terminal.dto;

import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.Comment;
import lombok.*;
import janghowon.terminal.domain.Board;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private Account account;
    private String title;
    private String content;
    private List<Comment> comments;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .account(account)
                .title(title)
                .content(content)
                .comments(comments)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, Account account, String title, String content, List<Comment> comments, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.account = account;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
