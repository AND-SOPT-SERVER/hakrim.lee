package org.sopt.user.api;
import org.sopt.user.api.request.LoginRequest;
import org.sopt.user.domain.User;
import org.sopt.user.api.request.SignUpRequest;
import org.sopt.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/signup")
    ResponseEntity<Void> signUp(@RequestBody SignUpRequest request){
        //중복체크 Valid
        userService.signUp(User.from(
                null,
                request.username(),
                request.password(),
                request.nickname()
        ));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        if(userService.userMatching(request.username(), request.password())){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
