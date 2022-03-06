package janghowon.terminal.repository;

import janghowon.terminal.domain.BusInfo;
import janghowon.terminal.dto.BusInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusInfoRepository extends JpaRepository<BusInfo, Long> {

    Page<BusInfo> findAllByArrival(String arrival, Pageable pageable);

}
