package janghowon.terminal.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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

    // 요금
    @Column(nullable = false)
    private Integer price;

    // 경유지
    @Column
    private String layover;

    // 참고 사항
    @Column
    private String note;

    @OneToMany(mappedBy = "businfo", cascade = CascadeType.ALL)
    @JsonManagedReference   // 직렬화 수행
    private List<AccountBusInfo> accountBusInfos = new ArrayList<>();

    @Builder
    public BusInfo(Long id, String arrival, String departtime, Integer price, String layover, String note, List<AccountBusInfo> accountBusInfos) {
        this.id = id;
        this.arrival = arrival;
        this.departtime = departtime;
        this.price = price;
        this.layover = layover;
        this.note = note;
        this.accountBusInfos = accountBusInfos;
    }
}
