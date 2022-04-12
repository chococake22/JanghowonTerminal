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
import janghowon.terminal.service.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardController.class) // Spring Boot에서 테스트 관리
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   // 테스트 순서를 Order를 통해 지정
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 모든 테스트 메서드를 동일한 인스턴스 환경에서 동작시키기 위해 사용한다.
public class BoardControllerTest {

    // 테스트를 할 때 애플리케이션을 서버에 배포하지 않고
    // 테스트용 MVC 환경을 만들어 요청 및 전송, 응답기능을 제공하는 클래스
    @Autowired
    private MockMvc mockMvc;

    // 주입받는 객체 설정하기
    @MockBean
    BoardService boardService;

    @MockBean
    CommentService commentService;




}
