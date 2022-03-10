package janghowon.terminal.service;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.auth.UserAccount;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        return new AccountDetails(account);
    }

    // 회원 가입
    @Transactional
    public Long save(AccountDto accountDto) {

        // 회원가입시 자동으로 USER 권한 설정
        // ADMIN은 관리자만 사용
        accountDto.setRole(Role.USER);

        accountDto.setPassword(bCryptPasswordEncoder
                .encode(accountDto.getPassword()));

        return accountRepository.save(accountDto.toEntity()).getId();


    }
}
