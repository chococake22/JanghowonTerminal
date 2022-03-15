package janghowon.terminal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import janghowon.terminal.domain.Board;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findAllById(Long id);

    List<Board> findByTitleContaining(String keyword);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
