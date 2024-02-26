package pet.hub.app.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pet.hub.app.domain.oauth2.dto.OAuth2UserDto;
import pet.hub.app.domain.oauth2.dto.response.OAuth2GoogleResponseDto;
import pet.hub.app.domain.oauth2.dto.response.OAuth2NaverResponseDto;
import pet.hub.app.domain.oauth2.dto.response.OAuth2Response;
import pet.hub.app.domain.oauth2.CustomOAuth2User;
import pet.hub.app.domain.user.entity.Authentication;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.repository.AuthenticationRepository;
import pet.hub.app.domain.user.repository.UserRepository;
import pet.hub.app.domain.user.util.InfoSet;
import pet.hub.app.domain.user.util.Role;
@RequiredArgsConstructor
@Service
@Slf4j
public class OAuth2UserSerivce extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        InfoSet infoSet;
        log.info("OAuth2User : " + oAuth2User);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            infoSet = InfoSet.NAVER;
            oAuth2Response = new OAuth2NaverResponseDto(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            infoSet = InfoSet.GOOGLE;
            oAuth2Response = new OAuth2GoogleResponseDto(oAuth2User.getAttributes());
        }
        else {

            return null;
        }
        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        User existData = userRepository.findByUserId(username);

        if (existData == null) {
            Authentication authentication = Authentication.builder()
                    .userId(username)
                    .email(oAuth2Response.getEmail())
                    .infoSet(infoSet)
                    .build();
            authenticationRepository.save(authentication);
            User user = User.builder()
                    .username(oAuth2Response.getName())
                    .role(Role.USER)
                    .build();
            user.setAuthentication(authentication);
            userRepository.save(user);

            OAuth2UserDto oAuth2UserDto = OAuth2UserDto.builder()
                    .name(oAuth2Response.getName())
                    .username(username)
                    .role(Role.USER.toString())
                    .build();

            return new CustomOAuth2User(oAuth2UserDto);
        }
        else {

            existData.getAuthentication().setEmail(oAuth2Response.getEmail());
            existData.setUsername(oAuth2Response.getName());

            userRepository.save(existData);

            OAuth2UserDto oAuth2UserDto = OAuth2UserDto.builder()
                    .username(existData.getAuthentication().getUserId())
                    .name(oAuth2Response.getName())
                    .role(Role.USER.toString())
                    .build();

            return new CustomOAuth2User(oAuth2UserDto);
        }
    }
}
