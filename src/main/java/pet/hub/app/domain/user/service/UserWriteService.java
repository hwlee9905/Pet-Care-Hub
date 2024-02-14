package pet.hub.app.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.hub.app.domain.user.entity.Authentication;
import pet.hub.app.domain.user.entity.ProfileImage;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.AuthenticationRepository;
import pet.hub.app.domain.user.repository.UserRepository;
import pet.hub.app.domain.user.entity.Address;
import pet.hub.app.domain.user.util.InfoSet;
import pet.hub.app.domain.user.util.Role;
import pet.hub.app.domain.user.util.Sex;

@RequiredArgsConstructor
@Service
public class UserWriteService {
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    @Transactional
    public User create() {
        Authentication authentication = Authentication.builder()
                .userId("hwlee")
                .email("test@test.com")
                .password("testpw")
                .failCount(0)
                .infoSet(InfoSet.DEFAULT)
                .salt("randomSalt")
                .build();
        authenticationRepository.save(authentication);
        Address address = Address.builder()
                .city("서울시")
                .district("강남구")
                .roadAddress("강남로")
                .build();
        ProfileImage profileImage = ProfileImage.builder()
                .url("testUrl")
                .path("testPath")
                .name("imageName")
                .build();
        User user = User.builder()
                .username("이현웅")
                .introduction("introduction")
                .role(Role.USER)
                .nickname("nickname")
                .sex(Sex.MAN)
                .profileImage(profileImage)
                .address(address)
                .build();
        user.setAuthentication(authentication);
        userRepository.save(user);
        return user;

    }
}
