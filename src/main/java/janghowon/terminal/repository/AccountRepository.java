package janghowon.terminal.repository;

import janghowon.terminal.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {

    Account findByUsername(String useranme);



}
