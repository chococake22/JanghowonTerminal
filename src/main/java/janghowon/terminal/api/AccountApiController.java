package janghowon.terminal.api;

import janghowon.terminal.domain.Account;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountApiController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    // 전체 회원 조회
    @GetMapping("/list")
    public List<Account> allAccount() {
        return accountRepository.findAll();
    }

    // 회원 상세 정보
    @GetMapping("/detail/{username}")
    public Account getAccount(@PathVariable String username) {
        return accountRepository.findByUsername(username).get();
    }

    // 회원가입
    @PostMapping("/signup")
    public Long signUpAccount(@RequestBody AccountDto accountDto) {
        log.info("accountDto.getEmail() = {} : " + accountDto.getEmail());
        return accountService.save(accountDto);
    }

    // 회원 정보 수정
    @PutMapping("/update")
    public Long update(@RequestBody AccountDto accountDto) {
        return accountService.update(accountDto);
    }










}
