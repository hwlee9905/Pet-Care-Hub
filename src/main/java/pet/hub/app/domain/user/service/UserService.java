package pet.hub.app.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.pet.Pet;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public User create() {
        User user = User.builder()
                .nickname("nickname")
                .address("address")
                .build();

        return userRepository.save(user);
    }
}
