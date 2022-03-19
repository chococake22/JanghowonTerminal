package janghowon.terminal.service;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.role.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> accountWrapper = accountRepository.findByUsername(username);

        Account account = accountWrapper.get();

        if(account == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        // UserDetails를 구현한 객체에 담아서 넘기기
        return new AccountDetails(account);
    }

    // 회원가입
    @Transactional
    public Long save(AccountDto accountDto) {

        // 회원가입시 자동으로 USER 권한 설정
        // ADMIN은 관리자만 사용
        accountDto.setRole(Role.USER);
        accountDto.setPassword(bCryptPasswordEncoder
                .encode(accountDto.getPassword()));

        return accountRepository.save(accountDto.toEntity()).getId();
    }


    // 회원 정보 가져오기(entity -> dto)
    @Transactional
    public AccountDto getAccount(String username) {

        log.info("username={}", username);

        Optional<Account> accountWrapper = accountRepository.findByUsername(username);
        Account account = accountWrapper.get();

        AccountDto accountDto = AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .phone(account.getPhone())
                .role(account.getRole())
                .build();

        return accountDto;

    }

    // 회원정보 수정
    @Transactional
    public Long update(AccountDto accountDto) {
        Optional<Account> accountWrapper = accountRepository.findById(accountDto.getId());
        Account account = accountWrapper.get();

        // 비밀번호 변경 여부
        if(accountDto.getPassword().equals(account.getPassword())) {
            accountDto.setPassword(account.getPassword());
        } else {
            accountDto.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        }

        // 엔티티가 스냅샷과 비교해서 바뀐 부분에 대해서 알아서 update 쿼리를 날리게 된다.
        // 그래서 update가 반영된 것.
        return accountRepository.save(accountDto.toEntity()).getId();
    }

}
