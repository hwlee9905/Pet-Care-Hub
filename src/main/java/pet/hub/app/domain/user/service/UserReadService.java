package pet.hub.app.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.jwt.dto.AuthTokenDto;
import pet.hub.app.domain.jwt.dto.CustomUserDetails;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserReadService implements UserDetailsService {
    private final UserRepository userRepository;
    public User Read() {
        return userRepository.findById(1L).get();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //DB에서 조회
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            AuthTokenDto authTokenDto = AuthTokenDto.builder()
                    .username(user.getAuthentication().getUserId())
                    .password(user.getAuthentication().getPassword())
                    .role(user.getRole().toString())
                    .build();
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(authTokenDto);
        }

        return null;
    }
}
