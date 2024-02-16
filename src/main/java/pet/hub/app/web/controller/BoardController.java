package pet.hub.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.hub.app.domain.board.BoardRepository;
import pet.hub.app.domain.board.BoardService;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

}
