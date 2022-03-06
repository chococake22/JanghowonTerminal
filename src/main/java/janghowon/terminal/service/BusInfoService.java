package janghowon.terminal.service;

import janghowon.terminal.domain.BusInfo;
import janghowon.terminal.dto.BusInfoDto;
import janghowon.terminal.repository.BusInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BusInfoService {

    private BusInfoRepository busInfoRepository;

    // 버스 시간 작성
    @Transactional
    public Long save(BusInfoDto busInfoDto) {
        return busInfoRepository.save(busInfoDto.toEntity()).getId();
    }


    @Transactional
    public Page<BusInfoDto> searchTimes(@RequestParam String arrival, Pageable pageable) {

        int page;

        if(pageable.getPageNumber() == 0) {
            page = 0;
        } else {
            page = pageable.getPageNumber() - 1;
        }

        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "departtime"));
        Page<BusInfo> busInfosEntities = busInfoRepository.findAllByArrival(arrival, pageable);
        int totalElements = (int) busInfosEntities.getTotalElements();

        return new PageImpl<BusInfoDto>(busInfosEntities.getContent()
                .stream()
                .map(busInfo -> new BusInfoDto(
                        busInfo.getId(),
                        busInfo.getArrival(),
                        busInfo.getDeparttime(),
                        busInfo.getLayover(),
                        busInfo.getNote()))
                .collect(Collectors.toList()), pageable, totalElements);


    }

}
