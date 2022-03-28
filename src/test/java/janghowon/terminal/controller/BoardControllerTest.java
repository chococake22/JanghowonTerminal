package janghowon.terminal.controller;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.Board;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.repository.BoardRepository;
import janghowon.terminal.role.Role;
import janghowon.terminal.service.BoardService;
import org.assertj.core.api.Assertions;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardControllerTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardController boardController;

    @Autowired
    AccountRepository accountRepository;

    @AfterEach
    void 테스트하고() {
        boardRepository.deleteAll();
    }

    @Test
    void 글작성() {
        // given
        Account a = new Account(1L, "haha", "1234", "haha@naver.com", "01012341234", Role.USER);
        Board b = new Board(1L, a, "title1", "content1", new ArrayList<>());
        BoardDto boardDto = BoardDto.builder()
                .id(b.getId())
                .account(b.getAccount())
                .title(b.getTitle())
                .content(b.getContent())
                .comments(b.getComments())
                .build();

        accountRepository.save(a);

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

    @Test
    void 글수정() {

        // given
        Account a = new Account(1L, "haha", "1234", "haha@naver.com", "01012341234", Role.USER);
        Board b = new Board(2L, a, "title2", "content1", new ArrayList<>());
        BoardDto boardDto = BoardDto.builder()
                .id(b.getId())
                .account(b.getAccount())
                .title(b.getTitle())
                .content(b.getContent())
                .comments(b.getComments())
                .build();

        boardRepository.save(boardDto.toEntity()).getId();

        // given
        BoardDto boardDto1 = boardService.getBoard(b.getId());
        boardDto1.setTitle("수정함");
        boardRepository.save(boardDto1.toEntity()).getId();

        // then
        BoardDto boardDto2 = boardService.getBoard(b.getId());
        System.out.println("boardDto1 : " + boardDto1.getTitle());
        System.out.println("boardDto2 : " + boardDto2.getTitle());
        Assertions.assertThat(boardDto2.getTitle()).isEqualTo(boardDto1.getTitle());

    }

    @Test
    void 글삭제() {

        // given
        Account account = new Account(1L, "haha", "1234", "haha@naver.com", "01012341234", Role.USER);
        Board b = new Board(54L, account, "title2", "content1", new ArrayList<>());
        BoardDto boardDto = BoardDto.builder()
                .id(b.getId())
                .account(b.getAccount())
                .title(b.getTitle())
                .content(b.getContent())
                .comments(b.getComments())
                .build();

        boardRepository.save(boardDto.toEntity()).getId();

        // when
        boardRepository.deleteById(57L);

        // then
        Assertions.assertThat(boardRepository.findById(b.getId()).isPresent()).isEqualTo(false);
    }
}
