package org.sopt.user.domain;

import org.sopt.diary.repository.DiaryEntity;
import org.sopt.user.repository.UserEntity;

public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;

    private User(Long id, String username, String password, String nickname){
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static User from(Long id, String username, String password, String nickname){
        return new User(id, username, password, nickname);
    }
    public static UserEntity toEntity(User user){
        return new UserEntity(user.id, user.username, user.password, user.nickname);
    }
}
