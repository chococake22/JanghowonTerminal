package janghowon.terminal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @GetMapping("/signup")
    public String signup() {
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signupCheck() {


    }

}
