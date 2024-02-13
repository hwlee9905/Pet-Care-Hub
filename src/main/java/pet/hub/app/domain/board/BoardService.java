package pet.hub.app.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.board.enums.BoardTab;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Board save(String title, String content, BoardTab boardtab, Long userId){
        Board board = Board.builder()
                .title(title)
                .content(content)
                .boardtab(boardtab)
                .userId(userId)
                .build();

        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoards(String keyword) {
        return boardRepository.findByTitleOrContent(keyword, keyword);
    }

}
