package pet.hub.app.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.web.dto.board.BoardRequestDto;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Board boardSave(BoardRequestDto requestDto){
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .boardtab(requestDto.getBoardtab())
                .build();
        return boardRepository.save(board);
    }
}
