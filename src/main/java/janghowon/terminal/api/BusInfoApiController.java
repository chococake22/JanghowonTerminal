package janghowon.terminal.api;

import janghowon.terminal.dto.BusInfoDto;
import janghowon.terminal.service.BusInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class BusInfoApiController {

    @Autowired
    private BusInfoService busInfoService;

    // 목적지별 검색
    @GetMapping("/searchtime")
    public Page<BusInfoDto> searchtime(@RequestBody String arrival, Pageable pageable) {
        return busInfoService.searchTimes(arrival, pageable);
    }

}
