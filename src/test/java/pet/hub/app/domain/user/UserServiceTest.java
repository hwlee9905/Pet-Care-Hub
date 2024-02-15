package pet.hub.app.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet.hub.app.domain.user.entity.User;
import pet.hub.app.domain.user.service.UserReadService;
import pet.hub.app.domain.user.service.UserWriteService;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserWriteService userWriteService;
    @Autowired
    private UserReadService userReadService;
    @Test
    @DisplayName("1.[Create]")
    void 회원가입() {
//        User user = userWriteService.signup();
//        assertThat(user.getUsername()).isEqualTo("이현웅");
//        assertThat(user.getAuthentication().getUserId()).isEqualTo("hwlee");
    }
    @Test
    @DisplayName("2.[Read]")
    void 회원조회() {
        User user = userReadService.Read();
        assertThat(user.getAuthentication().getUserId()).isEqualTo("hwlee");
    }

}
