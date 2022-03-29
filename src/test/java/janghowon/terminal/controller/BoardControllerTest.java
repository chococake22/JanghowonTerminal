package janghowon.terminal.controller;

import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.Board;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.repository.BoardRepository;
import janghowon.terminal.role.Role;
import janghowon.terminal.service.AccountService;
import janghowon.terminal.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest // Spring Boot에서 테스트 관리
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   // 테스트 순서를 Order를 통해 지정
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 모든 테스트 메서드를 동일한 인스턴스 환경에서 동작시키기 위해 사용한다.
public class BoardControllerTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardController boardController;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    static Account account;
    static Board board;

    @BeforeAll
    void 테스트이전() {

        System.out.println("테스트 이전");

        String pwd = "1234";
        pwd =  bCryptPasswordEncoder.encode(pwd);

        AccountDto accountDto = new AccountDto(1L, pwd, "1234", "apple@apple.com", "01012341234", Role.USER);
        accountService.save(accountDto);
        board = new Board(1L, account, "title1", "content1", new ArrayList<>());

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .account(board.getAccount())
                .title(board.getTitle())
                .content(board.getContent())
                .comments(board.getComments())
                .build();

        boardRepository.save(boardDto.toEntity()).getId();
    }

    @AfterAll
    void 테스트하고() {
        System.out.println("테스트 이후");
        boardRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("글 작성하자")
    void 글작성() {
        // given


        // when
        Optional<Board> b1 = boardRepository.findById(board.getId());
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
    @Order(2)
    @DisplayName("글 수정하자")
    void 글수정() {


        // given
        BoardDto boardDto1 = boardService.getBoard(board.getId());
        boardDto1.setTitle("수정함");
        boardRepository.save(boardDto1.toEntity()).getId();

        // then
        BoardDto boardDto2 = boardService.getBoard(board.getId());
        Assertions.assertThat(boardDto2.getTitle()).isEqualTo(boardDto1.getTitle());

    }

    @Test
    @Order(3)
    @DisplayName("글 삭제하자")
    void 글삭제() {

        // given


        // when
        boardRepository.deleteById(board.getId());

        // then
        Assertions.assertThat(boardRepository.findById(board.getId()).isPresent()).isEqualTo(false);
    }
}
