package janghowon.terminal.dto;

import com.sun.istack.NotNull;
import janghowon.terminal.domain.Account;
import janghowon.terminal.role.Role;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto {

    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Role role;

    public Account toEntity() {
        Account account = Account.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .role(role)
                .build();
        return account;
    }

    @Builder
    public AccountDto(Long id, String username, String password, String email, String phone, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}
