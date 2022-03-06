package janghowon.terminal.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PUBLIC)  // 기본 생성자의 접근을 protected로 설정
@Getter
@Entity
@Table(name = "BusInfo")
public class BusInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 도착지
    @Column(nullable = false)
    private String arrival;

    // 출발 시간
    @Column(nullable = false)
    private String departtime;

    // 경유지
    private String layover;

    // 참고 사항
    private String note;

    @Builder
    public BusInfo(Long id, String arrival, String departtime, String layover, String note) {
        this.id = id;
        this.arrival = arrival;
        this.departtime = departtime;
        this.layover = layover;
        this.note = note;
    }


}
