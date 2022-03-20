package janghowon.terminal.dto;

import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.AccountBusInfo;
import janghowon.terminal.domain.BusInfo;
import janghowon.terminal.role.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountBusInfoDto {

    private Long id;
    private Account account;
    private BusInfo busInfo;
    private String notice;

    public AccountBusInfo toEntity() {
        AccountBusInfo accountBusInfo = AccountBusInfo.builder()
                .id(id)
                .account(account)
                .businfo(busInfo)
                .notice(notice)
                .build();
        return accountBusInfo;
    }

    @Builder
    public AccountBusInfoDto(Long id, Account account, BusInfo busInfo, String notice) {
        this.id = id;
        this.account = account;
        this.busInfo = busInfo;
        this.notice = notice;
    }
}
