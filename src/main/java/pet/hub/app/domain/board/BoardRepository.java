package pet.hub.app.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
