package pet.hub.app.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.AuthenticationRepository;
import pet.hub.app.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserReadService {
    private final UserRepository userRepository;
    public User Read() {
        return userRepository.findById(1L).get();
    }
}
