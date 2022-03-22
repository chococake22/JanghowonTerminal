package janghowon.terminal.api;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Board;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.BoardRepository;
import janghowon.terminal.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    // 게시물 목록 가져오기
    @GetMapping("/notice")
    public List<Board> list() {
        return boardRepository.findAll();
    }

    // 게시물 상세 정보
    @GetMapping("/write/{id}")
    public BoardDto detail(@PathVariable("id") Long id) {
        return boardService.getBoard(id);
    }

    // 게시물 수정
    @PutMapping("/write/edit/{id}")
    public Long update(@RequestBody BoardDto boardDto, AccountDetails accountDetails) {
        return boardService.save(boardDto, accountDetails);
    }

    // 게시물 검색
    @GetMapping("/board/search")
    public Page<BoardDto> search(@RequestBody String keyword, Pageable pageable) {
        return boardService.searchBoards(keyword, keyword, pageable);
    }
}
