package pet.hub.app.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.board.enums.BoardTab;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.UserRepository;
import pet.hub.app.domain.user.util.Address;
import pet.hub.app.domain.user.util.ProfileImage;
import pet.hub.app.domain.user.util.Role;
import pet.hub.app.domain.user.util.Sex;
import pet.hub.app.web.dto.board.BoardRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        Address address = new Address("City", "Street", "10000");
        ProfileImage profileImage = new ProfileImage("url","path","image");
        user = userRepository.save(User.builder()
                .username("testUser")
                .nickname("testNick")
                .address(address)
                .profileImage(profileImage)
                .introduction("Hello, this is a test introduction.")
                .sex(Sex.MAN)
                .role(Role.USER)
                .build());
    }

    @DisplayName("Save Test")
    @Test
    public void boardSave(){
        BoardRequestDto requestDto = BoardRequestDto.builder()
                        .title("Dto Save Test")
                        .content("Dto Save Test2")
                        .boardTab(BoardTab.CAT)
                        .userId(user.getId())
                        .build();

        Board savedBoard = boardService.saveBoard(requestDto);

        assertThat(savedBoard).isNotNull();
        assertThat(savedBoard.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(savedBoard.getContent()).isEqualTo(requestDto.getContent());
    }

    @Test
    @DisplayName("Search Test")
    public void boardSearch() {

        BoardRequestDto requestDto = BoardRequestDto.builder()
                .title("오현두1")
                .content("검색1")
                .boardTab(BoardTab.DOG)
                .userId(user.getId())
                .build();

        BoardRequestDto requestDto2 = BoardRequestDto.builder()
                .title("오현두2")
                .content("검색2")
                .boardTab(BoardTab.CAT)
                .userId(user.getId())
                .build();

        boardService.saveBoard(requestDto);
        boardService.saveBoard(requestDto2);

        List<Board> searchResults = boardService.searchBoards("오현두");

        assertThat(searchResults).isNotEmpty();
        assertThat(searchResults.size()).isEqualTo(2);
        assertTrue(searchResults.stream().allMatch(board -> board.getTitle().contains("오현두") || board.getContent().contains("검색")));
    }

    @Test
    @DisplayName("Update Test")
    void updateBoardTest() {

        BoardRequestDto requestDto = BoardRequestDto.builder()
                .title("수정 전")
                .content("내용")
                .boardTab(BoardTab.CAT)
                .userId(user.getId())
                .build();

        Board beforeBoard = boardService.saveBoard(requestDto);

        BoardRequestDto updateDto = BoardRequestDto.builder()
                .title("수정 후")
                .content("변경")
                .boardTab(BoardTab.DOG)
                .userId(user.getId())
                .build();

        boardService.updateBoard(beforeBoard.getBoardId(), updateDto);

        Board updatedBoard = boardRepository.findById(beforeBoard.getBoardId()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        assertThat(requestDto.getTitle()).isNotEqualTo(updateDto.getTitle());
        assertThat(requestDto.getContent()).isNotEqualTo(updateDto.getContent());
        assertThat(requestDto.getBoardTab()).isNotEqualTo(updateDto.getBoardTab());

        assertThat(updatedBoard.getTitle()).isEqualTo(updateDto.getTitle());
        assertThat(updatedBoard.getContent()).isEqualTo(updateDto.getContent());
        assertThat(updatedBoard.getBoardTab()).isEqualTo(updateDto.getBoardTab());
    }
}