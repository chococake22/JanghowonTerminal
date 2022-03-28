package janghowon.terminal.controller;

import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountBusInfoRepository;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.role.Role;
import janghowon.terminal.service.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountBusInfoRepository accountBusInfoRepository;

    @Test
    void 회원가입() {

        // given
        Account account = new Account(1L, "test1", "test12#$A", "test1@test.com", "01022224555", Role.USER);
        AccountDto accountDto = AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .phone(account.getPhone())
                .role(account.getRole())
                .build();

        accountService.save(accountDto);

        // when
        AccountDto accountDto1 = accountService.getAccount(accountDto.getUsername());

        // then
        Assertions.assertThat(accountDto1.getUsername()).isEqualTo(account.getUsername());
    }



}
