package janghowon.terminal.controller;

import janghowon.terminal.dto.BusInfoDto;
import janghowon.terminal.service.BusInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        model.addAttribute("businfos", busInfoDtoList);
        return "time/timedetail";
    }



}
