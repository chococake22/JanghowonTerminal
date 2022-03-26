package janghowon.terminal.controller;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.Board;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.BoardRepository;
import janghowon.terminal.role.Role;
import janghowon.terminal.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardControllerTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardController boardController;

    @Test
    void 글작성() {
        // given
        Account a = new Account(1L, "haha", "1234", "haha@naver.com", "01012341234", Role.USER);
        Board b = new Board(20L, a, "title1", "content1", new ArrayList<>());
        BoardDto boardDto = BoardDto.builder()
                .id(b.getId())
                .account(b.getAccount())
                .title(b.getTitle())
                .content(b.getContent())
                .comments(b.getComments())
                .build();

        boardRepository.save(boardDto.toEntity()).getId();

        // when
        Optional<Board> b1 = boardRepository.findById(b.getId());
        System.out.println("있나 없나? " + b1.isPresent());
        Board b2 = b1.get();

        BoardDto boardDto4 = BoardDto.builder()
                .id(b2.getId())
                .account(b2.getAccount())
                .title(b2.getTitle())
                .content(b2.getContent())
                .comments(b2.getComments())
                .build();

        boardDto4.setTitle("이게 제목임");
        boardRepository.save(boardDto4.toEntity()).getId();


        // then
        Optional<Board> selected = boardRepository.findById(b2.getId());
        Assertions.assertThat(selected.get().getTitle()).isEqualTo(boardDto4.getTitle());
    }

//    @Test
//    void 글수정() {
//        // given
//        Board b = new Board(1L, "apple", "title", "이거는 내용입니다.");
//        BoardDto boardDto = BoardDto.builder()
//                .id(b.getId())
//                .writer(b.getWriter())
//                .title(b.getTitle())
//                .content(b.getContent())
//                .createdDate(b.getCreatedDate())
//                .modifiedDate(b.getModifiedDate())
//                .build();
//
//        boardService.save(boardDto);
//
//        // when
//        Optional<Board> board = boardRepository.findById(b.getId());
//        BoardDto boardDto1 = BoardDto.builder()
//                .id(board.get().getId())
//                .writer(board.get().getWriter())
//                .title(board.get().getTitle())
//                .content(board.get().getContent())
//                .createdDate(board.get().getCreatedDate())
//                .modifiedDate(board.get().getModifiedDate())
//                .build();
//        boardDto1.setWriter("banana");
//        boardController.update(boardDto1);
//
//        // then
//        BoardDto boardDto11 = boardService.getBoard(b.getId());
//        assertThat(boardDto11.getWriter()).isEqualTo("banana");
//
//
//    }
}
