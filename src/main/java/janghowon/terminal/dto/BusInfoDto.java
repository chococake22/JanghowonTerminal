package janghowon.terminal.dto;

import janghowon.terminal.domain.AccountBusInfo;
import janghowon.terminal.domain.BusInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BusInfoDto {

    private Long id;
    private String arrival;
    private String departtime;
    private Integer price;
    private String layover;
    private String note;
    private List<AccountBusInfo> accountBusInfos = new ArrayList<>();

    public BusInfo toEntity() {
        BusInfo busInfo = BusInfo.builder()
                .id(id)
                .arrival(arrival)
                .departtime(departtime)
                .price(price)
                .layover(layover)
                .note(note)
                .accountBusInfos(accountBusInfos)
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
