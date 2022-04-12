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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class AccountServiceTest {

    @Mock   // 주입할 객체
    private AccountRepository accountRepository;

    @Spy
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks    // Mock 객체가 주입될 클래스(주입 받을 객체)
    private AccountService accountService;

    static Account account = new Account(2L, "test", "1234", "test@test.com", "01012344567", Role.USER);

    public static AccountDto createAccountDto() {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .phone(account.getPhone())
                .role(account.getRole())
                .build();
    }

    @Test
    @DisplayName("회원가입하기")
    void 회원가입() {


    }

    @Test
    @DisplayName("회원정보 수정")
    void testThenReturn() throws Exception {




    }
//
//    @Test
//    @DisplayName("Mockito 예외")
//    void testThenThrows() {
//
//        // 정해진 메서드 실행시 예외 처리
//        // getAccount는 다른 클래스에 의존하지 않은 메소드이기 때문에 accountService가 수행 가능함
//        when(accountService.getAccount(accountDto.getUsername())).thenThrow(new IllegalArgumentException());
//
//        assertThatThrownBy(() -> accountService.getAccount(accountDto.getUsername())).isInstanceOf(IllegalArgumentException.class);
//
//    }
}
