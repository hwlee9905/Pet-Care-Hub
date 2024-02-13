package pet.hub.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.hub.app.domain.board.BoardRepository;
import pet.hub.app.domain.board.BoardService;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private BoardRepository boardRepository;
    private BoardService boardService;


}
