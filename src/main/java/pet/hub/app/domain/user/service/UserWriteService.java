package pet.hub.app.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.user.dto.request.SignupRequestDto;
import pet.hub.app.domain.user.entity.Authentication;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.AuthenticationRepository;
import pet.hub.app.domain.user.repository.UserRepository;
import pet.hub.app.domain.user.util.Address;
import pet.hub.app.domain.user.util.InfoSet;
import pet.hub.app.domain.user.util.Role;

@RequiredArgsConstructor
@Service
public class UserWriteService {
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    @Transactional
    public User signup(SignupRequestDto signupRequestDto) {
        Authentication authentication = Authentication.builder()
                .userId(signupRequestDto.getUserId())
                .email(signupRequestDto.getEmail())
                .password(signupRequestDto.getPassword())
                .infoSet(InfoSet.DEFAULT)
                .build();
        authenticationRepository.save(authentication);
        Address address = Address.builder()
                .city(signupRequestDto.getCity())
                .district(signupRequestDto.getDistrict())
                .roadAddress(signupRequestDto.getRoadAddress())
                .build();
        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .role(Role.USER)
                .nickname(signupRequestDto.getNickname())
                .sex(signupRequestDto.getSex())
                .address(address)
                .build();
        user.setAuthentication(authentication);
        userRepository.save(user);
        return user;

    }
}
