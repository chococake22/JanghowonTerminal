package janghowon.terminal.controller;


import janghowon.terminal.auth.UserAccount;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.service.BoardService;

@Controller
@AllArgsConstructor // 생성자 주입을 받음
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시물 목록 보기
    @GetMapping("/notice")
    public String list(Pageable pageable, Model model, @RequestParam(required = false, defaultValue = "") String search) {
        Page<BoardDto> boards = boardService.getBoards(pageable, search);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    // 게시물 작성
    @GetMapping("/write")
    public String write() {
        return "board/write";
    }

    // 게시물 작성 확인
    @PostMapping("/write")
    public String write(BoardDto boardDto, Model model, @AuthenticationPrincipal UserAccount userAccount) {
        boardService.save(boardDto);
        model.addAttribute("userAccount", userAccount);
        return "redirect:/notice";
    }

    // 상세 정보
    @GetMapping("/write/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }

    // 게시물 수정
    @GetMapping("/write/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "board/update";
    }

    // 게시물 수정 확인
    @PutMapping("/write/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.save(boardDto);
        return "redirect:/notice";
    }

    // 게시물 삭제
    @DeleteMapping("/write/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/notice";
    }

    // 게시물 검색
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model, Pageable pageable) {
        Page<BoardDto> boards = boardService.searchBoards(keyword, keyword, pageable);
        model.addAttribute("boards", boards);
        return "board/list";
    }

}
