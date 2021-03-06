package janghowon.terminal.service;

import janghowon.terminal.auth.AccountDetails;
import janghowon.terminal.domain.Board;
import janghowon.terminal.domain.Comment;
import janghowon.terminal.dto.BoardDto;
import janghowon.terminal.dto.CommentDto;
import janghowon.terminal.repository.BoardRepository;
import janghowon.terminal.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    private BoardRepository boardRepository;

    // List에 담겨있는 Entity -> Dto로 변환하기위해서 사용된 라이브러리
    private ModelMapper modelMapper;

    // 댓글 작성
    @Transactional
    public Long save(CommentDto commentDto, BoardDto boardDto,  @AuthenticationPrincipal AccountDetails accountDetails) {

        Board board = boardRepository.findAllById(boardDto.getId());

        CommentDto newCommentDto = new CommentDto();

        newCommentDto.setWriter(accountDetails.getUsername());
        newCommentDto.setBoard(board);
        newCommentDto.setContent(commentDto.getContent());

        return commentRepository.save(newCommentDto.toEntity()).getId();
    }

    // 댓글 리스트
    public List<CommentDto> getComments(Long id) {

        List<Comment> comments = commentRepository.findAllByBoardId(id);

        List<CommentDto> commentDtos = comments
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

        return commentDtos;
    }
}
