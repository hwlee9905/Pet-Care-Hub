package pet.hub.app.domain.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pet.hub.app.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
