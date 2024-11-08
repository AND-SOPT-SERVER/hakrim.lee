package org.sopt.user.api;
import org.sopt.user.api.request.LoginRequest;
import org.sopt.user.domain.User;
import org.sopt.user.api.request.SignUpRequest;
import org.sopt.user.service.UserService;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<String> signUp(@RequestBody SignUpRequest request){

        try{
            userService.signUp(User.from(
                    null,
                    request.username(),
                    request.password(),
                    request.nickname()
            ));
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    ResponseEntity<String> login(@RequestBody LoginRequest request) {

        if(userService.userMatching(request.username(), request.password())){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 일치하지 않습니다");
        }
    }

}
