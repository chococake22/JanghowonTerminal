package janghowon.terminal.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountBusInfo {

    @Id @GeneratedValue
    @Column(name = "account_businfo_id")
    private Long id;

    // 각각의 account와 businfo를 조회할 경우 즉시 모든 정보를 가져올 필요는 없음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businfo_id")
    private BusInfo businfo;

    // 특이사항
    @Column
    private String notice;

    @Builder
    public AccountBusInfo(Long id, Account account, BusInfo businfo, String notice) {
        this.id = id;
        this.account = account;
        this.businfo = businfo;
        this.notice = notice;
    }
}
