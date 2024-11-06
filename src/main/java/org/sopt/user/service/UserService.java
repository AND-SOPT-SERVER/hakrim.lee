package org.sopt.user.service;

import org.sopt.user.domain.User;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void signUp(User user){
        userRepository.save(User.toEntity(user));
    }

    public boolean userMatching(String username, String password) {
        //checking logic
        return password.equals(userRepository.findPasswordByUsername(username));
    }


}
