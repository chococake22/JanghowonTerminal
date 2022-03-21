package janghowon.terminal.controller;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.BusInfo;
import janghowon.terminal.dto.BusInfoDto;
import janghowon.terminal.service.BusInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
public class BusInfoController {

    @Autowired
    private BusInfoService busInfoService;

    // 버스 시간 추가
    @GetMapping("/timeadd")
    public String timeadd() {
        return "/time/timeadd";
    }

    // 버스 시간 추가 확인
    @PostMapping("/timeadd")
    public String timeadd(BusInfoDto busInfoDto){
        busInfoService.save(busInfoDto);
        return "redirect:/";
    }

    // 버스 시간 조회
    @GetMapping("/searchtime")
    public String searchtime(@RequestParam(value = "arrival") String arrival, Model model, Pageable pageable) {
        Page<BusInfoDto> busInfoDtoList = busInfoService.searchTimes(arrival, pageable);
        model.addAttribute("arrival", arrival);
        model.addAttribute("busInfoDtos", busInfoDtoList);
        return "time/timedetail";
    }

    // 내 버스 시간표로 추가
    @PostMapping("/searchtime/{id}")
    public String mytimeadd(@PathVariable("id") Long id, @AuthenticationPrincipal AccountDetails accountDetails) {
        busInfoService.mytimeadd(id, accountDetails);
        log.info("리다이렉트");
        return "redirect:/mypage";
    }
}
