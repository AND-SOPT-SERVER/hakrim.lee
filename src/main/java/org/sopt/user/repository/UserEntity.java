package org.sopt.user.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "user_test")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    public UserEntity(){}
    public UserEntity(Long id, String username, String password, String nickname){
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
