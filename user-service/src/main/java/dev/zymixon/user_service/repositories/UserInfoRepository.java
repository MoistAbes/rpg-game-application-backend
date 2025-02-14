package dev.zymixon.user_service.repositories;

import dev.zymixon.user_service.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT u from UserInfo u WHERE  u.username = :username")
    UserInfo findByUsername(@Param("username") String username);

    @Query("SELECT u from UserInfo u where u.username = :username or u.email = :email")
    Optional<UserInfo> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

}
