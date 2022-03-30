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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class AccountServiceTest {

    @Mock   // 주입할 객체
    private AccountRepository accountRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks    // Mock 객체가 주입될 클래스(주입 받을 객체)
    private AccountService accountService;

    static AccountDto accountDto = new AccountDto(2L, "haha", "1234","haha@haha.com", "01022443355", Role.USER);
    static Account account = new Account(3L, "test1", "1234", "test1@test.com", "01054315432", Role.USER);

    @Test
    void mockRepository() {


        // interface의 메서드도 설정이 가능하다.
        // accountService를 테스트해야하기때문에 getAccount를 사용할 때에 accountRepository에 대한 메서드를 정의해주어야 한다.
        // 그 이유는 accountRepository를 실제 DB와 연결해서 사용하는 것이 아닌 단순히 연결이 된다고 가정한 후 accountService를 테스트하는 것이기 때문이다.

        // 먼저 accountRepository의 메서드를 정의
        when(accountRepository.findByUsername(any())).thenReturn(Optional.of(account));

        // 그 다음에 accountService의 getAccount 메서드를 테스트한다
        assertThat(accountService.getAccount(account.getUsername()).getPhone()).isEqualTo(account.getPhone());



    }

    @Test
    @DisplayName("Mockito stubbing 반환")
    void testThenReturn(){

        // accountDto의 아이디를 넣으면 accountDto 객체 반환
        when(accountService.getAccount(accountDto.getUsername())).thenReturn(accountDto);

        assertThat(accountService.getAccount(accountDto.getUsername())).isEqualTo(accountDto);

    }

    @Test
    @DisplayName("Mockito 예외")
    void testThenThrows() {

        // 정해진 메서드 실행시 예외 처리
        // getAccount는 다른 클래스에 의존하지 않은 메소드이기 때문에 accountService가 수행 가능함
        when(accountService.getAccount(accountDto.getUsername())).thenThrow(new IllegalArgumentException());

        assertThatThrownBy(() -> accountService.getAccount(accountDto.getUsername())).isInstanceOf(IllegalArgumentException.class);

    }
}
