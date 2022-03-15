package janghowon.terminal.auth;

import janghowon.terminal.domain.Account;
import janghowon.terminal.role.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails implements UserDetails {

    private Account account;

    // UserDetails안에 있는 회원 정보 조회
    public Account getAccount() {
        return account;
    }

    // 권한 설정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Role role = account.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(authority);

        return collections;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
