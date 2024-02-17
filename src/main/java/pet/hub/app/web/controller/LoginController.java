package pet.hub.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.hub.app.domain.user.dto.request.SignupRequestDto;
import pet.hub.app.domain.user.service.UserWriteService;


@RequiredArgsConstructor
@RestController
public class LoginController {
    private final UserWriteService userWriteService;
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto){
        userWriteService.signup(signupRequestDto);

        return "OK";
    }
}
