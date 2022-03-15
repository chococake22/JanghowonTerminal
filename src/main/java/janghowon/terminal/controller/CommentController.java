package janghowon.terminal.controller;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.dto.CommentDto;
import janghowon.terminal.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor // 생성자 주입을 받음
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 작성 확인
    @PostMapping("/commentsave")
    public String commentSave(CommentDto commentDto, BoardDto boardDto, Model model, @AuthenticationPrincipal AccountDetails accountDetails) {

        commentService.save(commentDto, boardDto, accountDetails);
        model.addAttribute("userAccount", accountDetails);
        return "redirect:/notice";
    }
}
