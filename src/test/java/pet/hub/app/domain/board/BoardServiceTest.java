package pet.hub.app.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet.hub.app.domain.board.enums.BoardTab;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        boardRepository.deleteAll();
    }

    @Test
    public void boardSave(){
        String title = "spring";
        String content = "Boot";
        BoardTab tab = BoardTab.CAT;
        Long userId = 1L;

        Board savedBoard = boardService.save(title, content, tab, userId);

        Board foundBoard = boardRepository.findById(savedBoard.getBoardId()).orElse(null);
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getTitle()).isEqualTo(title);
        assertThat(foundBoard.getContent()).isEqualTo(content);
        assertThat(foundBoard.getBoardtab()).isEqualTo(tab);
        assertThat(foundBoard.getUserId()).isEqualTo(userId);
    }
}
