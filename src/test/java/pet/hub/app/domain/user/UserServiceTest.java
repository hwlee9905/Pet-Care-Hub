package pet.hub.app.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet.hub.app.domain.pet.PetService;
import pet.hub.app.domain.user.service.UserService;
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("1.[Create]")
    void create() {
        userService.create();
    }

}
