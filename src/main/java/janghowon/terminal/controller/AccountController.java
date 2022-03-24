package janghowon.terminal.controller;


import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.AccountBusInfo;
import janghowon.terminal.dto.AccountDto;
import janghowon.terminal.repository.AccountBusInfoRepository;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    private AccountRepository accountRepository;

    private AccountBusInfoRepository accountBusInfoRepository;

    // 메인 페이지
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {
        model.addAttribute("accountDetails", accountDetails);
        return "/index";
    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "account/signup";
    }

    // 회원가입 확인
    @PostMapping("/signup")
    public String signUp(@ModelAttribute AccountDto accountDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 조건에 맞는지 오류를 검증하고 오류가 나면 bindingResult에 FieldError 객체를 집어 넣는다

        // 검증 로직
        // 1. 아이디를 입력하지 않은 경우
        if(ObjectUtils.isEmpty(accountDto.getUsername())) {
            bindingResult.addError(new FieldError("accountDto", "username", accountDto.getUsername(), false, new String[]{"required.accountDto.username"}, null, null));
        }

        log.info("accountDto.getUsername={} " + accountDto.getUsername());

        // 2. 아이디를 입력했는데 중복인 경우
        if(!ObjectUtils.isEmpty(accountDto.getUsername())
                && !ObjectUtils.isEmpty(accountRepository.findByUsername(accountDto.getUsername()))) {
                bindingResult.addError(new FieldError("accountDto"
                ,"username"
                ,"이미 존재하는 아이디입니다."));
        }

        // 3. 비밀번호가 영문 대문자, 소문자, 특수문자 조합이어야 하고 8자 이상 20자 이하
        String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}";
        if (ObjectUtils.isEmpty(accountDto.getPassword())
            || !(accountDto.getPassword().matches(pattern))) {
            bindingResult.addError(new FieldError("accountDto"
                    , "password"
                    ,"비밀번호가 영문 대문자, 소문자, 특수문자 조합이어야 하고 8자 이상 20자 이하여야 합니다."));
        }

        // 4. 이메일을 입력하지 않은 경우
        if(ObjectUtils.isEmpty(accountDto.getEmail())) {
            bindingResult.addError(new FieldError("accountDto", "email", accountDto.getEmail(), false, new String[]{"required.accountDto.email"}, null, null));
        }

        // 5. 전화번호를 입력하지 않은 경우
        if(ObjectUtils.isEmpty(accountDto.getPhone())) {
            bindingResult.addError(new FieldError("accountDto", "phone", accountDto.getPhone(), false, new String[]{"required.accountDto.phone"}, null, null));
        }

        // 에러가 하나라도 있으면 다시 회원가입 폼으로 간다
        if (bindingResult.hasErrors()) {
            return "/account/signup";
        }

        // 검증 성공
        // redirect를 할 경우 데이터를 전달한다.
        redirectAttributes.addAttribute("username", accountDto.getUsername());
        redirectAttributes.addAttribute("status", true);

        accountService.save(accountDto);
        return "redirect:/";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage(Model model, @AuthenticationPrincipal AccountDetails accountDetails, AccountBusInfo accountBusInfo) {
        AccountDto accountDto = accountService.getAccount(accountDetails.getUsername());
        List<AccountBusInfo> accountBusInfos = accountBusInfoRepository.findAllByAccount_Id(accountDto.getId());

        model.addAttribute("accountDto", accountDto);
        model.addAttribute("accountBusInfos", accountBusInfos);
        return "account/mypage";
    }

    // 회원정보 변경 폼
    @GetMapping("/mypage/changeinfo")
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
    @GetMapping("/mypage/changepwd")
    public String changePwd(Model model, @AuthenticationPrincipal AccountDetails accountDetails) {

        AccountDto accountDto = accountService.getAccount(accountDetails.getUsername());
        model.addAttribute("accountDto", accountDto);
        model.addAttribute("accountDetails", accountDetails);
        return "account/changepwd";
    }

    // 비밀번호 변경 확인
    @PutMapping("/changepwd")
    public String changePwdUpdate(@ModelAttribute AccountDto accountDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 비밀번호가 영문 대문자, 소문자, 특수문자 조합이어야 하고 8자 이상 20자 이하
        String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}";
        if (ObjectUtils.isEmpty(accountDto.getPassword())
                || !(accountDto.getPassword().matches(pattern))) {
            bindingResult.addError(new FieldError("accountDto"
                    ,"password"
                    ,"비밀번호가 영문 대문자, 소문자, 특수문자 조합이어야 하고 8자 이상 20자 이하여야 합니다."));
        }

        // 에러가 하나라도 있으면 다시 회원가입 폼으로 간다
        if (bindingResult.hasErrors()) {
            return "account/changepwd";
        }

        // redirect를 할 경우 데이터를 전달한다.
        redirectAttributes.addAttribute("username", accountDto.getUsername());
        redirectAttributes.addAttribute("status", true);
        accountService.update(accountDto);
        return "redirect:/mypage";
    }
}
