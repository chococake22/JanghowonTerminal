package janghowon.terminal.dto;

import janghowon.terminal.domain.BusInfo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BusInfoDto {

    private Long id;
    private String arrival;
    private String departtime;
    private String layover;
    private String note;

    public BusInfo toEntity() {
        BusInfo busInfo = BusInfo.builder()
                .id(id)
                .arrival(arrival)
                .departtime(departtime)
                .layover(layover)
                .note(note)
                .build();
        return busInfo;
    }

    @Builder
    public BusInfoDto(Long id, String arrival, String departtime, String layover, String note) {
        this.id = id;
        this.arrival = arrival;
        this.departtime = departtime;
        this.layover = layover;
        this.note = note;
    }
}
