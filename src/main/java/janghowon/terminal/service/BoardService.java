package janghowon.terminal.service;


import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Account;
import janghowon.terminal.domain.Comment;
import janghowon.terminal.repository.AccountRepository;
import janghowon.terminal.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import janghowon.terminal.domain.Board;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {

    private AccountRepository accountRepository;
    private BoardRepository boardRepository;

    // 전체 게시물 조회
    @Transactional
    public Page<BoardDto> getBoards(Pageable pageable, @RequestParam(required = false, defaultValue = "") String search) {

        int page;

        if(pageable.getPageNumber() == 0) {
            page = 0;
        } else {
            page = pageable.getPageNumber() - 1;
        }

        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Board> p = boardRepository.findAll(pageable);

        int totalElements = (int) p.getTotalElements();

        // board -> Dto로 변환
        return new PageImpl<BoardDto>(p.getContent()
                .stream()
                .map(board -> new BoardDto(
                        board.getId(),
                        board.getAccount(),
                        board.getTitle(),
                        board.getContent(),
                        board.getComments(),
                        board.getCreatedDate(),
                        board.getModifiedDate()))
                .collect(Collectors.toList()), pageable, totalElements);
    }

    // 개별 게시물 상세 페이지
    @Transactional
    public BoardDto getBoard(Long id) {

        // boardWrapper가 null값을 가질 수도 있기 때문에 NPE를 예뱡하기 위해서 Wrapper 클래스를 사용한다.
        Optional<Board> boardWrapper = boardRepository.findById(id);

        // 여기서 엔티티만 가져오기
        Board board = boardWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .account(board.getAccount())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();

        return boardDto;
    }


    // 게시물 작성
    @Transactional
    public Long save(BoardDto boardDto, AccountDetails accountDetails) {

        Optional<Account> a = accountRepository.findById(accountDetails.getAccount().getId());
        Account account = a.get();
        boardDto.setAccount(account);
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    // 게시물 삭제
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    // 검색하기
    @Transactional
    public Page<BoardDto> searchBoards(String keyword, String content, Pageable pageable) {

        int page;

        if(pageable.getPageNumber() == 0) {
            page = 0;
        } else {
            page = pageable.getPageNumber() - 1;
        }

        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));

        // 제목에 해당 검색어가 포함된 게시물은 모두 가져온다.
        Page<Board> boardEntities = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);

        int totalElements = (int) boardEntities.getTotalElements();

        // board -> Dto로 변환
        return new PageImpl<BoardDto>(boardEntities.getContent()
                .stream()
                .map(board -> new BoardDto(
                        board.getId(),
                        board.getAccount(),
                        board.getTitle(),
                        board.getContent(),
                        board.getComments(),
                        board.getCreatedDate(),
                        board.getModifiedDate()))
                .collect(Collectors.toList()), pageable, totalElements);
    }

    // 총 게시글 개수
    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }
}
