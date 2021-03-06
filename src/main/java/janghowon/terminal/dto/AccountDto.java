package janghowon.terminal.dto;


import janghowon.terminal.domain.Account;
import janghowon.terminal.role.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
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
