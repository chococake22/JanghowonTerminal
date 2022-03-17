package janghowon.terminal.api;

import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.dto.CommentDto;
import janghowon.terminal.service.BoardService;
import janghowon.terminal.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService boardService;

    // 게시글별 댓글 조회
    @GetMapping("/comment/list/{id}")
    public List<CommentDto> commentList(@PathVariable("id") Long id) {
        BoardDto boardDto = boardService.getBoard(id);
        return commentService.getComments(boardDto.getId());
    }

}
