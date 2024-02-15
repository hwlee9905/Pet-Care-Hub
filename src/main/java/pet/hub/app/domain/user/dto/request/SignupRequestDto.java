package pet.hub.app.domain.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import pet.hub.app.domain.user.util.Address;
import pet.hub.app.domain.user.util.Sex;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String nickname;
    private String city;
    private String district;
    private String roadAddress;
    private Sex sex;
    private String userId;
    private String password;
    private String email;

}
