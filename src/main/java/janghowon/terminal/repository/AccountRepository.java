package janghowon.terminal.repository;

import janghowon.terminal.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, Long> {

    // findByUsername 메서드 오버라이딩 - 반환 타입을 래퍼클래스로 지정해서 null에 대한 오류 처리
    Optional<Account> findByUsername(String username);




}
