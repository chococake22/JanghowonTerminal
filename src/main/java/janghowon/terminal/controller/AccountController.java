package janghowon.terminal.controller;


import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.auth.UserAccount;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    // 마이페이지 폼
    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        AccountDto accountDto = accountService.getAccount(accountDetails.getUsername());
        model.addAttribute("accountDto", accountDto);
        return "/account/mypage";
    }

    // 회원 정보 수정
    @PutMapping("/mypage")
    public String myPageUpdate(AccountDto accountDto) {
        accountService.update(accountDto);
        return "redirect:/";
    }

}
