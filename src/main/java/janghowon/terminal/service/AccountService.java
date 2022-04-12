package janghowon.terminal.service;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.role.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Slf4j
@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    // 생성자 주입
    private AccountRepository accountRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 시큐리리 설정을 통해 로그인한 객체가 회원가입된 객체가 맞는지 확인
    // UserDetails를 구현한 AccountDetails 객체로 반환해서 authentication 안에 저장한다.
    @Override
    public UserDetails loadUserByUsername(String username) {

        // Optional.of 메소드를 통해 반드시 null이 아닌 값을 반환하게 하고 null일 경우 에러 발생(NullPointerException)
        Optional<Account> accountOptional = Optional.of(accountRepository.findByUsername(username)).get();

        Account account = accountOptional.get();

        // UserDetails를 구현한 객체에 담아서 넘기기
        return new AccountDetails(account);
    }

    // 회원가입
    @Transactional
    public Long save(AccountDto accountDto) {

        // 회원가입시 자동으로 USER 권한 설정
        // ADMIN은 관리자만 사용
        accountDto.setRole(Role.USER);

        // 회원가입 시 비밀번호 암호화
        accountDto.setPassword(bCryptPasswordEncoder
                .encode(accountDto.getPassword()));

        return accountRepository.save(accountDto.toEntity()).getId();
    }


    // 회원 정보 가져오기(entity -> dto)
    @Transactional
    public AccountDto getAccount(String username) {

        // null일 경우 NPE 에러
        Optional<Account> accountOptional = Optional.of(accountRepository.findByUsername(username)).get();

        Account account = accountOptional.get();

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

        Optional<Account> accountOptional = Optional.of(accountRepository.findById(accountDto.getId()).get());
        Account account = accountOptional.get();

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
