package janghowon.terminal.service;

import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.AccountDetails;
import janghowon.terminal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        return new AccountDetails(account);


    }
}
