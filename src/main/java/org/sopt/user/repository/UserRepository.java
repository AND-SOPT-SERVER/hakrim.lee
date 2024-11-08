package org.sopt.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT password FROM user_test WHERE username = :username", nativeQuery = true)
    String findPasswordByUsername(@Param("username") String username);
}

