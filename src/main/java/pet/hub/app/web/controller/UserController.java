package pet.hub.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.hub.app.domain.user.dto.request.SignupRequestDto;
import pet.hub.app.domain.user.service.UserWriteService;

@RestController
@RequiredArgsConstructor
public class UserController {
    //.requestMatchers("/admin").hasAnyAuthority("USER") USER 계정 로그인 필요
    @GetMapping("/user")
    public String userHome(){


        return "유저로 로그인하셨습니다. 유저 컨트롤러입니다.";
    }

}
