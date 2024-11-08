package org.sopt.user.service;

import jakarta.transaction.Transactional;
import org.sopt.DiaryApplication;
import org.sopt.user.domain.User;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void signUp(User user){

        try {
            userRepository.save(User.toEntity(user));
        }catch (Exception e){
            throw new DiaryApplication.DB.DataDuplicated("이미 존재하는 username 입니다");
        }
    }

    public boolean userMatching(String username, String password) {
        return password.equals(userRepository.findPasswordByUsername(username));
    }


}
