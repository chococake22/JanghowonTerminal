package janghowon.terminal.service;

import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.role.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class AccountServiceTest {

    @InjectMocks    // Mock 객체가 주입될 클래스(주입 받을 객체)
    private AccountService accountService;

    @Mock   // 주입할 객체
    private AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    static Account account;
    static AccountDto accountDto;

    @Test
    @DisplayName("Mockito stubbing 반환")
    void testThenReturn(){

        accountDto = new AccountDto(2L, "haha", "1234","haha@haha.com", "01022443355", Role.USER);

        when(accountService.getAccount(accountDto.getUsername())).thenReturn(accountDto);

        assertThat(accountService.getAccount(accountDto.getUsername())).isEqualTo(accountDto);

    }

    @Test
    @DisplayName("Mockito 예외")
    void testThenThrows() {

        accountDto = new AccountDto(2L, "haha", "1234","haha@haha.com", "01022443355", Role.USER);

        // 정해진 메서드 실행시 예외 처리
        when(accountService.getAccount(accountDto.getUsername())).thenThrow(new IllegalArgumentException());

        assertThatThrownBy(() -> accountService.getAccount(accountDto.getUsername())).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("실제 메서드 실행")
    void testThenCallRealMethod() {

        Optional<Account> account = accountRepository.findByUsername("apple");



        // 실제 메서드를 실행
        when(accountService.getAccount(account.get().getUsername())).thenCallRealMethod();

        assertThat(accountService.getAccount(account.get().getUsername())).isEqualTo(account.get().getUsername());

    }
}
