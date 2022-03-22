package janghowon.terminal.controller;

import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.Board;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.BoardRepository;
import janghowon.terminal.role.Role;
import janghowon.terminal.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

//    @Test
//    void 글작성() {
//        // given
//
//        boardService = new BoardService(boardRepository);
//        Board b = new Board(1L, "apple", "title", "이거는 내용입니다.");
//
//        BoardDto boardDto = BoardDto.builder()
//                .id(b.getId())
//                .writer(b.getWriter())
//                .title(b.getTitle())
//                .content(b.getContent())
//                .createdDate(b.getCreatedDate())
//                .modifiedDate(b.getModifiedDate())
//                .build();
//
//        // when
//        boardService.save(boardDto);
//
//        // then
//        BoardDto boardDto1 = boardService.getBoard(boardDto.getId());
//        assertThat(b.getWriter()).isEqualTo(boardDto1.getWriter());
//    }
//
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
