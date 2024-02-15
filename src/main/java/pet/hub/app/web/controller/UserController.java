package pet.hub.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pet.hub.app.domain.user.dto.request.SignupRequestDto;
import pet.hub.app.domain.user.service.UserWriteService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserWriteService userWriteService;
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto){
        userWriteService.signup(signupRequestDto);

        return "OK";
    }
}
