package janghowon.terminal.controller;


import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.auth.UserAccount;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        model.addAttribute("accountDetails", accountDetails);
        return "/index";
    }

    @GetMapping("/signup")
    public String signUpForm() {
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signUp(AccountDto accountDto) {
        accountService.save(accountDto);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        model.addAttribute("accountDetails", accountDetails);
        return "/account/mypage";
    }

    @PostMapping("/mypage")
    public String myPageCheck(@AuthenticationPrincipal AccountDetails accountDetails) {
        accountService.getAccount(accountDetails.getUsername());
        return "redirect:/";
    }

}
