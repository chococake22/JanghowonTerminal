package janghowon.terminal.service;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.AccountBusInfo;
import janghowon.terminal.domain.BusInfo;
import janghowon.terminal.dto.BusInfoDto;
import janghowon.terminal.repository.AccountBusInfoRepository;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.repository.BusInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BusInfoService {

    private BusInfoRepository busInfoRepository;

    private AccountRepository accountRepository;

    private AccountBusInfoRepository accountBusInfoRepository;

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
                        busInfo.getPrice(),
                        busInfo.getLayover(),
                        busInfo.getNote(),
                        busInfo.getAccountBusInfos()))
                .collect(Collectors.toList()), pageable, totalElements);
    }

    // 내 시간표 추가하기
    @Transactional
    public void mytimeadd(BusInfoDto busInfoDto, AccountDetails accountDetails) {

        Optional<Account> a = accountRepository.findByUsername(accountDetails.getUsername());
        Account account = a.get();

        BusInfo busInfo = busInfoDto.toEntity();

        // 등록한 시간표가 이미 있는지 없는지를 판단
        // 없으면 새로 등록
        if (accountBusInfoRepository.findByBusinfo_Id(busInfo.getId())==null) {
            AccountBusInfo accountBusInfo = new AccountBusInfo();
            accountBusInfo.setBusinfo(busInfo);
            accountBusInfo.setAccount(account);
            accountBusInfo.setNotice(busInfo.getNote());
            accountBusInfoRepository.save(accountBusInfo);
            log.info("accountBusInfo = {}" + accountBusInfo);

            // 있으면 추가 안함
        } else {
            log.info("이미 있는 거라서 추가 안함");
        }
    }

}
