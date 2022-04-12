package janghowon.terminal.controller;

import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.service.AccountService;
import org.assertj.core.api.Assertions;
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
    void 회원가입() {






    }




}
