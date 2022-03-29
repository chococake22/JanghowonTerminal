package janghowon.terminal.controller;

import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.service.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AccountControllerTest {

    @InjectMocks    // Mock 객체가 주입될 클래스
    private AccountService accountService;

    @Mock   // Mock 객체 형성, 실행하면 Mock 객체 반환
    private AccountRepository accountRepository;

    static Account account;
    static AccountDto accountDto;

    @Test
    @DisplayName("회원가입이 잘 되었나?")
    void 회원확인() {







    }

//    @Test
//    @Order(2)
//    @DisplayName("회원정보가 수정되었나?")
//    void 회원정보수정() {
//
//        // when
//        AccountDto accountDto1 = accountService.getAccount(accountDto.getUsername());
//        accountDto1.setPhone("01099992222");
//        accountDto1.setEmail("test@test11.com");
//        accountService.save(accountDto1);
//
//        // then
//
//        // static import를 사용하면 메소드나 변수를 패키지, 클래스 명을 기입할 필요 없이 접근 가능하다. (코드가 더 깔끔해보임)
//        assertThat(accountDto1.getPhone()).isEqualTo(accountService.getAccount(accountDto.getUsername()).getPhone());
//
//    }

//    @Test
//    @DisplayName("만약 비밀번호가 조건을 충족시키지 못하면?")
//    void 비밀번호오류() {
//
//        String password = "1234";
//        Account account = new Account(2L, "test2", password, "test2@test.com", "01099887766", Role.USER);
//
//    }
}
