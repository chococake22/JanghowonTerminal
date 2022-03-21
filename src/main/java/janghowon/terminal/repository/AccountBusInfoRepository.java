package janghowon.terminal.repository;

import janghowon.terminal.domain.AccountBusInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountBusInfoRepository extends JpaRepository<AccountBusInfo, Long> {

    List<AccountBusInfo> findAllByAccount_Id(Long id);

    AccountBusInfo findByBusinfo_Id(Long id);

    AccountBusInfo findByAccount_IdAndBusinfo_Id(Long id, Long id2);




}
