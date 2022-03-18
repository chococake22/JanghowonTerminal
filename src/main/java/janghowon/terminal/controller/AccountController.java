package janghowon.terminal.controller;


import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    // 메인 페이지
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        model.addAttribute("accountDetails", accountDetails);
        return "/index";
    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String signUpForm() {
        return "account/signup";
    }

    // 회원가입 확인
    @PostMapping("/signup")
    public String signUp(AccountDto accountDto) {
        accountService.save(accountDto);
        return "redirect:/";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        AccountDto accountDto = accountService.getAccount(accountDetails.getUsername());
        model.addAttribute("accountDto", accountDto);
        return "/account/mypage";
    }

    // 회원정보 변경 폼
    @GetMapping("/changeinfo")
    public String changeinfo(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        AccountDto accountDto = accountService.getAccount(accountDetails.getUsername());
        model.addAttribute("accountDto", accountDto);
        model.addAttribute("accountDetails", accountDetails);
        return "/account/changeinfo";
    }

    // 회원 정보 수정
    @PutMapping("/changeinfo")
    public String changeInfoUpdate(AccountDto accountDto) {
        accountService.update(accountDto);
        return "redirect:/mypage";
    }

    // 비밀번호 변경 폼
    @GetMapping("/changepwd")
    public String changePwd(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        AccountDto accountDto = accountService.getAccount(accountDetails.getUsername());
        model.addAttribute("accountDto", accountDto);
        model.addAttribute("accountDetails", accountDetails);
        return "/account/changepwd";
    }

    // 비밀번호 변경 폼
    @PutMapping("/changepwd")
    public String changePwdUpdate(AccountDto accountDto) {
        accountService.update(accountDto);
        return "redirect:/mypage";
    }
}
