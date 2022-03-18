package janghowon.terminal.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)  // 기본 생성자의 접근을 protected로 설정
@Getter
@Entity
@Table(name = "BusInfo")
public class BusInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "businfo_id")
    private Long id;

    // 도착지
    @Column(nullable = false)
    private String arrival;

    // 출발 시간
    @Column(nullable = false)
    private String departtime;

    @Column(nullable = false)
    private String price;

    // 경유지
    private String layover;

    // 참고 사항
    private String note;

    @OneToMany(mappedBy = "busInfo")
    private List<Account> accounts = new ArrayList<>();

    @Builder
    public BusInfo(Long id, String arrival, String departtime, String layover, String note) {
        this.id = id;
        this.arrival = arrival;
        this.departtime = departtime;
        this.layover = layover;
        this.note = note;
    }


}
