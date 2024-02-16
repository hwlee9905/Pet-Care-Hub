package pet.hub.app.domain.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pet.hub.app.domain.board.enums.BoardTab;
import pet.hub.app.web.dto.board.BoardRequestDto;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        boardRepository.deleteAll();

        Board board = Board.builder()
                    .boardId(1L)
                    .title("Dto Save Test")
                    .content("Dto Save Test2")
                    .boardtab(BoardTab.CAT)
                    .build();
        Mockito.lenient().when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
    }

    @Test
    public void boardSave(){
        BoardRequestDto requestDto = BoardRequestDto.builder()
                        .title("Dto Save Test")
                        .content("Dto Save Test2")
                        .boardtab(BoardTab.CAT)
                        .build();

        when(boardRepository.save(any(Board.class))).then(returnsFirstArg());

        Board savedBoard = boardService.boardSave(requestDto);

        Assertions.assertEquals(boardRepository.findById(1L).get().getTitle(), savedBoard.getTitle());
        Assertions.assertEquals(boardRepository.findById(1L).get().getContent(), savedBoard.getContent());
        Assertions.assertEquals(boardRepository.findById(1L).get().getBoardtab(), savedBoard.getBoardtab());
    }
}
